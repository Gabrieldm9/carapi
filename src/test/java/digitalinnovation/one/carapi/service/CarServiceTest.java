package digitalinnovation.one.carapi.service;


import digitalinnovation.one.carapi.builder.CarDTOBuilder;
import digitalinnovation.one.carapi.dto.CarDTO;
import digitalinnovation.one.carapi.entity.Car;
import digitalinnovation.one.carapi.exception.CarNotFoundException;
import digitalinnovation.one.carapi.mapper.CarMapper;
import digitalinnovation.one.carapi.repository.CarRepository;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

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

        // when
        Mockito.when(carRepository.save(expectedSavedCar)).thenReturn(expectedSavedCar);

        // then
        CarDTO createdCarDTO = carService.createCar(expectedCarDTO);

        Assertions.assertEquals(expectedCarDTO.getId(), createdCarDTO.getId());
        Assertions.assertEquals(expectedCarDTO.getPrice(), createdCarDTO.getPrice());
        Assertions.assertEquals(expectedCarDTO.getModel(), createdCarDTO.getModel());
        Assertions.assertEquals(expectedCarDTO.getBrand(), createdCarDTO.getBrand());

    }
    @Test
    void whenNotRegisteredCarIdIsGivenThenThrowAnException(){
        // given
        CarDTO expectedFoundCarDTO = CarDTOBuilder.builder().build().toCarDTO();

        // when
        Mockito.when(carRepository.findById(expectedFoundCarDTO.getId())).thenReturn(Optional.empty());

        // then
        Assertions.assertThrows(CarNotFoundException.class, () -> carService.findById(expectedFoundCarDTO.getId()));


    }
    @Test
    void whenListCarIsCalledThenReturnAListOfCars(){
        // given
        CarDTO expectedFoundCarDTO = CarDTOBuilder.builder().build().toCarDTO();
        Car expectedFoundCar = carMapper.toModel(expectedFoundCarDTO);

        // when
        Mockito.when(carRepository.findAll()).thenReturn(Collections.singletonList(expectedFoundCar));

        //then
        List<CarDTO> foundListCarsDTO = carService.listAll();

        assertThat(foundListCarsDTO, is(not(empty())));
        assertThat(foundListCarsDTO.get(0), is(equalTo(expectedFoundCarDTO)));


    }
    @Test
    void whenListBeerIsCalledThenReturnAnEmptyListOfCars(){
        // when
        Mockito.when(carRepository.findAll()).thenReturn(Collections.EMPTY_LIST);

        // then
        List<CarDTO> foundListCarsDTO = carService.listAll();

        assertThat(foundListCarsDTO, is(empty()));

    }

    void whenExclusionIsCalledWithValidIdThenABeerShouldBeDeleted() throws CarNotFoundException {
        // given
        CarDTO expectedDeletedCarDTO = CarDTOBuilder.builder().build().toCarDTO();
        Car expectedDeletedCar = carMapper.toModel(expectedDeletedCarDTO);

        // when
        Mockito.when(carRepository.findById(expectedDeletedCarDTO.getId())).thenReturn(Optional.of(expectedDeletedCar));
        Mockito.doNothing().when(carRepository).deleteById(expectedDeletedCarDTO.getId());

        // then
        carService.delete(expectedDeletedCarDTO.getId());

        Mockito.verify(carRepository, Mockito.times(1)).findById(expectedDeletedCarDTO.getId());
        Mockito.verify(carRepository, Mockito.times(1)).deleteById(expectedDeletedCarDTO.getId());


    }




}
