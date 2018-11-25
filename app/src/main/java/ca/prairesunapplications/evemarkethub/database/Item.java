package ca.prairesunapplications.evemarkethub.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "items",
        foreignKeys = @ForeignKey(entity = Group.class, parentColumns = "id", childColumns = "group_id"))

public class Item {

    @PrimaryKey
    public int id;


    public String name;
    public String description;
    public double price;
    public double average_price;

    public int group_id;



}
