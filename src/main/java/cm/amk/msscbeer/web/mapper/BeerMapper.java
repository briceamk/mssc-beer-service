package cm.amk.msscbeer.web.mapper;

import cm.amk.msscbeer.domain.Beer;
import cm.amk.msscbeer.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {
    BeerDto beerToBeerDto(Beer beer);
    Beer beerDtoToBeer(BeerDto beerDto);
}
