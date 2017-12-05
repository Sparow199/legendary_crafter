package toulouse.aoudia.legendary_crafter.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import toulouse.aoudia.legendary_crafter.model.Hero;
import toulouse.aoudia.legendary_crafter.model.User;

public interface HeroRepository extends MongoRepository<Hero, String> {
    Hero findByName(String name);
}
