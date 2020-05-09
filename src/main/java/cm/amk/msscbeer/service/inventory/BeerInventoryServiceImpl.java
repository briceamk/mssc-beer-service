package cm.amk.msscbeer.service.inventory;

import cm.amk.msscbeer.service.inventory.model.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Component
public class BeerInventoryServiceImpl implements BeerInventoryService {

    private final RestTemplate restTemplate;
    private static final String INVENTORY_PATH = "/api/v1/beer/{beerId}/inventory";
    @Value("${inventory.service.host}")
    private String inventoryServiceHost;

    public BeerInventoryServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Integer getOnHandInventory(UUID beerId) {
        ResponseEntity<List<BeerInventoryDto>> responseEntity = restTemplate.exchange(inventoryServiceHost + INVENTORY_PATH, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<BeerInventoryDto>>() {}, (Object) beerId);

        Integer onHand = Objects.requireNonNull(responseEntity.getBody()).stream().mapToInt(BeerInventoryDto::getQuantityOnHand).sum();

        return onHand;
    }
}
