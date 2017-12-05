package toulouse.aoudia.legendary_crafter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import toulouse.aoudia.legendary_crafter.model.BasicItem;
import toulouse.aoudia.legendary_crafter.repository.ItemRepository;
import toulouse.aoudia.legendary_crafter.repository.UserRepository;

import java.util.List;

@Component
public class ItemService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;

    public List<BasicItem> findAllItem() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return userRepository.findByName(authentication.getName()).getItems();
    }
    public BasicItem findById(String id){
        return itemRepository.findById(id);
    }
    public BasicItem createItem(){
        return null;
    }
    public void deleteById(BasicItem item){
        itemRepository.delete(item);
    }
}
