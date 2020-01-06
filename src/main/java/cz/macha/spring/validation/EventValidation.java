package cz.macha.spring.validation;

import cz.macha.spring.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class EventValidation {

    private final PlaceService placeService;

    private List<String> errors = new ArrayList<>();

    @Autowired
    public EventValidation( PlaceService placeService){
        this.placeService = placeService;
    }

    public void validation(String name, String description, String date, String place){
        name(name);
        description(description);
        date(date);
        place(place);
    }

    private void name(String name){
        if(name.isEmpty()){
            errors.add("event name is required");
        }
    }

    private void description(String description){
        if(description.isEmpty()){
            errors.add("description is required");
        }
    }

    private void date(String date){
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        if(date.length() != 10){
            errors.add("date is not correct");
        }else {
            try {
                format.parse(date);
            }
            catch(ParseException e){
                errors.add("date is not correct");
            }
        }
    }

    private void place(String place){
        if(place.isEmpty()){
            errors.add("Event place is required");
        } else if(placeService.getPlaceByName(place) == null){
            errors.add("place doesnt exist");
        }
    }

    public List<String> getErrors() {
        return errors;
    }
}
