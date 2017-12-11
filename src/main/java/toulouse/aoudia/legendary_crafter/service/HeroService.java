package toulouse.aoudia.legendary_crafter.service;

import org.springframework.beans.factory.annotation.Autowired;
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

    public Set<Hero> findAllHero(String name){
        return userRepository.findByName(name)
                .getHeroes();
    }

    public Hero findById(String id,String name){
        Optional<Hero> optional = userRepository.findByName(name).getHeroes().stream()
                .filter(hero -> hero.getName().equals(id)).findFirst();
        return optional.orElse(null);
    }
    public boolean isHeroExist(String id,String name){
        return userRepository.findByName(name).getHeroes().stream()
                .anyMatch(hero -> hero.getName().equals(id));
    }
    public void saveHero(String HeroName, String name){
        Hero hero = new Hero(HeroName);
        saveHero(hero,name);
    }
    public void saveHero(Hero hero,String name){
        User user = userRepository.findByName(name);
        user.getHeroes().add(hero);
        userRepository.save(user);
    }
    public void stuffHero(Hero hero, BasicItem item, String name){
        User user = userRepository.findByName(name);
        user.getHeroes().remove(hero);
        user = hero.equipStuff(user, item);
        user.getHeroes().add(hero);
        userRepository.save(user);
    }
    public void deleteById(String id, String name){
        User user = userRepository.findByName(name);
        user.getHeroes().remove(findById(id,name));
        userRepository.save(user);
    }


}
