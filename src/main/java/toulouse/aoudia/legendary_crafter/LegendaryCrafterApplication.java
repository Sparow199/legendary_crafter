package toulouse.aoudia.legendary_crafter;

import com.sun.jna.platform.win32.OaIdl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
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
        BasicItem basicItem1 = new BasicItem("bouclier", 200, BasicItem.Rarity.Common, Collections.singletonList(BasicItem.Slot.Left_Hand));
        BasicItem basicItem2 = new BasicItem("bouclier", 110, BasicItem.Rarity.Legendary, Collections.singletonList(BasicItem.Slot.Left_Hand));
        BasicItem basicItem3 = new BasicItem("épée", 100, BasicItem.Rarity.Common, Collections.singletonList(BasicItem.Slot.Right_Hand));
        List<BasicItem.Slot> towHanded = new ArrayList<>();
        towHanded.add(BasicItem.Slot.Right_Hand);
        towHanded.add(BasicItem.Slot.Left_Hand);
        BasicItem basicItem4 = new BasicItem("épée à deux main", 100, BasicItem.Rarity.Legendary, towHanded);

        basicItem1= itemRepository.save(basicItem1);
        basicItem2 = itemRepository.save(basicItem2);
        basicItem3 = itemRepository.save(basicItem3);
        basicItem4 = itemRepository.save(basicItem4);

        List<BasicItem> pierreItems = new ArrayList<>();
        pierreItems.add(basicItem2);
        pierreItems.add(basicItem3);
        User pierre = new User("Pierre", new HashSet<>(), pierreItems);
        userRepository.save(pierre);

        System.out.println("Server Running...");
    }
}
