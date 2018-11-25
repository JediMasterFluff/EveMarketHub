package ca.prairesunapplications.evemarkethub.database;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.*;
import com.loopj.android.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import ca.prairesunapplications.evemarkethub.utils.EveRestClient;
import cz.msebera.android.httpclient.entity.mime.Header;

/**
 * Created by fluffy on 18/11/17.
 * This class' sole purpose is to house all calls to load data into the EveMarket DB
 * This class will assume the database is already created and the referenced table columns exist
 * This class will perform ALL url calls and pass the returned info to the DB Helper
 */

public class LoadDb {

	private final Context myContext;
	private Database mDb;
	private ItemDAO mItemDao;

	public LoadDb(Context context) {
		this.myContext = context;
		mDb = Room.inMemoryDatabaseBuilder(myContext, Database.class).build();
		mItemDao = mDb.itemDAO();
		try {
			loadItems();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		//loadGroups();
		//loadCategories();
		//createCategoryGroups();
		//createGroupItems();
		//loadMarketPricing();
	}


	private void loadItems() throws JSONException {
		EveRestClient.get("universe/type/?datasource=tranquility&page=1", null, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
				super.onSuccess(statusCode, headers, response);
			}

			@Override
			public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONArray items) {

				for (int i = 0; i < items.length(); i++) {
					try {
						JSONObject object = items.getJSONObject(i);

						Item item = new Item();
						item.id = object.getInt("id");
						item.description = object.getString("description");
						item.price =  object.getDouble("price");
						item.average_price = object.getDouble("average_price");
						item.group_id = object.getInt("group_id");

						mItemDao.addItem(item);

					} catch (JSONException e) {
						e.printStackTrace();
					}
					
				}


			}
		});
	}


/*
	private void loadItems() {
		EveMarketDatabaseHandler handler = new EveMarketDatabaseHandler(myContext);
		String json = getJson(buildRequestURL(1));
		try {
			JSONArray array = new JSONArray(json);

			for(int i = 0; i < array.length(); i++) {
				int id = array.getInt(i);

				String detailJson = getJson(buildRequestURL(id));
				JSONObject type = new JSONObject(detailJson);

				if(type.getBoolean("published")) {
					int type_id = type.getInt("type_id");
					String name = type.getString("name");
					String desc = cleanseString(type.getString("description"));

					handler.addItem(type_id, name, desc);
				}

			}
		} catch(JSONException e) {
			e.printStackTrace();
		}


	}

	*
	 * Builds the EVE api url based on what and where you want the data from
	 *
	 * @param i optional id of object we want details for
	 *
	 * @return finished constructed url string that matches the EVE API specs
	 *
	private String buildRequestURL(int i) {

		String url;
		String type = "";
		if(i == 1) {
			switch(TYPES.type) {
				case type:
					type = "types";
					break;
				case group:
					type = "groups";
					break;
				case category:
					type = "categories";
					break;
				case market:
					url = "https://esi.tech.ccp.is/" + "dev" + "/markets/prices/?datasource=" + "tranquility";
					return url;
				default:
					break;
			}

			url = "https://esi.tech.ccp.is/" + "dev" + "/universe/" + type + "/?datasource=" + "tranquility" + "&page=1";

		} else {
			switch(TYPES.type) {
				case type:
					type = "types";
					break;
				case group:
					type = "groups";
					break;
				case category:
					type = "categories";
					break;
				default:
					break;
			}

			url = "https://esi.tech.ccp.is/" + "dev" + "/universe/" + type + "/" + i + "/?datasource=" + "tranquility" + "&page=1";
		}

		return url;
	}
*/
	private String getJson(String u) {

		HttpURLConnection connection;
		BufferedReader reader;

		try {
			URL url = new URL(u);
			connection = (HttpURLConnection) url.openConnection();
			connection.connect();

			InputStream is = connection.getInputStream();

			reader = new BufferedReader(new InputStreamReader(is));

			StringBuilder buffer = new StringBuilder();
			String line;

			while((line = reader.readLine()) != null) {
				buffer.append(line).append("\n");
			}

			return buffer.toString();

		} catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String cleanseString(String d) {
		d = d.replaceAll("\\<.*?>", "");
		d = d.replaceAll("'", "");
		return d;
	}

	private void loadGroups() {

	}

	private void loadCategories() {

	}

	private void loadMarketPricing() {

	}

	private void createCategoryGroups() {

	}

	private void createGroupItems() {

	}

	private enum TYPES {
		type, group, category, market
	}

}
