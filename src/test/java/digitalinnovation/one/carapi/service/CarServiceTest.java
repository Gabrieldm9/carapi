package digitalinnovation.one.carapi.service;


import digitalinnovation.one.carapi.builder.CarDTOBuilder;
import digitalinnovation.one.carapi.dto.CarDTO;
import digitalinnovation.one.carapi.entity.Car;
import digitalinnovation.one.carapi.mapper.CarMapper;
import digitalinnovation.one.carapi.repository.CarRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    private static final long INVALID_CAR_ID = 2L;

    @Mock
    private CarRepository carRepository;

    private CarMapper carMapper = CarMapper.INSTANCE;

    @InjectMocks
    private CarService carService;

    @Test
    void whenCarInformedThenItShouldBeCreated(){
        // given
        CarDTO expectedCarDTO = CarDTOBuilder.builder().build().toCarDTO();
        Car expectedSavedCar = carMapper.toModel(expectedCarDTO);

        //when
        Mockito.when(carRepository.save(expectedSavedCar)).thenReturn(expectedSavedCar);

        //then
        CarDTO createdCarDTO = carService.createCar(expectedCarDTO);

        Assertions.assertEquals(expectedCarDTO.getId(), createdCarDTO.getId());
        Assertions.assertEquals(expectedCarDTO.getPrice(), createdCarDTO.getPrice());
        Assertions.assertEquals(expectedCarDTO.getModel(), createdCarDTO.getModel());
        Assertions.assertEquals(expectedCarDTO.getBrand(), createdCarDTO.getBrand());

    }

}
