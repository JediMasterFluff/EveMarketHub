package ca.prairesunapplications.evemarkethub.database;

import android.content.Context;
import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by fluffy on 18/11/17.
 * <p>
 * This class' sole purpose is to house all calls to load data into the EveMarket DB
 * This class will assume the database is already created and the referenced table columns exist
 * <p>
 * This class will perform ALL url calls and pass the returned info to the DB Helper
 */

public class LoadDb {

	private final Context myContext;

	public LoadDb(Context context) {
		this.myContext = context;
		loadItems();
		//loadGroups();
		//loadCategories();
		//createCategoryGroups();
		//createGroupItems();
		//loadMarketPricing();
	}

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

	/**
	 * Builds the EVE api url based on what and where you want the data from
	 *
	 * @param i optional id of object we want details for
	 * @return finished constructed url string that matches the EVE API specs
	 */
	private String buildRequestURL(@NonNull int i) {

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
			String line = "";

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

	private enum TYPES {
		type, group, category, market
	}

}
