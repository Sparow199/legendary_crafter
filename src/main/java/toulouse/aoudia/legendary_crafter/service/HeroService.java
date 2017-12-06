package toulouse.aoudia.legendary_crafter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import toulouse.aoudia.legendary_crafter.model.BasicItem;
import toulouse.aoudia.legendary_crafter.model.Hero;
import toulouse.aoudia.legendary_crafter.model.User;
import toulouse.aoudia.legendary_crafter.repository.UserRepository;

import java.util.Optional;
import java.util.Set;

@Component
public class HeroService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    public Set<Hero> findAllHero(){
        return userService.getActiveUser()
                .getHeroes();
    }
    public Hero findById(String id){
        Optional<Hero> optional = userService.getActiveUser().getHeroes().stream()
                .filter(hero -> hero.getName().equals(id)).findFirst();
        return optional.isPresent()?optional.get():null;
    }
    public boolean isHeroExist(String id){
        return userService.getActiveUser().getHeroes().stream()
                .anyMatch(hero -> hero.getName().equals(id));
    }
    public void saveHero(String name){
        Hero hero = new Hero(name);
        saveHero(hero);
    }
    public void saveHero(Hero hero){
        User user = userService.getActiveUser();
        user.getHeroes().add(hero);
        userRepository.save(user);
    }
    public void stuffHero(Hero hero, BasicItem item){
        User user = userService.getActiveUser();
        user.getHeroes().remove(hero);
        user = hero.equipStuff(user, item);
        user.getHeroes().add(hero);
        userRepository.save(user);
    }
    public void deleteById(String id){
        User user = userService.getActiveUser();
        user.getHeroes().remove(findById(id));
        userRepository.save(user);
    }


}
