package cz.macha.spring.validation;

import cz.macha.spring.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlaceValidation {

    private final PlaceService placeService;
    private List<String> errors = new ArrayList<>();


    @Autowired
    public PlaceValidation(PlaceService placeService) {
        this.placeService = placeService;
    }

    public void validation(String name, String address){
        name(name);
        address(address);
    }

    private void name(String name){
        if(name.isEmpty()){
            errors.add("PLACE NAME IS REQUIRED");
        }
        else if(placeService.getPlaceByName(name) != null){
            errors.add("NAME ALREADY EXISTS");
        }
    }

    private void address(String address){
        if(address.isEmpty()){
            errors.add("ADDRESS IS REQUIRED");
        }
    }

    public List<String> getErrors() {
        return errors;
    }
}
