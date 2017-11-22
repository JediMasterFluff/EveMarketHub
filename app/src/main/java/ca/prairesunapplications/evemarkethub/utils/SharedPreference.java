package ca.prairesunapplications.evemarkethub.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ca.prairesunapplications.evemarkethub.objects.Item;

/**
 * Created by fluffy on 21/11/17.
 */

public class SharedPreference {

    public static final String PREF_NAME = "EVEMARKETHUB";
    public static final String FAVS = "Item_Favourites";

    public SharedPreference() {
        super();
    }

    public void saveFavourites(Context context, List<Item> items) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        editor = settings.edit();

        Gson gson = new Gson();
        String json = gson.toJson(items);

        editor.putString(FAVS, json);

        editor.commit();
    }

    public void addFavourite(Context context, Item item) {
        List<Item> items = getFavourites(context);
        if (items == null) {
            items = new ArrayList<Item>();
        } else if (items.size() < 5)// Only allowing 5 favourites at this time
            items.add(item);

        saveFavourites(context, items);
    }

    public void removeFavourite(Context context, Item item) {
        ArrayList<Item> items = getFavourites(context);
        if (items != null) {
            items.remove(item);
            saveFavourites(context, items);
        }
    }

    public ArrayList<Item> getFavourites(Context context) {
        SharedPreferences settings;
        List<Item> favs;

        settings = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);

        if (settings.contains(FAVS)) {
            String json = settings.getString(FAVS, null);
            Gson gson = new Gson();
            Item[] items = gson.fromJson(json, Item[].class);

            favs = Arrays.asList(items);
            favs = new ArrayList<Item>(favs);
        } else
            return new ArrayList<Item>();

        return (ArrayList<Item>) favs;
    }

    public boolean isFavourite(Context context, Item item) {

        ArrayList<Item> items = getFavourites(context);
        if (items != null) {
            if (items.contains(item))
                return true;
            else
                return false;
        } else
            return false;
    }
}
