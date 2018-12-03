package ca.prairesunapplications.evemarkethub.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.concurrent.Executors;

@android.arch.persistence.room.Database(version = 1, entities = {DbItem.class}, exportSchema = false)
public abstract class Database extends RoomDatabase {

    private static Database INSTANCE;

    public abstract ItemDAO itemDAO();

    public synchronized static Database getInstance(Context context){
        if(INSTANCE == null)
            INSTANCE = buildDatabase(context);

        return INSTANCE;
    }

    private static Database buildDatabase(final Context context) {
        return Room.databaseBuilder(context,
                Database.class,
                "EveMarketDB"
                ).addCallback(new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                    @Override
                    public void run() {

                        getInstance(context).itemDAO().insertAll();
                    }
                });
            }
        }).build();
    }

}
