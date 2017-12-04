package toulouse.aoudia.legendary_crafter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import toulouse.aoudia.legendary_crafter.model.BasicItem;
import toulouse.aoudia.legendary_crafter.repository.ItemRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemService {

    @Autowired
    private ItemRepository repository;

    public List<BasicItem> findAllItem(){
        return repository.findAll();
    }
    public BasicItem findById(String id){
        return repository.findById(id);
    }
    public BasicItem createItem(){
        return null;
    }
    public void deleteById(BasicItem item){
        repository.delete(item);
    }
}
