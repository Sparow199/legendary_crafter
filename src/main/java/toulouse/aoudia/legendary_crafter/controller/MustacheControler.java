package toulouse.aoudia.legendary_crafter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import toulouse.aoudia.legendary_crafter.model.User;
import toulouse.aoudia.legendary_crafter.service.HeroService;
import toulouse.aoudia.legendary_crafter.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Map;

@Controller
public class MustacheControler {
    @Autowired
    HeroService heroService;
    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String indexPage(){
        return "index";
    }

    @RequestMapping(value = "/login")
    public String loginPage(){
        return "login";
    }

    @RequestMapping(value = "/user/create",method = RequestMethod.GET)
    public String userCreatPage(){
        return "create";
    }

    @RequestMapping(value = "/user/create",method = RequestMethod.POST)
    public String userCreatPost(@RequestParam String username, @RequestParam String password){
        User user = new User(username,password);
        userService.saveUser(user);
        return "create";
    }

    @RequestMapping(value = "/auth",method = RequestMethod.POST)
    public void login(@RequestParam Map<String, String> map, HttpServletRequest request, HttpServletResponse response){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());
    }



}
