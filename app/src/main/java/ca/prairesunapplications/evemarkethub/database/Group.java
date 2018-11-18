package ca.prairesunapplications.evemarkethub.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "groups")
public class Group {

    @PrimaryKey
    public int id;
}
