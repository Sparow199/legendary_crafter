package toulouse.aoudia.legendary_crafter.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class BasicItem {

    @Id
    public String basicItemId;

    public String name;
    public String durability;

    public BasicItem(String name, String durability) {
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
