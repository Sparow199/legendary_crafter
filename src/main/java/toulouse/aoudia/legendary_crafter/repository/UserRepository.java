package toulouse.aoudia.legendary_crafter.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import toulouse.aoudia.legendary_crafter.model.BasicItem;
import toulouse.aoudia.legendary_crafter.model.User;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    User findByName(String name);
    boolean existsByName(String name);
}
