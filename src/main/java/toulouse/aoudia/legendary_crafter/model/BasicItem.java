package toulouse.aoudia.legendary_crafter.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.bson.types.ObjectId;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.annotation.Id;
import org.springframework.data.web.JsonPath;

import java.util.List;
import java.util.Map;

public class BasicItem {

    public enum Rarity{
        Legendary,
        Common
    }

    public enum Slot{
        Head,
        Body,
        Foot,
        Right_Hand,
        Left_Hand
    }

    @Id
    private String id;
    private String name;
    private int durability;
    private Rarity rarity;
    private Map<Hero.Stats, Integer> stats;
    private List<Slot> slots;

    public BasicItem() {
        this.id = "";
    }

    @JsonCreator
    public BasicItem(
            @JsonProperty("id")String id,
            @JsonProperty("name")String name,
            @JsonProperty("durability")int durability,
            @JsonProperty("rarity")Rarity rarity,
            @JsonProperty("stats")Map<Hero.Stats, Integer> stats,
            @JsonProperty("slots")List<Slot> slots) {
        this.id = id;
        this.name = name;
        this.durability = durability;
        this.rarity = rarity;
        this.stats = stats;
        this.slots = slots;
    }

    public BasicItem(String name, int durability, Rarity rarity, Map<Hero.Stats, Integer> stats, List<Slot> slots) {
        this.id = new ObjectId().toString();
        this.name = name;
        this.durability = durability;
        this.rarity = rarity;
        this.stats = stats;
        this.slots = slots;
    }

    public String getId() {
        return id;
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

    public int setDurability(int durability) {
        this.durability = durability;
        return this.durability;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public Map<Hero.Stats, Integer> getStats() {
        return stats;
    }

    public void setStats(Map<Hero.Stats, Integer> stats) {
        this.stats = stats;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }

    @Override
    public String toString() {
        return "BasicItem{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", durability=" + durability +
                ", rarity=" + rarity +
                ", slots=" + slots +
                '}';
    }

    public void generateNewId(){
        this.id = new ObjectId().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasicItem)) return false;

        BasicItem basicItem = (BasicItem) o;

        return getId().equals(basicItem.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
