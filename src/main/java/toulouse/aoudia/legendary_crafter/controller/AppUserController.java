package toulouse.aoudia.legendary_crafter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import toulouse.aoudia.legendary_crafter.model.Hero;
import toulouse.aoudia.legendary_crafter.repository.UserRepository;
import toulouse.aoudia.legendary_crafter.service.HeroService;
import toulouse.aoudia.legendary_crafter.service.UserService;

import java.security.Principal;
import java.util.Set;

@Controller
@RequestMapping("/app")
public class AppUserController {

    @Autowired
    UserService userService;
    @Autowired
    HeroService heroService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String User(Principal user, Model model){
        Set<Hero> heroes = userService.findAllHeroes(user.getName());
        model.addAttribute("heroes",heroes);
        model.addAttribute("name",user.getName());
        return "user";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String createHero(@RequestParam String heroName, Principal user, Model model){
        heroService.saveHero(heroName,user.getName());
        Set<Hero> heroes = userService.findAllHeroes(user.getName());
        model.addAttribute("heroes",heroes);
        model.addAttribute("name",user.getName());
        return "user";
    }

}
