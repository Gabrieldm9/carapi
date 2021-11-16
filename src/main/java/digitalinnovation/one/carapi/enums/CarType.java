package digitalinnovation.one.carapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CarType {

    HATCHBACK("Hatchback"),
    COUPE("Coupe"),
    SEDAN("Sedan"),
    SPORT("Sport"),
    SUV("SUV"),
    PICKUP("Pickup");

    private final String description;

}
