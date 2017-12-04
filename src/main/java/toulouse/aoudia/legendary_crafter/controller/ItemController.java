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
import toulouse.aoudia.legendary_crafter.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    ItemService itemService;

    @RequestMapping("/")
    ResponseEntity<List<BasicItem>> listAllItems(){
        List<BasicItem> items = itemService.findAllItem();
        if (items.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<BasicItem>>(items, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<BasicItem> getItem(@PathVariable("id") String id){
        BasicItem item = itemService.findById(id);
        if (item != null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<BasicItem>(item, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(UriComponentsBuilder ucBuilder) {
        BasicItem item = itemService.createItem();
        System.out.println(String.format("Creating Item : %s", item));

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/item/{id}").buildAndExpand(item.getBasicItemId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity<BasicItem> removeItem(@PathVariable("id") String id){
        BasicItem item = itemService.findById(id);
        if (item != null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        itemService.deleteById(id);
        return new ResponseEntity<BasicItem>(HttpStatus.OK);
    }
}
