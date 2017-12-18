package toulouse.aoudia.legendary_crafter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import toulouse.aoudia.legendary_crafter.model.BasicItem;
import toulouse.aoudia.legendary_crafter.model.Hero;
import toulouse.aoudia.legendary_crafter.model.User;
import toulouse.aoudia.legendary_crafter.service.HeroService;
import toulouse.aoudia.legendary_crafter.service.ItemService;
import toulouse.aoudia.legendary_crafter.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/app")
public class AppController {

    @Autowired
    UserService userService;
    @Autowired
    HeroService heroService;
    @Autowired
    ItemService itemService;

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

    @RequestMapping(value = "/hero/{id}", method = RequestMethod.GET)
    public String getHeroById(Model model, Principal user, @PathVariable String id){
        Hero hero = heroService.findById(id,user.getName());
        model.addAttribute("hero",hero);
        model.addAttribute("username",user.getName());
        model.addAttribute("slot", BasicItem.Slot.values());
        model.addAttribute("inventaire",userService.findById(user.getName()).getItems());
        return "hero";
    }

    @RequestMapping(value = "/list/", method = RequestMethod.GET)
    public String listAllHero(Model model, Principal user){
        List<User> userList = userService.findAllUsers();
        System.out.println(userList);
        model.addAttribute(userList);
        return "list";
    }

    @RequestMapping(value = "/inspection/{userName}/{heroName}",method = RequestMethod.GET)
    public String displayHero(Model model, Principal user, @PathVariable String userName, @PathVariable String heroName){
        Hero hero = heroService.findById(heroName,userName);
        model.addAttribute(userName);
        model.addAttribute(hero);
        return "inspection";
    }

    @RequestMapping(value = "/hero/{id}", method = RequestMethod.POST)
    public String equipHero(Model model, Principal user, @PathVariable String id,@RequestParam String itemId, @RequestParam String methode){
        if(methode.equals("equip")) {
            BasicItem basicItem = itemService.findById(itemId, user.getName());
            heroService.stuffHero(heroService.findById(id, user.getName()), basicItem, user.getName());
        }else if(methode.equals("delete")){
            BasicItem basicItem = itemService.findById(itemId, user.getName());
            itemService.deleteItem(basicItem,user.getName());
        }else if(methode.equals("create")){
            itemService.createItem(user.getName());
        }

        Hero hero = heroService.findById(id, user.getName());
        model.addAttribute("hero",hero);
        model.addAttribute("username",user.getName());
        model.addAttribute("slot", BasicItem.Slot.values());
        model.addAttribute("inventaire",userService.findById(user.getName()).getItems());
        return "hero";
    }

}
