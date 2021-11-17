package digitalinnovation.one.carapi.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CarNotFoundException extends Exception{

    public CarNotFoundException(Long id){
        super(String.format("Car with id %s not found in the system.", id));
    }
}
