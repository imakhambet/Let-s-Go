package cz.macha.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CourseApiApp {
    public static void main(String[] args) {
        SpringApplication.run(CourseApiApp.class, args);
    }
//    @EventListener(ApplicationReadyEvent.class)
//    private void testJpaMethods(){
//        Address address = new Address();
//        address.setCity("Kiev");
//        address.setHomeNumber("4");
//        address.setStreet("Main Street");
//        Address address1 = new Address();
//        address1.setCity("Lviv");
//
//    }
}
