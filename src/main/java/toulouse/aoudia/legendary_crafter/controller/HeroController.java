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

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/hero")
public class HeroController {

    @Autowired
    HeroService heroService;

    @RequestMapping("/")
    ResponseEntity<Set<Hero>> listAllHero(){
        Set<Hero> heroes = heroService.findAllHero();
        if (heroes.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Set<Hero>>(heroes, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<Hero> getHero(@PathVariable("id") String id){
        Hero hero = heroService.findById(id);
        if (hero == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Hero>(hero, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createhero(@RequestBody String name, UriComponentsBuilder ucBuilder) {
        System.out.println(String.format("Creating Hero : %s", name));

        if (heroService.isHeroExist(name)) {
            System.err.println(String.format("Unable to create. A Hero with name %s already exist", name));
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        heroService.saveHero(name);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/hero/{id}").buildAndExpand(name).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateHero(@PathVariable("id") String id, @RequestBody BasicItem item) {
        System.out.println(String.format("Updating Hero %s with item %s", id, item));

        Hero currentHero = heroService.findById(id);

        if (currentHero == null) {
            System.err.println(String.format("Unable to update. Hero with id %s not found.", id));
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        heroService.stuffHero(currentHero, item);
        return new ResponseEntity<Hero>(currentHero, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity removeItem(@PathVariable("id") String id){
        Hero hero = heroService.findById(id);
        if (hero == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        heroService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
