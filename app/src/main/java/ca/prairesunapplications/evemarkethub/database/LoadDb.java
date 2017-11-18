package ca.prairesunapplications.evemarkethub.database;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by fluffy on 18/11/17.
 *
 * This class' sole purpose is to house all calls to load data into the EveMarket DB
 * This class will assume the database is already created and the referenced table columns exist
 */

public class LoadDb {



    public void LoadDb() {
        loadItems();
        loadGroups();
        loadCategories();
        createCategoryGroups();
        createGroupItems();
        loadMarketPricing();
    }

    private void loadItems() {
    }

    private void loadGroups(){

    }

    private void loadCategories(){

    }

    private void loadMarketPricing(){

    }

    private void createCategoryGroups(){

    }

    private void createGroupItems(){

    }

    /**
     * Builds the EVE api url based on what and where you want the data from
     * @param t the type of data you want
     * @param s the server source you want it from
     * @param l the server build you want the data from
     * @param i optional id of object we want details for
     * @return finished constructed url string that matches the EVE API specs
     */
    private String buildRequestURL(@NonNull String t,@NonNull  String s,@NonNull  String l,@Nullable String i){

        String type;
        String source;
        String location;
        String id;

        switch (t){
            case "type":
                type = "types";
                break;
            case "group":
                type = "groups";
                break;
            case "category":
                type = "categories";
                break;
            case "market":
                type = "market";
                break;

            default:
                break;
        }
        return null;
    }
}
