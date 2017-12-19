package toulouse.aoudia.legendary_crafter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import toulouse.aoudia.legendary_crafter.model.BasicItem;
import toulouse.aoudia.legendary_crafter.model.Hero;
import toulouse.aoudia.legendary_crafter.service.HeroService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/hero")
public class ApiHeroController {

    @Autowired
    HeroService heroService;

    @RequestMapping("/")
    ResponseEntity<List<String>> listAllHero(Principal user){
        Set<Hero> heroes = heroService.findAllHero(user.getName());
        if (heroes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<String> names = new ArrayList<>();
        heroes.forEach(hero -> names.add(hero.getName()));
        return new ResponseEntity<>(names, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<Hero> getHero(@PathVariable("id") String id,Principal user){
        Hero hero = heroService.findById(id,user.getName());
        if (hero == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(hero, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createHero(@RequestBody Map<String, String> heroName, UriComponentsBuilder ucBuilder, Principal user) {
        String name = null;

        if (heroName.containsKey("name")){
            name = heroName.get("name");
        }else{
            System.err.println(String.format("No name was specified."));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        System.out.println(String.format("Creating Hero : %s", name));

        if (heroService.isHeroExist(name,user.getName())) {
            System.err.println(String.format("Unable to create. A Hero with name %s already exist", name));
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        heroService.saveHero(name,user.getName());

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/hero/{id}").buildAndExpand(name).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateHero(@PathVariable("id") String id, @RequestBody BasicItem item, Principal user) {
        System.out.println(String.format("Updating Hero %s with item %s", id, item));

        Hero currentHero = heroService.findById(id,user.getName());

        if (currentHero == null) {
            System.err.println(String.format("Unable to update. Hero with id %s not found.", id));
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        heroService.stuffHero(currentHero, item,user.getName());
        return new ResponseEntity<>(currentHero, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity removeItem(@PathVariable("id") String id, Principal user){
        Hero hero = heroService.findById(id,user.getName());
        if (hero == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        heroService.deleteById(id,user.getName());
        return new ResponseEntity(HttpStatus.OK);
    }
}
