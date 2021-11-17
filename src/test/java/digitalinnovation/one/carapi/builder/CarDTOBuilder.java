package digitalinnovation.one.carapi.builder;

import digitalinnovation.one.carapi.dto.CarDTO;
import digitalinnovation.one.carapi.enums.CarType;
import digitalinnovation.one.carapi.enums.FuelType;
import lombok.Builder;



@Builder
public class CarDTOBuilder {

    @Builder.Default
    private Long id = 1l;

    @Builder.Default
    private Double price = 13500.00;

    @Builder.Default
    private String model = "XJS 82";

    @Builder.Default
    private String brand = "Jaguar";

    @Builder.Default
    private CarType type = CarType.COUPE;

    @Builder.Default
    private String engine = "3.0 V12";

    @Builder.Default
    private FuelType fuel = FuelType.GASOLINE;

    @Builder.Default
    private int mileage = 89000;

    @Builder.Default
    private int doorcount = 3;

    public CarDTO toCarDTO(){
        return new CarDTO(id, price, model, brand, type, engine, fuel, mileage, doorcount);
    }

}
