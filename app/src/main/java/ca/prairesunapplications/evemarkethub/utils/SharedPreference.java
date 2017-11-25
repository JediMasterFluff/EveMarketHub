package ca.prairesunapplications.evemarkethub.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ca.prairesunapplications.evemarkethub.objects.Item;
import xdroid.toaster.Toaster;

/**
 * Created by fluffy on 21/11/17.
 */

public class SharedPreference {

    public static final String PREF_NAME = "EVEMARKETHUB";
    private static final String FAVS = "Item_Favourites";

    public SharedPreference() {
        super();
    }

    private void saveFavourites(Context context, List<Item> items) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        editor = settings.edit();

        Gson gson = new Gson();
        String json = gson.toJson(items);

        editor.putString(FAVS, json);

        editor.apply();
    }

    public void addFavourite(Context context, Item item) {
        List<Item> items = getFavourites(context);
        if (items == null) {
            items = new ArrayList<Item>();
        } else if (items.size() < 5) {// Only allowing 5 favourites at this time
            items.add(item);
            Toaster.toast("Item Added To Favourites");
        } else
            Toaster.toastLong("Cannot add any more favourites");

        saveFavourites(context, items);
    }

    public void removeFavourite(Context context, Item item) {
        ArrayList<Item> items = getFavourites(context);
        if (items != null) {
            for (Item i : items) {
                if (i.getId() == item.getId()) {
                    items.remove(i);
                    Toaster.toast("Item Removed From Favourites");
                    saveFavourites(context, items);
                }
            }
        }
    }

    public ArrayList<Item> getFavourites(Context context) {
        SharedPreferences settings;
        List<Item> favs;

        settings = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        if (settings.contains(FAVS)) {
            String json = settings.getString(FAVS, null);
            Gson gson = new Gson();
            Item[] items = gson.fromJson(json, Item[].class);

            favs = Arrays.asList(items);
            favs = new ArrayList<>(favs);
        } else
            return new ArrayList<>();

        return (ArrayList<Item>) favs;
    }

    public boolean isFavourite(Context context, Item item) {

        ArrayList<Item> items = getFavourites(context);
        if (items != null) {
            for (Item i : items) {
                if (i.getId() == item.getId())
                    return true;
            }
            return false;
        } else
            return false;
    }
}