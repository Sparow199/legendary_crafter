package toulouse.aoudia.legendary_crafter.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class BasicItem {

    @Id
    private String id;

    private String name;
    private int durability;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public BasicItem(String name, int durability) {
        this.name = name;
        this.durability = durability;
        id = new ObjectId().toString();
    }

    @Override
    public String toString() {
        return String.format(
            "BasicItem[id=%s, name='%s', durability='%s']",
                id, name, durability);
    }

}
