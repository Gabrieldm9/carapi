package digitalinnovation.one.carapi.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FuelType {

    GASOLINE("Gasoline"),
    FLEX("Flex"),
    DIESEL("Diesel"),
    ELETRIC("Eletric"),
    HYBRID("Hybrid");


    private final String description;


}
