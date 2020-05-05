package cm.amk.msscbeer.web.controller;

import cm.amk.msscbeer.web.model.BeerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    @GetMapping("/{beerId}")
    public ResponseEntity<?> getBeerById(@PathVariable UUID beerId) {
        //TODO impl
        return new ResponseEntity<>(BeerDto.builder().build(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveNewBeer(@RequestBody BeerDto beerDto) {
        //TODO impl
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<?> updateBeerById(@PathVariable UUID beerId) {
        //TODO impl
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
