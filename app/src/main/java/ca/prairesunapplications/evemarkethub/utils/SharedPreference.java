package ca.prairesunapplications.evemarkethub.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ca.prairesunapplications.evemarkethub.objects.Item;
import xdroid.toaster.Toaster;

/**
 * EveMarketHub SharedPreferences class to handle all operations to the preference for the app
 *
 * Created by fluffy on 21/11/17.
 */

public class SharedPreference {

	private static final String FAV_PREF_NAME = "EVEMARKETHUB_FAVOURITES";
	private static final String USER_PREF_NAME = "EVEMARKETHUB_USER";
	private static final String DATA_PREF_NAME = "EVEMARKETHUB_DATA";
	private static final String FAVS = "Item_Favourites";

	public SharedPreference() {
		super();
	}

	public void addFavourite(Context context, Item item) {
		List<Item> items = getFavourites(context);
		if(items == null) {
			items = new ArrayList<>();
		} else if(items.size() < 5) {// Only allowing 5 favourites at this time
			if(isFavourite(context, item)) {
				Toaster.toastLong("Item is already a favourite");
			} else {
				items.add(item);
				Toaster.toast("Item added to favourites");
			}
		} else {
			Toaster.toastLong("Cannot add any more favourites");
		}
		saveFavourites(context, items);
	}

	public void saveFavourites(Context context, List<Item> items) {
		SharedPreferences settings;
		SharedPreferences.Editor editor;
		Log.e("EveMarketHub", "getting settings from preference");
		settings = context.getSharedPreferences(FAV_PREF_NAME, Context.MODE_PRIVATE);

		editor = settings.edit();

		Gson gson = new Gson();
		String json = gson.toJson(items);

		editor.putString(FAVS, json);

		editor.apply();
	}

	public ArrayList<Item> getFavourites(Context context) {
		SharedPreferences settings;
		List<Item> favs;

		settings = context.getSharedPreferences(FAV_PREF_NAME, Context.MODE_PRIVATE);

		if(settings.contains(FAVS)) {
			String json = settings.getString(FAVS, null);
			Gson gson = new Gson();
			Item[] items = gson.fromJson(json, Item[].class);

			favs = Arrays.asList(items);
			favs = new ArrayList<>(favs);
		} else return new ArrayList<>();

		return (ArrayList<Item>) favs;
	}

	public boolean isFavourite(Context context, Item item) {

		ArrayList<Item> items = getFavourites(context);
		if(items != null) {
			for(Item i : items) {
				if(i.getId() == item.getId()) return true;
			}
			return false;
		} else return false;
	}

	public void removeFavourite(Context context, Item item) {
		ArrayList<Item> items = getFavourites(context);
		if(items != null) {
			for(Item i : items) {
				if(i.getId() == item.getId()) {
					items.remove(i);
					Toaster.toast("Item Removed From Favourites");
					saveFavourites(context, items);
				}
			}
		}
	}
}