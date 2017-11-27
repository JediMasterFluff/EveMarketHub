package ca.prairesunapplications.evemarkethub.objects;

import ca.prairesunapplications.evemarkethub.R;

/**
 * Created by fluffy on 13/11/17.
 */

@SuppressWarnings("DefaultFileTemplate")
public class Item {

    private int id;
    private String name;
    private String description;
    private String group_name;
    private String category_name;
    private double price;
    private double average_price;

    public Item() {
        this.id = 0;
        this.name = "default_item";
        this.description =  Integer.toString(R.string.item_details_descrip_default);
        this.group_name = "no group";
        this.category_name = "no category";
        this.price = 0.0;
        this.average_price = 0.0;
    }

    public Item(int id, String name,String category, double price) {
        this.id = id;
        this.name = name;
        this.description = "default";
        this.category_name = category;
        this.group_name = "no group";
        this.price = price;
        this.average_price = 0.0;
    }

    public Item(int id, String name, String description, String group_name, double price, double average_price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.group_name = group_name;
        this.category_name = "no category";
        this.price = price;
        this.average_price = average_price;
    }

    public Item(int id, String name, String description, String group_name, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.group_name = group_name;
        this.category_name = "no category";
        this.price = price;
        this.average_price = 0.0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
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

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
