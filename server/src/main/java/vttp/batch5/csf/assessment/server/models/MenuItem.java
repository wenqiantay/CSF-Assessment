package vttp.batch5.csf.assessment.server.models;

import org.springframework.data.annotation.Id;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class MenuItem {

    @Id
    private String menuId;
    private String name;
    private String description;
    private double price;

    public String getMenuId() {
        return menuId;
    }
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    @Override
    public String toString() {
        return "Menu [menuId=" + menuId + ", name=" + name + ", description=" + description + ", price=" + price + "]";
    }

    public JsonObject toJson() {

        JsonObjectBuilder builder = Json.createObjectBuilder()
                                    .add("id", menuId)
                                    .add("name", name)
                                    .add("description", description)
                                    .add("price", price);
        return builder.build();
    }
    
}
