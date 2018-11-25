package ca.prairesunapplications.evemarkethub.database;


import android.arch.persistence.room.RoomDatabase;

@android.arch.persistence.room.Database(version = 1, entities = {Item.class})
public abstract class Database extends RoomDatabase {

    abstract public ItemDAO itemDAO();
}
