package digitalinnovation.one.carapi.controller;


import digitalinnovation.one.carapi.dto.CarDTO;
import digitalinnovation.one.carapi.exception.CarNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Api("Manages car api")
public interface CarControllerDocs {

    @ApiOperation(value = "Car creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success car creation"),
            @ApiResponse(code = 400, message = "Missing required fields or wrong field range value.")
    })
    CarDTO createCar(CarDTO carDTO);

    @ApiOperation(value = "Returns a list of all cars registered in the system")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of all cars registered in the system")
    })
    List<CarDTO> listCars();

    @ApiOperation(value = "Update a car found by a given valid Id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success car updated"),
            @ApiResponse(code = 404, message = "Car not found")
    })
    void updateById(@PathVariable Long id, CarDTO carDTO ) throws CarNotFoundException;

    @ApiOperation(value = "Delete a car found by a given valid Id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success car deleted"),
            @ApiResponse(code = 404, message = "Car not found")
    })
    void delete(@PathVariable Long id) throws CarNotFoundException;

}
