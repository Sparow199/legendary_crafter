package toulouse.aoudia.legendary_crafter.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class BasicItem {

    @Id
    private String basicItemId;

    private String name;
    private int durability;

    public String getBasicItemId() {
        return basicItemId;
    }

    public void setBasicItemId(String basicItemId) {
        this.basicItemId = basicItemId;
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
        basicItemId = new ObjectId().toString();
    }

    @Override
    public String toString() {
        return String.format(
            "BasicItem[id=%s, name='%s', durability='%s']",
            basicItemId, name, durability);
    }

}
