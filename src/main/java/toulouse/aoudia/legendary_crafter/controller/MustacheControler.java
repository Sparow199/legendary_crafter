package toulouse.aoudia.legendary_crafter.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class MustacheControler {
    @RequestMapping("/")
    public String indexPage(){
        return "index";
    }

    @RequestMapping(value = "/login")
    public String loginPage(){
        return "login";
    }

    @RequestMapping(value = "/auth",method = RequestMethod.POST)
    public void login(@RequestParam Map<String, String> map, HttpServletRequest request, HttpServletResponse response){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());
    }

    @RequestMapping(value = "/create")
    public String createPage(){
        return "create";
    }
}
