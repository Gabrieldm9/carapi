package digitalinnovation.one.carapi.entity;

import digitalinnovation.one.carapi.enums.CarType;
import digitalinnovation.one.carapi.enums.FuelType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String brand;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CarType type;

    @Column(nullable = false)
    private String engine;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FuelType fuel;

    @Column
    private int mileage;

    @Column
    private int doorcount;

}
