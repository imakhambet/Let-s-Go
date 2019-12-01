//package cz.macha.spring.model;
//
//import org.junit.Assert;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Random;
//
//import static org.junit.Assert.*;
//
//public class OrganizerTest {
//
//    @Test
//    public void createNewOrganizer(){
//        String login = "earOrganizer";
//        String password = String.valueOf(new Random().nextInt(9999)+1000);
//        String email = "imakha@gmail.com";
//        String phone = "777 777 777";
//        Organizer organizer = new Organizer(login, password, email, phone);
//
//        assertEquals(organizer.getLogin(), login);
//        assertEquals(organizer.getPassword(), password);
//        assertEquals(organizer.getEmail(), email);
//        assertEquals(organizer.getPhone(), phone);
//    }
//
//    @Test
//    public void createNewEventsForOrganizer(){
//
//        Random r = new Random();
//
//        String orgLogin = "earorganizer";
//        String orgPassword = String.valueOf(r.nextInt(9999)+1000);
//        String orgEemail = "imakha@gmail.com";
//        String orgPhone = "777 777 777";
//        Organizer organizer = new Organizer(orgLogin, orgPassword, orgEemail, orgPhone);
//
//        String adminLogin = "earadmin";
//        String adminPassword = String.valueOf(r.nextInt(9999)+1000);
//        Admin admin = new Admin(adminLogin, adminPassword);
//
//        String categoryName = "concerts";
//        Category category = new Category(categoryName, admin);
//
//        String placeName = "place" + r.nextInt(10);
//        String placeAddress = "Address" + r.nextInt(10);
//        Place place = new Place(placeName, admin, placeAddress);
//        Place place2 = new Place(placeName, admin, placeAddress);
//
//        String eventName = "Hockey" + r.nextInt(10);
//        String eventDescr = "Hockey in" + placeName;
//        String date = new Random().nextInt(30) + r.nextInt(30)
//                + "2020";
//
//        organizer.addEvent(new Event(organizer, category, place,  eventName, eventDescr, date));
//        organizer.addEvent(new Event(organizer, category, place2,  eventName, eventDescr, date));
//
//        assertEquals(2, organizer.getEvents().size());
//    }
//
//
//}