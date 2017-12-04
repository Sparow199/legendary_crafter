package toulouse.aoudia.legendary_crafter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import toulouse.aoudia.legendary_crafter.model.BasicItem;
import toulouse.aoudia.legendary_crafter.repository.ItemRepository;

@SpringBootApplication
@EnableMongoRepositories
public class LegendaryCrafterApplication implements CommandLineRunner {

    @Autowired
    private ItemRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(LegendaryCrafterApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {

        repository.deleteAll();

        // save a couple of BasicItems
        repository.save(new BasicItem("épée", 100));
        repository.save(new BasicItem("bouclier", 200));
        repository.save(new BasicItem("bouclier", 110));

        // fetch all BasicItems
        System.out.println("BasicItems found with findAll():");
        System.out.println("-------------------------------");
        for (BasicItem BasicItem : repository.findAll()) {
            System.out.println(BasicItem);
        }
        System.out.println();

        // fetch an individual BasicItem
        System.out.println("BasicItem found with findByFirstName('épée'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByName("épée"));

        System.out.println("BasicItems found with findByLastName('bouclier'):");
        System.out.println("--------------------------------");
        for (BasicItem BasicItem : repository.findAllByName("bouclier")) {
            System.out.println(BasicItem);
        }

    }
}
