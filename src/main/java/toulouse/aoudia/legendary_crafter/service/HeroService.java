package toulouse.aoudia.legendary_crafter.service;

import org.springframework.stereotype.Component;
import toulouse.aoudia.legendary_crafter.model.Hero;

import java.util.ArrayList;
import java.util.List;

@Component
public class HeroService {
    public List<Hero> findAllHero(){
        // ToDo
        return new ArrayList<>();
    }
    public Hero findById(String id){
        // ToDo
        return null;
    }
    public boolean isHeroExist(String id){
        // ToDo
        return false;
    }
    public void saveHero(Hero hero){
        // ToDo
    }
    public void updatehero(Hero hero){
        // ToDo
    }
    public void deleteById(String id){
        // ToDo
    }
}
