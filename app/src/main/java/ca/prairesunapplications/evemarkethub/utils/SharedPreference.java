package ca.prairesunapplications.evemarkethub.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ca.prairesunapplications.evemarkethub.objects.Item;
import ca.prairesunapplications.evemarkethub.objects.Station;
import ca.prairesunapplications.evemarkethub.screens.BaseActivity;
import xdroid.toaster.Toaster;

/**
 * EveMarketHub SharedPreferences class to handle all operations to the preference for the app
 * Created by fluffy on 21/11/17.
 */

public class SharedPreference {

	public static final String FAV_PREF_NAME = "EVEMARKETHUB_FAVOURITES";
	public static final String USER_PREF_NAME = "EVEMARKETHUB_USER";
	public static final String DATA_PREF_NAME = "EVEMARKETHUB_DATA";

	public static final String ITEM_FAVOURITES = "Item_Favourites";
	public static final String STATION_FAVOURITES = "Station_Favourites";

	public SharedPreference() {
		super();
	}

	public void addFavourite(Context context, Object item, String pref_name, String pref_type, Class cls) {
		ArrayList favs = getFavourites(context, pref_name, pref_type, cls);
		if(favs == null) {
			favs = new ArrayList<>();
		} else if(favs.size() < 5) {// Only allowing 5 favourites at this time
			if(isFavourite(context, item, pref_name, pref_type)) {
				Toaster.toastLong("Item is already a favourite");
			} else {
				favs.add(item);
				Toaster.toast("Item added to favourites");
			}
		} else {
			Toaster.toastLong("Cannot add any more favourites");
		}
		saveFavourites(context, favs, pref_name, pref_type);
	}

	public void saveFavourites(Context context, List<Class> favs, String pref_name, String pref_type) {
		SharedPreferences settings;
		SharedPreferences.Editor editor;
		Log.e(BaseActivity.LOG_HEADER, "getting settings from preference");
		settings = context.getSharedPreferences(pref_name, Context.MODE_PRIVATE);

		editor = settings.edit();

		Gson gson = new Gson();
		String json = gson.toJson(favs);

		editor.putString(pref_type, json);

		editor.apply();
	}

	public <T> ArrayList<T> getFavourites(Context context, String pref_name, String pref_type, Class<T[]> cls) {
		SharedPreferences settings;
		T[] favs;

		settings = context.getSharedPreferences(pref_name, Context.MODE_PRIVATE);

		if(settings.contains(pref_type)) {
			String json = settings.getString(pref_type, null);
			Gson gson = new Gson();
			favs = gson.fromJson(json, cls);

		} else return new ArrayList<>();

		return new ArrayList<>(Arrays.asList(favs));
	}

	public boolean isFavourite(Context context, Object item, String pref_name, String pref_type) {


		switch(pref_type) {
			case ITEM_FAVOURITES:
				Log.e(BaseActivity.LOG_HEADER, "Item Favourite Save");
				ArrayList<Item> favs = getFavourites(context, pref_name, pref_type, Item[].class);
				if(favs != null) {
					for(Item i : favs) {
						if(i.equals(item)) return true;
					}
				} else return false;
			case STATION_FAVOURITES:
				Log.e(BaseActivity.LOG_HEADER, "Station Favourite Save");
				ArrayList<Station> favs_st = getFavourites(context, pref_name, pref_type, Station[].class);
				if(favs_st != null) {
					for(Station s : favs_st) {
						if(s.equals(item)) return true;
					}
					return false;
				} else return false;
		}
		return false;
	}

	public void removeFavourite(Context context, Object item, String pref_name, String pref_type, Class cls) {
		ArrayList favs = getFavourites(context, pref_name, pref_type, cls);

		if(favs != null) {
			for(Object o : favs) {
				if(o instanceof Item && item instanceof Item) {
					Item i = (Item) o;
					Item j = (Item) item;
					if(i.getId() == j.getId()) {
						favs.remove(o);
						Toaster.toast("Item Removed From Favourites");
						saveFavourites(context, favs, pref_name, pref_type);
					}
				} else if(o instanceof Station && item instanceof Station) {
					Station s = (Station) o;
					Station t = (Station) item;
					if(s.getId() == t.getId()) {
						favs.remove(o);
						Toaster.toast("Station Removed From Favourites");
						saveFavourites(context, favs, pref_name, pref_type);
					}
				}
			}
		}
	}
}