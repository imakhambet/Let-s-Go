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
            errors.add("Name is required");
        }
        else if(placeService.getPlaceByName(name) != null){
            errors.add("Name exists");
        }
    }

    private void address(String address){
        if(address.isEmpty()){
            errors.add("Address is required");
        }
    }

    public List<String> getErrors() {
        return errors;
    }
}
