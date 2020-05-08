package cm.amk.msscbeer.web.controller;

import cm.amk.msscbeer.service.BeerService;
import cm.amk.msscbeer.web.model.BeerDto;
import cm.amk.msscbeer.web.model.BeerPagedList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.JsonPath;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/beer")
@RequiredArgsConstructor
public class BeerController {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 20;
    private final BeerService beerService;

    @GetMapping
    public ResponseEntity<?> listBeers(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                       @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                       @RequestParam(value = "beerName", required = false) String beerName,
                                       @RequestParam(value = "beerStyle", required = false) String beerStyle,
                                       @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand) {

        if(pageNumber == null || pageNumber == 0)
            pageNumber = DEFAULT_PAGE_NUMBER;

        if(pageSize == null || pageSize == 0)
            pageSize = DEFAULT_PAGE_SIZE;

        if(showInventoryOnHand == null)
            showInventoryOnHand = false;

        BeerPagedList beerPagedList = beerService.findBeers(beerName, beerStyle, PageRequest.of(pageNumber, pageSize), showInventoryOnHand);
        return new ResponseEntity<>(beerPagedList, HttpStatus.OK);
    }

    @GetMapping("/{beerId}")
    public ResponseEntity<?> getBeerById(@PathVariable UUID beerId,
                                         @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand) {
        if(showInventoryOnHand == null)
            showInventoryOnHand = false;
        return new ResponseEntity<>(beerService.getBeerById(beerId, showInventoryOnHand), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveNewBeer(@Valid @RequestBody BeerDto beerDto) {
        return new ResponseEntity<>( beerService.saveNewBeer(beerDto), HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<?> updateBeerById(@PathVariable UUID beerId, @Valid @RequestBody BeerDto beerDto) {
        return new ResponseEntity<>(beerService.updateBeer(beerId, beerDto), HttpStatus.NO_CONTENT);
    }
}
