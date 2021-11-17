package digitalinnovation.one.carapi.controller;


import digitalinnovation.one.carapi.dto.CarDTO;
import digitalinnovation.one.carapi.exception.CarNotFoundException;
import digitalinnovation.one.carapi.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cars")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CarController implements CarControllerDocs{

    private final CarService carService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarDTO createCar(@RequestBody @Valid CarDTO carDTO){

        return carService.createCar(carDTO);

    }

    @GetMapping
    public List<CarDTO> listCars() {
        return carService.listAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws CarNotFoundException {
        carService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateById(@PathVariable Long id,@RequestBody @Valid CarDTO carDTO) throws CarNotFoundException {
        carService.updateById(id, carDTO);
    }
}
