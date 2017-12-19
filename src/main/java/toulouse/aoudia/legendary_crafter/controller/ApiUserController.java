package toulouse.aoudia.legendary_crafter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import toulouse.aoudia.legendary_crafter.config.WebSecurityCustomConfig;
import toulouse.aoudia.legendary_crafter.model.Hero;
import toulouse.aoudia.legendary_crafter.model.User;
import toulouse.aoudia.legendary_crafter.service.UserService;

import java.util.*;

@RestController
@RequestMapping("/api/user")
public class ApiUserController {

    @Autowired
    UserService userService;

    @RequestMapping("/")
    ResponseEntity<List<String>> listAllUser(){
        List<User> users = userService.findAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<String> userNames = new ArrayList<>();
        users.forEach(user -> userNames.add(user.getName()));
        return new ResponseEntity<>(userNames, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<User> getUser(@PathVariable("id") String id){
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        System.out.println(String.format("Creating User : %s", user));

        if (userService.isUserExist(user.getName())) {
            System.err.println(String.format("Unable to create. A User with name %s already exist", user.getName()));
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        userService.saveUser(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getName()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}/hero")
    public ResponseEntity<List<String>> listAllUserHeroes(@PathVariable("id") String id) {
        Set<Hero> heroes = userService.findAllHeroes(id);
        if (heroes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<String> heroNames = new ArrayList<>();
        heroes.forEach(hero -> heroNames.add(hero.getName()));
        return new ResponseEntity<>(heroNames, HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}/hero/{heroId}", method = RequestMethod.GET)
    ResponseEntity<Hero> getHero(@PathVariable("userId") String userId, @PathVariable("heroId") String heroId){
        Hero hero = userService.findHeroById(userId, heroId);
        if (hero == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(hero, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    ResponseEntity<?> createUser(@RequestBody Map<String, String> credentials, UriComponentsBuilder ucBuilder) throws Exception {
        String userName = null;
        String password = null;
        if(credentials.containsKey("username") && credentials.containsKey("password")) {
            userName = credentials.get("username");
            password = credentials.get("password");
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        System.out.println(String.format("Creating user : %s", userName));
        if(userService.isUserExist(userName)){
            System.err.println(String.format("Unable to create. A Hero with name %s already exist", userName));
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        userService.saveUser(new User(userName, password));
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(userName).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

}
