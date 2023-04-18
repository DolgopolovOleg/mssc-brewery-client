package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BreweryClientTest {

    // For these test mssc-brewery app should be started

    @Autowired
    BreweryClient breweryClient;

    @Test
    void getBeerByIdTest() {
        UUID uuid = UUID.randomUUID();
        BeerDto testBeer = breweryClient.getBeerById(uuid);
        assertEquals(uuid, testBeer.getId());
    }


    @Test
    void saveBeerTest() {
        BeerDto testBeer = BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName("testBeer")
                .build();

        URI savedBeerUri = breweryClient.saveBeer(testBeer);

        assertNotNull(savedBeerUri);
    }

    @Test
    void updateBeerTest() {
        BeerDto testBeer = BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName("testBeer")
                .build();

        breweryClient.updateBeer(testBeer);
    }

    @Test
    void deleteBeerTest() {
        breweryClient.deleteBeer(UUID.randomUUID());
    }

}