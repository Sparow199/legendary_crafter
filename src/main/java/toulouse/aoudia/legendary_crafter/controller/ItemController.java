package toulouse.aoudia.legendary_crafter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import toulouse.aoudia.legendary_crafter.model.BasicItem;
import toulouse.aoudia.legendary_crafter.service.ItemService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    ItemService itemService;

    @RequestMapping("/")
    ResponseEntity<List<String>> listAllItems(Principal user){
        List<BasicItem> items = itemService.findAllItem(user.getName());
        if (items.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        List<String> itemsNames = new ArrayList<>();
        items.stream().forEach(item -> itemsNames.add(item.getId()));
        return new ResponseEntity<List<String>>(itemsNames, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<BasicItem> getItem(@PathVariable("id") String id, Principal user){
        BasicItem item = itemService.findById(id,user.getName());
        if (item == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<BasicItem>(item, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<?> createItem(UriComponentsBuilder ucBuilder, Principal user) {
        BasicItem item = itemService.createItem(user.getName());
        System.out.println(String.format("Creating Item : %s", item));

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/item/{id}").buildAndExpand(item.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity removeItem(@PathVariable("id") String id, Principal user){
        BasicItem item = itemService.findById(id,user.getName());
        if (item == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        itemService.deleteItem(item,user.getName());
        return new ResponseEntity(HttpStatus.OK);
    }
}
