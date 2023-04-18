package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Setter
@Component
@ConfigurationProperties(value = "brewery", ignoreUnknownFields = false)
public class BreweryClient {

    private final String BEER_API_PATH = "/api/v1/beer";
    private String apihost;

    private final RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public BeerDto getBeerById(UUID uuid) {
        return restTemplate.getForObject(String.format("%s%s/%s", apihost, BEER_API_PATH, uuid.toString()), BeerDto.class);
    }

    public URI saveBeer(BeerDto beerDto) {
        return restTemplate.postForLocation(String.format("%s%s", apihost, BEER_API_PATH), beerDto);
    }

    public void updateBeer(BeerDto beerDto) {
        restTemplate.put(String.format("%s%s/%s", apihost, BEER_API_PATH, beerDto.getId().toString()), beerDto);
    }

    public void deleteBeer(UUID uuid) {
        restTemplate.delete(String.format("%s%s/%s", apihost, BEER_API_PATH, uuid.toString()));
    }
}
