package digitalinnovation.one.carapi.service;

import digitalinnovation.one.carapi.dto.CarDTO;
import digitalinnovation.one.carapi.entity.Car;
import digitalinnovation.one.carapi.exception.CarNotFoundException;
import digitalinnovation.one.carapi.mapper.CarMapper;
import digitalinnovation.one.carapi.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper = CarMapper.INSTANCE;

    public CarDTO createCar(CarDTO carDTO){
        Car car = carMapper.toModel(carDTO);
        Car savedCar = carRepository.save(car);
        return carMapper.toDTO(savedCar);
    }

    public List<CarDTO> listAll(){
        List<Car> allCars = carRepository.findAll();
        return allCars.stream()
                .map(carMapper::toDTO)
                .collect(Collectors.toList());
    }
    public CarDTO findById(Long id) throws CarNotFoundException {
        Car car = verifyIfExists(id);

        return carMapper.toDTO(car);
    }

    public void delete(Long id) throws CarNotFoundException {
        verifyIfExists(id);
        carRepository.deleteById(id);
    }
    public void updateById(Long id, CarDTO carDTO) throws CarNotFoundException {
        verifyIfExists(id);
        Car carToUpdate = carMapper.toModel(carDTO);

        carRepository.save(carToUpdate);
    }

    private Car verifyIfExists(Long id) throws CarNotFoundException{
        return carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));

    }


}
