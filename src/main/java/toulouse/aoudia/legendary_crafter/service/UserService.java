package toulouse.aoudia.legendary_crafter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import toulouse.aoudia.legendary_crafter.model.Hero;
import toulouse.aoudia.legendary_crafter.model.User;
import toulouse.aoudia.legendary_crafter.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getActiveUser(){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByName(authentication.getName());
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public User findById(String id){
        return userRepository.findByName(id);
    }

    public boolean isUserExist(String id){
        return userRepository.findAll().stream().anyMatch(user -> user.getName().equals(id));
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public Set<Hero> findAllHeroes(String id){
        return userRepository.findByName(id).getHeroes();
    }

    public Hero findHeroById(String userId, String heroId){
        Optional<Hero> optional = userRepository.findByName(userId).getHeroes().stream().filter(hero -> hero.getName().equals(heroId)).findFirst();
        return optional.isPresent()?optional.get():null;
    }
}
