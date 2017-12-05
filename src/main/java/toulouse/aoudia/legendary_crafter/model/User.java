package toulouse.aoudia.legendary_crafter.model;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.List;

public class User {
    @Id
    private String id;

    private String name;

    private List<Hero> heroes;

    private List<BasicItem> items;

    public User(String name, List<Hero> heroes, List<BasicItem> items) {
        this.id = new ObjectId().toString();
        this.name = name;
        this.heroes = heroes;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<Hero> heroes) {
        this.heroes = heroes;
    }

    public List<BasicItem> getItems() {
        return items;
    }

    public void setItems(List<BasicItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", heroes=" + heroes +
                ", items=" + items +
                '}';
    }
}
