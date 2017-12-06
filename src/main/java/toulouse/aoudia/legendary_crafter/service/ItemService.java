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
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class ItemService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserService userService;

    public List<BasicItem> findAllItem() {
        return userService.getActiveUser().getItems();
    }
    public BasicItem findById(String id){
        Optional<BasicItem> optional = userService.getActiveUser().getItems().stream().filter(item -> item.getId().equals(id)).findFirst();
        return optional.isPresent()?optional.get():null;
    }
    public BasicItem createItem(){
        User user = userService.getActiveUser();
        List<BasicItem> items = itemRepository.findAll();
        Collections.shuffle(items);
        BasicItem item = items.stream().findAny().get();
        item.generateNewId();
        user.getItems().add(item);
        userRepository.save(user);
        return item;
    }
    public void deleteItem(BasicItem item){
        User user = userService.getActiveUser();
        user.getItems().remove(item);
        userRepository.save(user);
    }
}
