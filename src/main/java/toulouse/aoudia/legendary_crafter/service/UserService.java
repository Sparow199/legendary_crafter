package toulouse.aoudia.legendary_crafter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import toulouse.aoudia.legendary_crafter.model.Hero;
import toulouse.aoudia.legendary_crafter.model.User;
import toulouse.aoudia.legendary_crafter.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public User findById(String id){
        return userRepository.findByName(id);
    }

    public boolean isUserExist(String id){
        // ToDo
        return false;
    }

    public void saveUser(User user){
        // ToDo
    }

    public void updateUser(User user){
        // ToDo
    }

    public Set<Hero> findAllHeroes(String id){
        return userRepository.findByName(id).getHeroes();
    }

    public Hero findHeroById(String userId, String heroId){
        // ToDo
        return null;
    }
}
