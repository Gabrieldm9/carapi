package digitalinnovation.one.carapi.repository;

import digitalinnovation.one.carapi.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
