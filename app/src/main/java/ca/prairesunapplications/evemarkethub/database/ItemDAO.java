package ca.prairesunapplications.evemarkethub.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

@Dao
public interface ItemDAO {

    @Query("SELECT * FROM items WHERE id = :itemId")
    int selectItemsByID(int itemId);


    @Query("SELECT * FROM items")
    Item[] loadAllItems();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addItem(Item item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addItems(Item[] items);
}
