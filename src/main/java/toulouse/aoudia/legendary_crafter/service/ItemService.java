package toulouse.aoudia.legendary_crafter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import toulouse.aoudia.legendary_crafter.model.BasicItem;
import toulouse.aoudia.legendary_crafter.model.User;
import toulouse.aoudia.legendary_crafter.repository.ItemRepository;
import toulouse.aoudia.legendary_crafter.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;

    public List<BasicItem> findAllItem() {

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByName(authentication.getName()).getItems();
    }
    public BasicItem findById(String id){
        return itemRepository.findById(id);
    }
    public BasicItem createItem(){

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByName(authentication.getName());
        BasicItem item = itemRepository.findAll().stream().findAny().get();
        user.getItems().add(item);
        userRepository.save(user);
        return item;
    }
    public void deleteItem(BasicItem item){

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByName(authentication.getName());
        for(BasicItem myItem : new ArrayList<>(user.getItems())){
            if(myItem.getId().equals(item.getId())){
                user.getItems().remove(myItem);
            }
        }
        userRepository.save(user);
    }
}
