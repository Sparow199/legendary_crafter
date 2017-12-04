package toulouse.aoudia.legendary_crafter.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import toulouse.aoudia.legendary_crafter.model.BasicItem;

import java.util.List;

public interface ItemRepository extends MongoRepository<BasicItem, String> {

    BasicItem findBasicItemByName(String name);
    List<BasicItem> findAllByName(String name);

}
