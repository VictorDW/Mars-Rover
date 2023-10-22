package com.rover.MarsRover.map.repository;

import com.rover.MarsRover.DTO.request.MapDataRequest;
import com.rover.MarsRover.DTO.response.MapDataResponse;
import com.rover.MarsRover.model.Map;
import com.rover.MarsRover.repository.MapRepository;
import com.rover.MarsRover.service.IMapNavigationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("tests")
class MapNavigationServiceImplTest {

    @Autowired
    private  MapRepository mapRepository;

    @Autowired
    private IMapNavigationService mapNavigationService;

    @Test
    @DisplayName("deberia permitir almacenar los datos del mapa en la BD")
    void crearMapa() {

        MapDataRequest  mapDataRequest = new MapDataRequest(20,20);

        Map mapa = new Map(1L,20,20,true);
        MapDataResponse mapDataResponse1 = new MapDataResponse(mapa);

        MapDataResponse mapDataResponse2 =mapNavigationService.createMap(mapDataRequest);

        assertThat(mapDataResponse2).isEqualTo(mapDataResponse1);
    }

}