package evgenyt.springdemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Web app controller
 */

@Controller
public class WebApp {
    // Show home.jsp
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHome() {
        return "/";
    }
}
