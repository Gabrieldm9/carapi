package digitalinnovation.one.carapi.dto;


import digitalinnovation.one.carapi.enums.CarType;
import digitalinnovation.one.carapi.enums.FuelType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {

    private Long id;

    @NotNull
    private Double price;

    @NotNull
    @Size(min = 2, max = 100)
    private String model;

    @NotNull
    @Size(min = 2, max = 100)
    private String brand;

    @Enumerated(EnumType.STRING)
    @NotNull
    private CarType type;

    @NotNull
    @Size(min = 2, max = 100)
    private String engine;

    @Enumerated(EnumType.STRING)
    @NotNull
    private FuelType fuel;


    private int mileage;


    private int doorcount;


}
