package cm.amk.msscbeer.web.controller;

import cm.amk.msscbeer.boostrap.BeerLoader;
import cm.amk.msscbeer.service.BeerService;
import cm.amk.msscbeer.web.model.BeerDto;
import cm.amk.msscbeer.web.model.BeerStyleEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BeerService beerService;

    @Autowired
    ObjectMapper objectMapper;




    /*@Test
    void getBeerById() throws Exception {
        given(beerService.getBeerById(any(), any())).willReturn(getValidBeerDto());
        Map<String, Object> params = new LinkedHashMap<>();
        mockMvc.perform(get("/api/v1/beer/beer" + UUID.randomUUID().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveNewBeer() throws Exception {
        BeerDto beerDto = getValidBeerDto();
        String beerToJson = objectMapper.writeValueAsString(beerDto);

        given(beerService.saveNewBeer(any())).willReturn(beerDto);

        mockMvc.perform(post("/api/v1/beer/beer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerToJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.beerName", is(beerDto.getBeerName())))
                .andExpect(jsonPath("$.beerStyle", is(beerDto.getBeerStyle().toString())));
    }

    @Test
    void updateBeerById() throws Exception {
        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        given(beerService.updateBeer(any(), any())).willReturn(beerDto);

        mockMvc.perform(put("/api/v1/beer" + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.beerName", is(beerDto.getBeerName())))
                .andExpect(jsonPath("$.beerStyle", is(beerDto.getBeerStyle().toString())));

    }*/

    BeerDto getValidBeerDto() {
        return  BeerDto.builder()
                    .beerName("My Beer")
                    .beerStyle(BeerStyleEnum.ALE)
                    .price(new BigDecimal("2.99"))
                    .upc(BeerLoader.BEER_1_UPC)
                    .build();
    }
}