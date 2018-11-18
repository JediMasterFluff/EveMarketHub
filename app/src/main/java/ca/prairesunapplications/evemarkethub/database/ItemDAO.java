package ca.prairesunapplications.evemarkethub.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

@Dao
public interface ItemDAO {

    @Query("SELECT * FROM items WHERE id = :itemId")
    int selectItemsByID(int itemId);


    @Query("SELECT * FROM items")
    Item[] loadAllItems();
}
