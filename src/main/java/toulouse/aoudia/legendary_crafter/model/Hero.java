package toulouse.aoudia.legendary_crafter.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hero {
    @Id
    private String id;
    private String name;
    private Map<BasicItem.Slot, BasicItem> stuff;


    public Hero(String name) {
        this.id = new ObjectId().toString();
        this.name = name;
        this.stuff = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<BasicItem.Slot, BasicItem> getStuff() {
        return stuff;
    }

    public User equipStuff(User user, BasicItem item) {
        user.getItems().remove(item);
        for(BasicItem.Slot slot : item.getSlots()){
            if(this.stuff.containsKey(slot)){
                List<BasicItem> alreadyRemovedItem = new ArrayList<>();
                for (BasicItem.Slot slotToClean : stuff.get(slot).getSlots()) {
                    if(!alreadyRemovedItem.contains(this.stuff.get(slotToClean))){
                        alreadyRemovedItem.add(this.stuff.get(slotToClean));
                        user.getItems().add(this.stuff.get(slotToClean));
                    }
                    this.stuff.remove(slotToClean);
                }
            }
            this.stuff.put(slot, item);
        }
        return user;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", stuff=" + stuff +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hero)) return false;

        Hero hero = (Hero) o;

        return getName().equals(hero.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }
}
