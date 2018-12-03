package ca.prairesunapplications.evemarkethub.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "items")//,foreignKeys = @ForeignKey(entity = Group.class, parentColumns = "id", childColumns = "group_id"))

public class DbItem {

    @PrimaryKey
    private int id;


    private String name;
    private String description;
    private double price;
    private double average_price;

    private int group_id;

    public DbItem(int id, String name, String description, double price, double average_price, int group_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.average_price = average_price;
        this.group_id = group_id;
    }

    public DbItem(){}

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

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

    public double getAverage_price() {
        return average_price;
    }

    public void setAverage_price(double average_price) {
        this.average_price = average_price;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }
}
