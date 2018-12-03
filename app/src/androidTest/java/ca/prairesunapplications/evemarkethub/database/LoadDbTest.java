package ca.prairesunapplications.evemarkethub.database;

import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class LoadDbTest {


    Database db;
    ItemDAO itemDAO;

    @Before
    public void setUp() throws IOException {
        db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),Database.class).build();
        itemDAO = db.itemDAO();
    }

    @Test
    public void writeItem(){
        DbItem item = new DbItem();
        item.setId(0);
        itemDAO.addItem(item);

        assertEquals(1,itemDAO.countItems());

        item.setDescription("This is a test item");

        itemDAO.updateItem(item);

        assertEquals(1,itemDAO.countItems());
    }

    @After
    public void tearDown() throws Exception {
        db.close();
    }
}