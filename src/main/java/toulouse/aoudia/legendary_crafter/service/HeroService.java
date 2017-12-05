package toulouse.aoudia.legendary_crafter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import toulouse.aoudia.legendary_crafter.model.BasicItem;
import toulouse.aoudia.legendary_crafter.model.Hero;
import toulouse.aoudia.legendary_crafter.model.User;
import toulouse.aoudia.legendary_crafter.repository.HeroRepository;
import toulouse.aoudia.legendary_crafter.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class HeroService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HeroRepository heroRepository;

    public Set<Hero> findAllHero(){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByName(authentication.getName())
                .getHeroes();
    }
    public Hero findById(String id){
        return heroRepository.findByName(id);
    }
    public boolean isHeroExist(String id){
        // ToDo
        return false;
    }
    public void saveHero(String name){
        Hero hero = new Hero(name);
        saveHero(hero);
    }
    public void saveHero(Hero hero){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByName(authentication.getName());
        user.getHeroes().add(heroRepository.save(hero));
        userRepository.save(user);
    }
    public void stuffHero(Hero hero, BasicItem item){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByName(authentication.getName());
        user.getHeroes().remove(hero);
        user = hero.equipStuff(user, item);
        user.getHeroes().add(hero);
        userRepository.save(user);
    }
    public void deleteById(String id){
        // ToDo
    }


}
