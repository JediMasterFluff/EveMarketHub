package objects;

/**
 * Created by fluffy on 13/11/17.
 */

public class Item {
    private int itemid;
    private String name;
    private String description;

    public Item() {
        this.itemid = 0;
        this.name = "default";
        this.description = "default description";
    }

    public Item(int itemid, String name, String description) {
        this.itemid = itemid;
        this.name = name;
        this.description = description;
    }

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
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
}
