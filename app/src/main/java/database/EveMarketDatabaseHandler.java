package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by fluffy on 16/11/17.
 * Database helper class to handle initial database creation and load
 */

public class EveMarketDatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "evemarket";
    //Table Names
    private static final String TABLE_ITEMS = "items";
    private static final String TABLE_GROUPS = "groups";
    private static final String TABLE_CATEGORIES = "categories";
    private static final String TABLE_MARKET_PRICE = "market_price";
    private static final String TABLE_PRICING_HISTORY = "pricing_history";
    private static final String TABLE_GROUP_ITEMS = "group_items";
    private static final String TABLE_CATEGORY_GROUPS = "category_groups";
    //Item Table Column Names
    private static final String ITEMS_KEY_ID = "type_id";
    private static final String ITEMS_KEY_NAME = "name";
    private static final String ITEMS_KEY_DESC = "description";
    //Groups Table Column Names
    private static final String GROUPS_KEY_ID = "group_id";
    private static final String GROUPS_KEY_NAME = "name";
    //Category Table Column Names
    private static final String CATEGORIES_KEY_ID = "category_id";
    private static final String CATEGORIES_KEY_NAME = "name";
    //Market Price Table Column Names
    private static final String MARKET_PRICING_KEY_ID = "type_id";
    private static final String MARKET_PRICING_KEY_AVERAGE = "average_price";
    private static final String MARKET_PRICING_KEY_PRICE = "adjusted_price";
    //Pricing History Table Column Names
    private static final String PRICING_HISTORY_KEY_ID = "type_id";
    private static final String PRICING_HISTORY_KEY_PRICE = "adjusted_price";
    private static final String PRICING_HISTORY_KEY_UPDATED = "updated_last";
    //Group Items Table Column Names
    private static final String GROUP_ITEMS_GROUP_KEY = "group_id";
    private static final String GROUP_ITEMS_ITEM_KEY = "type_id";
    //Category Groups Table Column Names
    private static final String CATEGORY_GROUPS_GROUP_KEY = "group_id";
    private static final String CATEGORY_GROUPS_CATEGORY_KEY = "category_id";
    private final Context mycontext;
    private SQLiteDatabase db;

    public EveMarketDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, null);
        this.mycontext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_ITEMS_TABLE = "CREATE TABLE " + TABLE_ITEMS + "(" +
                ITEMS_KEY_ID + "INTEGER PRIMARY KEY," +
                ITEMS_KEY_NAME + "TEXT," +
                ITEMS_KEY_DESC + "TEXT" + ")";

        String CREATE_GROUP_TABLE = "CREATE TABLE " + TABLE_GROUPS + "(" +
                GROUPS_KEY_ID + "INTEGER PRIMARY KEY," +
                GROUPS_KEY_NAME + "TEXT" + ")";

        String CREATE_CATEGORY_TABLE = "CREATE TABLE " + TABLE_CATEGORIES + "(" +
                CATEGORIES_KEY_ID + "INTEGER PRIMARY KEY," +
                CATEGORIES_KEY_NAME + "TEXT" + ")";

        String CREATE_MARKETING_PRICING_TABLE = "CREATE TABLE " + TABLE_MARKET_PRICE + "(" +
                MARKET_PRICING_KEY_ID + "INTEGER PRIMARY KEY," +
                MARKET_PRICING_KEY_PRICE + "INTEGER," +
                MARKET_PRICING_KEY_AVERAGE + "INTEGER" + ")";

        String CREATE_PRICING_HISTORY_TABLE = "CREATE TABLE " + TABLE_PRICING_HISTORY + "(" +
                PRICING_HISTORY_KEY_ID + "INTEGER PRIMARY KEY," +
                PRICING_HISTORY_KEY_PRICE + "INTEGER," +
                PRICING_HISTORY_KEY_UPDATED + "DATETIME" + ")";

        String CREATE_GROUP_ITEMS_TABLE = "CREATE TABLE " + TABLE_GROUP_ITEMS + "(" +
                "ID PRIMARY KEY AUTOINCREMENT," +
                GROUP_ITEMS_GROUP_KEY + "INTEGER FOREIGN KEY REFERENCES " + TABLE_GROUPS + "(" + GROUPS_KEY_ID + ")," +
                GROUP_ITEMS_ITEM_KEY + "INTEGER FOREIGN KEY REFERENCES" + TABLE_ITEMS + "(" + ITEMS_KEY_ID + "))";

        String CREATE_CATEGORY_GROUPS_TABLE = "CREATE TABLE " + TABLE_CATEGORY_GROUPS + "(" +
                "ID PRIMARY KEY AUTOINCREMENT," +
                CATEGORY_GROUPS_CATEGORY_KEY + "INTEGER FOREIGN KEY REFERENCES " + TABLE_CATEGORIES + "(" + CATEGORIES_KEY_ID + ")," +
                CATEGORY_GROUPS_GROUP_KEY + "INTEGER FOREIGN KEY REFERENCES " + TABLE_GROUPS + "(" + GROUPS_KEY_ID + "))";

        db.execSQL(CREATE_ITEMS_TABLE);
        db.execSQL(CREATE_GROUP_TABLE);
        db.execSQL(CREATE_CATEGORY_TABLE);
        db.execSQL(CREATE_MARKETING_PRICING_TABLE);
        db.execSQL(CREATE_PRICING_HISTORY_TABLE);
        db.execSQL(CREATE_GROUP_ITEMS_TABLE);
        db.execSQL(CREATE_CATEGORY_GROUPS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public String getDatabaseName() {
        return this.DATABASE_NAME;
    }

    @Override
    public synchronized void close() {
        super.close();
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}
