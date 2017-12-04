package toulouse.aoudia.legendary_crafter;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class LegendaryCrafterApplication {

	public static void main(String[] args) {
		SpringApplication.run(LegendaryCrafterApplication.class, args);
	}
}
