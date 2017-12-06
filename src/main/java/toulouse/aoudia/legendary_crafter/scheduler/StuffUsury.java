package toulouse.aoudia.legendary_crafter.scheduler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import toulouse.aoudia.legendary_crafter.model.BasicItem;
import toulouse.aoudia.legendary_crafter.model.Hero;
import toulouse.aoudia.legendary_crafter.model.User;
import toulouse.aoudia.legendary_crafter.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class StuffUsury {
    @Autowired
    UserRepository userRepository;

    @Scheduled(fixedRate = 1000)
    public void usury(){
        userRepository.findAll().stream()
                .forEach(this::use);
    }
    private void use(User user){
        user.getHeroes().stream()
                .forEach(this::use);
        userRepository.save(user);
    }
    private void use(Hero hero){
        List<BasicItem> alreadyUsed = new ArrayList<>();
        for (Object o : hero.getStuff().values().stream().distinct().toArray()) {
            BasicItem item = (BasicItem) o;
            item.setDurability(item.getDurability() - 1);
            if(item.getDurability() <= 0){
                System.out.println(hero.getName() + " lose his " + item.getName());
                for(BasicItem.Slot slot : new HashSet<>(hero.getStuff().keySet())){
                    if(hero.getStuff().get(slot).equals(item)){
                        hero.getStuff().remove(slot);
                    }
                }
            }
        }
    }
}
