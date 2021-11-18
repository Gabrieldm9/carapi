package digitalinnovation.one.carapi.controller;


import digitalinnovation.one.carapi.builder.CarDTOBuilder;
import digitalinnovation.one.carapi.dto.CarDTO;
import digitalinnovation.one.carapi.exception.CarNotFoundException;
import digitalinnovation.one.carapi.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Collections;


import static digitalinnovation.one.carapi.utils.JsonConvertionUtils.asJsonString;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CarControllerTest {

    private static final String CAR_API_URL_PATH = "/api/v1/cars";
    private static final long INVALID_CAR_ID = 1l;

    private MockMvc mockMvc;

    @Mock
    private CarService carService;

    @InjectMocks
    private CarController carController;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(carController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();

    }
    @Test
    void whenPOSTIsCalledThenACarIsCreated() throws Exception{
        // given
        CarDTO carDTO = CarDTOBuilder.builder().build().toCarDTO();

        // when
        when(carService.createCar(carDTO)).thenReturn(carDTO);

        // then
        mockMvc.perform(post(CAR_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(carDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.price", is(carDTO.getPrice())))
                .andExpect(jsonPath("$.model", is(carDTO.getModel())))
                .andExpect(jsonPath("$.type", is(carDTO.getType().toString())))
                .andExpect(jsonPath("$.engine", is(carDTO.getEngine())))
                .andExpect(jsonPath("$.fuel", is(carDTO.getFuel().toString())))
                .andExpect(jsonPath("$.mileage", is(carDTO.getMileage())))
                .andExpect(jsonPath("$.doorcount", is(carDTO.getDoorcount())));

    }
    @Test
    void whenPOSTIsCalledWithoutRequiredFieldThenAnErrorIsReturned() throws Exception {
        // given
        CarDTO carDTO = CarDTOBuilder.builder().build().toCarDTO();
        carDTO.setBrand(null);

        // then
        mockMvc.perform(post(CAR_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(carDTO)))
                .andExpect(status().isBadRequest());



    }
 @Test
    void whenGETIsCalledWithValidIdThenOkStatusIsReturned() throws Exception {
        // given
        CarDTO carDTO = CarDTOBuilder.builder().build().toCarDTO();

        //when
        when(carService.findById(carDTO.getId())).thenReturn(carDTO);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(CAR_API_URL_PATH + "/" + carDTO.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price", is(carDTO.getPrice())))
                .andExpect(jsonPath("$.model", is(carDTO.getModel())))
                .andExpect(jsonPath("$.type", is(carDTO.getType().toString())))
                .andExpect(jsonPath("$.engine", is(carDTO.getEngine())))
                .andExpect(jsonPath("$.fuel", is(carDTO.getFuel().toString())))
                .andExpect(jsonPath("$.mileage", is(carDTO.getMileage())))
                .andExpect(jsonPath("$.doorcount", is(carDTO.getDoorcount())));
    }

    @Test
    void whenGETIsCalledWithoutRegisteredIdThenNotFoundStatusIsReturned() throws Exception {
        // given
        CarDTO carDTO = CarDTOBuilder.builder().build().toCarDTO();

        //when
        when(carService.findById(carDTO.getId())).thenThrow(CarNotFoundException.class);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(CAR_API_URL_PATH + "/" + carDTO.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenGETListWithCarsIsCalledThenOkStatusIsReturned() throws Exception {
        // given
        CarDTO carDTO = CarDTOBuilder.builder().build().toCarDTO();

        //when
        when(carService.listAll()).thenReturn(Collections.singletonList(carDTO));

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(CAR_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].price", is(carDTO.getPrice())))
                .andExpect(jsonPath("$[0].model", is(carDTO.getModel())))
                .andExpect(jsonPath("$[0].type", is(carDTO.getType().toString())))
                .andExpect(jsonPath("$[0].engine", is(carDTO.getEngine())))
                .andExpect(jsonPath("$[0].fuel", is(carDTO.getFuel().toString())))
                .andExpect(jsonPath("$[0].mileage", is(carDTO.getMileage())))
                .andExpect(jsonPath("$[0].doorcount", is(carDTO.getDoorcount())));
    }

    @Test
    void whenGETListWithoutBeersIsCalledThenOkStatusIsReturned() throws Exception {
        // given
        CarDTO carDTO = CarDTOBuilder.builder().build().toCarDTO();

        //when
        when(carService.listAll()).thenReturn(Collections.singletonList(carDTO));

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(CAR_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void whenDELETEIsCalledWithValidIdThenNoContentStatusIsReturned() throws Exception {
        // given
        CarDTO carDTO = CarDTOBuilder.builder().build().toCarDTO();

        //when
        doNothing().when(carService).delete(carDTO.getId());

        // then
        mockMvc.perform(MockMvcRequestBuilders.delete(CAR_API_URL_PATH + "/" + carDTO.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void whenDELETEIsCalledWithInvalidIdThenNotFoundStatusIsReturned() throws Exception {
        //when
        doThrow(CarNotFoundException.class).when(carService).delete(INVALID_CAR_ID);

        // then
        mockMvc.perform(MockMvcRequestBuilders.delete(CAR_API_URL_PATH + "/" + INVALID_CAR_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
