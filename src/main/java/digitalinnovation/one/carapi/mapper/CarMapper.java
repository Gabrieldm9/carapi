package digitalinnovation.one.carapi.mapper;


import digitalinnovation.one.carapi.dto.CarDTO;
import digitalinnovation.one.carapi.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    @Mapping(target = "price", source = "price", numberFormat = "R$#,00")
    Car toModel(CarDTO carDTO);

    CarDTO toDTO(Car car);
}
