package cz.macha.spring.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MainController {

    @RequestMapping("/")
    public String gerMainPage(){
//        ModelAndView mv = new ModelAndView("index.jsp");
        return ("<h1>Welcome</h1>");
    }

    @RequestMapping("/user")
    public String getUserPage(){
//        ModelAndView mv = new ModelAndView("index.jsp");
        return ("<h1>Welcome User</h1>");
    }

    @RequestMapping("/admin")
    public String getAdminPage(){
//        ModelAndView mv = new ModelAndView("index.jsp");
        return ("<h1>Welcome Admin</h1>");
    }
}
