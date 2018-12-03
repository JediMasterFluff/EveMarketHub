package ca.prairesunapplications.evemarkethub.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ItemDAO {

    @Query("SELECT * FROM items WHERE id = :itemId")
    DbItem getItemByID(int itemId);

    @Query("SELECT * FROM items")
    List<DbItem> getAllItems();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addItem(DbItem item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addItems(DbItem[] items);

    @Insert
    void insertAll(DbItem... items);

    @Query("SELECT COUNT(*) FROM items")
    int countItems();

    @Update
    void updateItem(DbItem item);

    @Delete
    void removeItem(DbItem item);
}
