package evgenyt.springdemo;

import org.springframework.security.access.annotation.Secured;
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

    // Super secret section, only users with role "SUPER" can access it
    @RequestMapping(value = "superduper", method = RequestMethod.GET)
    @Secured("ROLE_SUPER")
    public String getSuperDuper() {
        return "superduper";
    }
}
