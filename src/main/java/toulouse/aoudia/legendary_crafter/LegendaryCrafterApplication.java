package toulouse.aoudia.legendary_crafter;

import com.sun.jna.platform.win32.OaIdl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import toulouse.aoudia.legendary_crafter.model.BasicItem;
import toulouse.aoudia.legendary_crafter.model.User;
import toulouse.aoudia.legendary_crafter.repository.ItemRepository;
import toulouse.aoudia.legendary_crafter.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@SpringBootApplication
@EnableMongoRepositories
@EnableScheduling
public class LegendaryCrafterApplication implements CommandLineRunner {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(LegendaryCrafterApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {

        itemRepository.deleteAll();
        userRepository.deleteAll();

        // save a couple of BasicItems
        itemRepository.save(new BasicItem("bouclier", 200, BasicItem.Rarity.Common, Collections.singletonList(BasicItem.Slot.Left_Hand)));
        itemRepository.save(new BasicItem("bouclier", 200, BasicItem.Rarity.Legendary, Collections.singletonList(BasicItem.Slot.Left_Hand)));

        itemRepository.save(new BasicItem("bouclier", 200, BasicItem.Rarity.Common, Collections.singletonList(BasicItem.Slot.Right_Hand)));
        itemRepository.save(new BasicItem("bouclier", 200, BasicItem.Rarity.Legendary, Collections.singletonList(BasicItem.Slot.Right_Hand)));

        itemRepository.save(new BasicItem("épée", 100, BasicItem.Rarity.Common, Collections.singletonList(BasicItem.Slot.Right_Hand)));
        itemRepository.save(new BasicItem("épée", 100, BasicItem.Rarity.Legendary, Collections.singletonList(BasicItem.Slot.Right_Hand)));

        itemRepository.save(new BasicItem("épée", 100, BasicItem.Rarity.Common, Collections.singletonList(BasicItem.Slot.Left_Hand)));
        itemRepository.save(new BasicItem("épée", 100, BasicItem.Rarity.Legendary, Collections.singletonList(BasicItem.Slot.Left_Hand)));

        List<BasicItem.Slot> towHanded = new ArrayList<>();
        towHanded.add(BasicItem.Slot.Right_Hand);
        towHanded.add(BasicItem.Slot.Left_Hand);
        itemRepository.save(new BasicItem("épée à deux main", 100, BasicItem.Rarity.Legendary, towHanded));
        itemRepository.save(new BasicItem("épée à deux main", 100, BasicItem.Rarity.Common, towHanded));

        itemRepository.save(new BasicItem("Hache de guerre", 200, BasicItem.Rarity.Legendary, towHanded));
        itemRepository.save(new BasicItem("Hache de guerre", 200, BasicItem.Rarity.Common, towHanded));

        User pierre = new User("Pierre");
        userRepository.save(pierre);
    }
}
