package toulouse.aoudia.legendary_crafter.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {
    @Id
    private String id;

    private String name;

    private Set<Hero> heroes;

    private List<BasicItem> items;

    public User(){
        this.id = new ObjectId().toString();
        this.name = "";
        this.heroes = new HashSet<>();
        this.items = new ArrayList<>();
    }

    @JsonCreator
    public User(@JsonProperty("name")String name){
        this.id = new ObjectId().toString();
        this.name = name;
        this.heroes = new HashSet<>();
        this.items = new ArrayList<>();
    }

    public User(String name, Set<Hero> heroes, List<BasicItem> items) {
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

    public Set<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(Set<Hero> heroes) {
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
