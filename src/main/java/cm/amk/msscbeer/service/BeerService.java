package cm.amk.msscbeer.service;

import cm.amk.msscbeer.web.model.BeerDto;
import cm.amk.msscbeer.web.model.BeerPagedList;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {
    BeerDto getBeerById(UUID beerId, Boolean showInventoryOnHand);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeer(UUID beerId, BeerDto beerDto);

    BeerPagedList findBeers(String beerName, String beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand);

    BeerDto getBeerByUPC(String upc, Boolean showInventoryOnHand);
}
