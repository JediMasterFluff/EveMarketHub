package ca.prairesunapplications.evemarkethub.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fluffy on 16/11/17.
 * Database helper class to handle initial database creation and load
 */

public class EveMarketDatabaseHandler extends SQLiteOpenHelper {

	public static final int PRICE_INCREASE = 1;
	public static final int PRICE_DESCREASE = -1;
	public static final int PRICE_NEUTRAL = 0;
	private static final int DATABASE_VERSION = 2;
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
	private SQLiteDatabase db;

	public EveMarketDatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION, null);
	}

	@Override
	public String getDatabaseName() {
		return DATABASE_NAME;
	}

	@Override
	public synchronized void close() {
		super.close();
	}

	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		cleanSlate();
	}

	public void cleanSlate() {

		Log.d("EveMarket", "Cleaning Database");

		db = getWritableDatabase();

		String DROP_TABLE_ITEMS = "DROP TABLE IF EXISTS " + TABLE_ITEMS;
		String DROP_TABLE_GROUPS = "DROP TABLE IF EXISTS " + TABLE_GROUPS;
		String DROP_TABLE_CATEGORY = "DROP TABLE IF EXISTS " + TABLE_CATEGORIES;
		String DROP_TABLE_MARKET = "DROP TABLE IF EXISTS " + TABLE_MARKET_PRICE;
		String DROP_TABLE_HISTORY = "DROP TABLE IF EXISTS " + TABLE_PRICING_HISTORY;
		String DROP_TABLE_ITEM_GROUPS = "DROP TABLE IF EXISTS " + TABLE_GROUP_ITEMS;
		String DROP_TABLE_CATEGORY_GROUPS = "DROP TABLE IF EXISTS " + TABLE_CATEGORY_GROUPS;

		db.execSQL(DROP_TABLE_CATEGORY_GROUPS);
		db.execSQL(DROP_TABLE_ITEM_GROUPS);
		db.execSQL(DROP_TABLE_HISTORY);
		db.execSQL(DROP_TABLE_MARKET);
		db.execSQL(DROP_TABLE_CATEGORY);
		db.execSQL(DROP_TABLE_GROUPS);
		db.execSQL(DROP_TABLE_ITEMS);

		createDb(db);
	}

	private void createDb(SQLiteDatabase sqLiteDatabase) {
		Log.i("EveMarket", "Creating Database");
		db = sqLiteDatabase;
		String CREATE_ITEMS_TABLE = "CREATE TABLE " + TABLE_ITEMS + "(" + ITEMS_KEY_ID + " INTEGER PRIMARY KEY," + ITEMS_KEY_NAME + " TEXT," + ITEMS_KEY_DESC + " TEXT" + ")";

		String CREATE_GROUP_TABLE = "CREATE TABLE " + TABLE_GROUPS + "(" + GROUPS_KEY_ID + " INTEGER PRIMARY KEY, " + GROUPS_KEY_NAME + " TEXT" + ")";

		String CREATE_CATEGORY_TABLE = "CREATE TABLE " + TABLE_CATEGORIES + "(" + CATEGORIES_KEY_ID + " INTEGER PRIMARY KEY, " + CATEGORIES_KEY_NAME + " TEXT" + ")";

		String CREATE_MARKETING_PRICING_TABLE = "CREATE TABLE " + TABLE_MARKET_PRICE + "(" + MARKET_PRICING_KEY_ID + " INTEGER PRIMARY KEY, " + MARKET_PRICING_KEY_PRICE + " INTEGER, " + MARKET_PRICING_KEY_AVERAGE + " INTEGER" + ")";

		String CREATE_PRICING_HISTORY_TABLE = "CREATE TABLE " + TABLE_PRICING_HISTORY + "(" + PRICING_HISTORY_KEY_ID + " INTEGER PRIMARY KEY, " + PRICING_HISTORY_KEY_PRICE + " INTEGER, " + PRICING_HISTORY_KEY_UPDATED + " DATETIME" + ")";

		String CREATE_GROUP_ITEMS_TABLE = "CREATE TABLE " + TABLE_GROUP_ITEMS + "(" + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + GROUP_ITEMS_GROUP_KEY + " INTEGER, " + GROUP_ITEMS_ITEM_KEY + " INTEGER, " + "FOREIGN KEY (" + GROUP_ITEMS_GROUP_KEY + ") REFERENCES " + TABLE_GROUPS + "(" + GROUPS_KEY_ID + "), " + "FOREIGN KEY (" + GROUP_ITEMS_ITEM_KEY + ") REFERENCES " + TABLE_ITEMS + "(" + ITEMS_KEY_ID + "))";

		String CREATE_CATEGORY_GROUPS_TABLE = "CREATE TABLE " + TABLE_CATEGORY_GROUPS + "(" + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + CATEGORY_GROUPS_CATEGORY_KEY + " INTEGER, " + CATEGORY_GROUPS_GROUP_KEY + " INTEGER, " + "FOREIGN KEY (" + CATEGORY_GROUPS_CATEGORY_KEY + ") REFERENCES " + TABLE_CATEGORIES + "(" + CATEGORIES_KEY_ID + "), " + "FOREIGN KEY (" + CATEGORY_GROUPS_GROUP_KEY + ") REFERENCES " + TABLE_GROUPS + "(" + GROUPS_KEY_ID + "))";

		db.execSQL(CREATE_ITEMS_TABLE);
		Log.i("EveMarket", "Created Items Table");
		db.execSQL(CREATE_GROUP_TABLE);
		Log.i("EveMarket", "Created Groups Table");
		db.execSQL(CREATE_CATEGORY_TABLE);
		Log.i("EveMarket", "Created Category Table");
		db.execSQL(CREATE_MARKETING_PRICING_TABLE);
		Log.i("EveMarket", "Created Market Table");
		db.execSQL(CREATE_PRICING_HISTORY_TABLE);
		Log.i("EveMarket", "Created History Table");
		db.execSQL(CREATE_GROUP_ITEMS_TABLE);
		Log.i("EveMarket", "Created Group Items Table");
		db.execSQL(CREATE_CATEGORY_GROUPS_TABLE);
		Log.i("EveMarket", "Created Category Groups Table");
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
		Log.i("EveMarket", "-------------------------------------------------------------- onUpgrade called");
		createDb(sqLiteDatabase);
	}

	// When called, the app will call the EVE url to get the latest info on the provided item id
	public boolean updateItem(int id) {
		return true;
	}

	void addItem(int id, String name, String description) {
		db = getWritableDatabase();
		String sql = "INSERT INTO " + TABLE_ITEMS + " VALUES (" + id + ", '" + name + "', '" + description + "')";

		try {
			db.execSQL(sql);
		} catch(SQLException e) {
			e.printStackTrace();
		}

        /*
        Log.i("Item Inserted > ",
                "ID: " + id + ", " +
                        "Name: " + name + ", " +
                        "Desc: " + description);
        */
	}

	public JSONObject getItem(int id) {

		String sql = "SELECT i.name, i.price, c.name " + "FROM items as i, Category as c, Group_Items as gi, Category_Groups as cg " + "WHERE i.type_id = " + id + " " + "AND i.type_id = gi.type_id" + "AND gi.group_id = cg.group_id" + "AND cg.category_id = c.category_id";

		SQLiteDatabase db = getReadableDatabase();

		Cursor c = db.rawQuery(sql, null);
		c.moveToNext();

		JSONObject object = new JSONObject();
		try {
			object.put("id", id);
			object.put("name", c.getString(0));
			object.put("price", c.getDouble(1));
			object.put("category", c.getString(2));
		} catch(JSONException e) {
			e.printStackTrace();
		}

		c.close();
		return object;
	}

	public Map<Float, Float> gerPricingHistory(int id) {

		Map<Float, Float> map = new HashMap<>();

		String sql = "SELECT old_price, updated_last " + "FROM Pricing_History " + "WHERE type_id = " + id;
		SQLiteDatabase db = getReadableDatabase();

		Cursor c = db.rawQuery(sql, null);
		while(c.moveToNext()) {
			map.put(c.getFloat(0), c.getFloat(1));
		}
		c.close();
		return map;

	}

	public int getOldPriceChange(int id) {

		db = getReadableDatabase();

		String sql = "SELECT old_price, updated_last " + "FROM Pricing_History " + "WHERE type_id = " + id + " " + "ORDER BY updated_last DESC LIMIT 1";
		Cursor c = db.rawQuery(sql, null);
		c.moveToNext();

		int oldestPrice = c.getInt(0);
		c.close();

		return oldestPrice;

	}
}
