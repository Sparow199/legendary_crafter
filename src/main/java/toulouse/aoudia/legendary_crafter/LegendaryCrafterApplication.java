package toulouse.aoudia.legendary_crafter;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LegendaryCrafterApplication {

	public static void main(String[] args) {
		SpringApplication.run(LegendaryCrafterApplication.class, args);
		MongoClientURI uri = new MongoClientURI("mongodb://legendary_admin:<PASSWORD>@legendarycrafterdb-shard-00-00-dmwgc.mongodb.net:27017,legendarycrafterdb-shard-00-01-dmwgc.mongodb.net:27017,legendarycrafterdb-shard-00-02-dmwgc.mongodb.net:27017/test?ssl=true&replicaSet=legendaryCrafterDb-shard-0&authSource=admin");
		MongoClient mongoClient = new MongoClient(uri);
		MongoDatabase database = mongoClient.getDatabase("test");
	}
}
