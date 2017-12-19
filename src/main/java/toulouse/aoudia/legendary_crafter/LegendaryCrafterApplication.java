package toulouse.aoudia.legendary_crafter;

import com.sun.jna.platform.win32.OaIdl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import toulouse.aoudia.legendary_crafter.model.BasicItem;
import toulouse.aoudia.legendary_crafter.model.Hero;
import toulouse.aoudia.legendary_crafter.model.User;
import toulouse.aoudia.legendary_crafter.repository.ItemRepository;
import toulouse.aoudia.legendary_crafter.repository.UserRepository;
import toulouse.aoudia.legendary_crafter.service.ItemService;

import java.util.*;

@SpringBootApplication
@EnableMongoRepositories
@EnableScheduling
public class LegendaryCrafterApplication implements CommandLineRunner {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemService itemService;

	public static void main(String[] args) {
		SpringApplication.run(LegendaryCrafterApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {

        itemRepository.deleteAll();
        userRepository.deleteAll();

        // save a couple of BasicItems
        Map<Hero.Stats, Integer> stats = new HashMap<>();
        stats.put(Hero.Stats.Shield, 2);
        stats.put(Hero.Stats.Might, 1);
        itemRepository.save(new BasicItem("bouclier", 200, BasicItem.Rarity.Common, stats, Collections.singletonList(BasicItem.Slot.Left_Hand)));
        itemRepository.save(new BasicItem("bouclier", 200, BasicItem.Rarity.Legendary, stats, Collections.singletonList(BasicItem.Slot.Left_Hand)));

        itemRepository.save(new BasicItem("bouclier", 200, BasicItem.Rarity.Common, stats, Collections.singletonList(BasicItem.Slot.Right_Hand)));
        itemRepository.save(new BasicItem("bouclier", 200, BasicItem.Rarity.Legendary, stats, Collections.singletonList(BasicItem.Slot.Right_Hand)));

        stats = Collections.singletonMap(Hero.Stats.Might, 2);
        itemRepository.save(new BasicItem("épée", 100, BasicItem.Rarity.Common, stats, Collections.singletonList(BasicItem.Slot.Right_Hand)));
        itemRepository.save(new BasicItem("épée", 100, BasicItem.Rarity.Legendary, stats, Collections.singletonList(BasicItem.Slot.Right_Hand)));

        itemRepository.save(new BasicItem("épée", 100, BasicItem.Rarity.Common, stats,Collections.singletonList(BasicItem.Slot.Left_Hand)));
        itemRepository.save(new BasicItem("épée", 100, BasicItem.Rarity.Legendary, stats, Collections.singletonList(BasicItem.Slot.Left_Hand)));

        List<BasicItem.Slot> multiPart = new ArrayList<>();
        multiPart.add(BasicItem.Slot.Right_Hand);
        multiPart.add(BasicItem.Slot.Left_Hand);
        stats = Collections.singletonMap(Hero.Stats.Might, 4);
        itemRepository.save(new BasicItem("épée à deux main", 100, BasicItem.Rarity.Legendary, stats, multiPart));
        itemRepository.save(new BasicItem("épée à deux main", 100, BasicItem.Rarity.Common, stats, multiPart));

        itemRepository.save(new BasicItem("Hache de guerre", 200, BasicItem.Rarity.Legendary, stats, multiPart));
        itemRepository.save(new BasicItem("Hache de guerre", 200, BasicItem.Rarity.Common, stats, multiPart));

        stats = Collections.singletonMap(Hero.Stats.Shield, 1);
        itemRepository.save(new BasicItem("Casque", 100, BasicItem.Rarity.Common, stats, Collections.singletonList(BasicItem.Slot.Head)));
        itemRepository.save(new BasicItem("Casque", 100, BasicItem.Rarity.Legendary, stats, Collections.singletonList(BasicItem.Slot.Head)));

        stats = Collections.singletonMap(Hero.Stats.Shield, 1);
        itemRepository.save(new BasicItem("Bottes", 100, BasicItem.Rarity.Common, stats, Collections.singletonList(BasicItem.Slot.Foot)));
        itemRepository.save(new BasicItem("Bottes", 100, BasicItem.Rarity.Legendary, stats, Collections.singletonList(BasicItem.Slot.Foot)));


        stats = Collections.singletonMap(Hero.Stats.Shield, 2);
        itemRepository.save(new BasicItem("Plastron", 200, BasicItem.Rarity.Common, stats, Collections.singletonList(BasicItem.Slot.Body)));
        itemRepository.save(new BasicItem("Plastron", 200, BasicItem.Rarity.Legendary, stats, Collections.singletonList(BasicItem.Slot.Body)));


        multiPart = new ArrayList<>();
        multiPart.add(BasicItem.Slot.Head);
        multiPart.add(BasicItem.Slot.Body);
        multiPart.add(BasicItem.Slot.Foot);
        stats = Collections.singletonMap(Hero.Stats.Shield, 5);
        itemRepository.save(new BasicItem("Armure de plaque integral", 300, BasicItem.Rarity.Common, stats, multiPart));
        itemRepository.save(new BasicItem("Armure de plaque integral", 300, BasicItem.Rarity.Legendary, stats, multiPart));

        multiPart = new ArrayList<>();
        multiPart.add(BasicItem.Slot.Head);
        multiPart.add(BasicItem.Slot.Body);
        stats = Collections.singletonMap(Hero.Stats.Shield, 2);
        itemRepository.save(new BasicItem("Bure à capuche", 100, BasicItem.Rarity.Common, stats, multiPart));
        itemRepository.save(new BasicItem("Bure à capuche", 100, BasicItem.Rarity.Legendary, stats, multiPart));

        User user = new User("pierre", "password");
        userRepository.save(user);


        Hero hero = new Hero("conan le barbare");

        user.getItems().add(itemService.createItem("pierre"));
        user.getItems().add(itemService.createItem("pierre"));
        user.getItems().add(itemService.createItem("pierre"));
        user.getItems().add(itemService.createItem("pierre"));
        user.getItems().add(itemService.createItem("pierre"));
        hero.equipStuff(user,user.getItems().get(0));

        user.getHeroes().add(hero);
        userRepository.save(user);

    }
}
