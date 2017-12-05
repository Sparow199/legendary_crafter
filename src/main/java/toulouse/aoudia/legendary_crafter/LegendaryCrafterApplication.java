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
        BasicItem basicItem1 = new BasicItem("bouclier", 200);
        BasicItem basicItem2 = new BasicItem("bouclier", 110);
        BasicItem basicItem3 = new BasicItem("epee", 100);

        basicItem1= itemRepository.save(basicItem1);
        basicItem2 = itemRepository.save(basicItem2);
        basicItem3 = itemRepository.save(basicItem3);

        List<BasicItem> pierreItems = new ArrayList<>();
        pierreItems.add(basicItem2);
        pierreItems.add(basicItem3);
        User pierre = new User("Pierre", new ArrayList<>(), pierreItems);
        userRepository.save(pierre);

        System.out.println("Server Running...");
    }
}
