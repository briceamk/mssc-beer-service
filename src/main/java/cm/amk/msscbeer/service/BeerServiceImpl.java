package cm.amk.msscbeer.service;

import cm.amk.msscbeer.domain.Beer;
import cm.amk.msscbeer.repository.BeerRepository;
import cm.amk.msscbeer.web.controller.NotFoundException;
import cm.amk.msscbeer.web.mapper.BeerMapper;
import cm.amk.msscbeer.web.model.BeerDto;
import cm.amk.msscbeer.web.model.BeerPagedList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service("beerService")
@Slf4j
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    @Cacheable(cacheNames = "beerCache", key = "#beerId", condition = "#showInventoryOnHand == false")
    public BeerDto getBeerById(UUID beerId, Boolean showInventoryOnHand) {
        if(showInventoryOnHand) {
            return beerMapper.beerToBeerDtoWithInventory(beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
        }
        else{
            return beerMapper.beerToBeerDto(beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
        }
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
        beerRepository.findById(beerId).orElseThrow(NotFoundException::new);
        beerDto.setId(beerId);
        return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
    }

    @Override
    @Cacheable(cacheNames = "beerListCache", condition = "#showInventoryOnHand == false")
    public BeerPagedList findBeers(String beerName, String beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand) {
        BeerPagedList beerPagedList;
        Page<Beer> beerPage;

        if(!StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
            //search beers by beerName and beerStyle
            beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
        } else if(!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyle)) {
            //search beers by beerName
            beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
        } else if(StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
            //search beers by beerStyle
            beerPage = beerRepository.findByBeerStyle(beerStyle, pageRequest);
        } else {
            //search all beer
            beerPage = beerRepository.findAll(pageRequest);
        }
        if(showInventoryOnHand) {
            beerPagedList = new BeerPagedList(
                    beerPage.getContent().stream().map(beerMapper::beerToBeerDtoWithInventory).collect(Collectors.toList()),
                    PageRequest.of(beerPage.getPageable().getPageNumber(), beerPage.getPageable().getPageSize()),
                    beerPage.getTotalElements());
        } else {
            beerPagedList = new BeerPagedList(
                    beerPage.getContent().stream().map(beerMapper::beerToBeerDto).collect(Collectors.toList()),
                    PageRequest.of(beerPage.getPageable().getPageNumber(), beerPage.getPageable().getPageSize()),
                    beerPage.getTotalElements());
        }
        return beerPagedList;
    }

    @Override
    @Cacheable(value = "upcCache", key = "#upc", condition = "#showInventoryOnHand == false")
    public BeerDto getBeerByUPC(String upc, Boolean showInventoryOnHand) {
        log.info("*****************getBeerByUPC: I was called");
        Beer beer = beerRepository.findByUpc(upc).orElseThrow(NotFoundException::new);
        if(showInventoryOnHand) {
            return beerMapper.beerToBeerDtoWithInventory(beer);
        }else{
            return beerMapper.beerToBeerDto(beer);
        }
    }

}
