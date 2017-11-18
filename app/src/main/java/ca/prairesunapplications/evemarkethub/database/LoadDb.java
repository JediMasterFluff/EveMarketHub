package ca.prairesunapplications.evemarkethub.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import ca.prairesunapplications.evemarkethub.screens.MainActivity;
import xdroid.toaster.Toaster;

/**
 * Created by fluffy on 18/11/17.
 *
 * This class' sole purpose is to house all calls to load data into the EveMarket DB
 * This class will assume the database is already created and the referenced table columns exist
 */

public class LoadDb {

    private enum TYPES{
        type,group,category,market
    }

    private Context myContext;
    private SQLiteDatabase db;

    public LoadDb(Context context, SQLiteDatabase db) {
        this.myContext = context;
        this.db = db;
        loadItems();
        //loadGroups();
        //loadCategories();
        //createCategoryGroups();
        //createGroupItems();
        //loadMarketPricing();
    }

    private void loadItems() {
        String json = getJson(buildRequestURL(TYPES.type,"tranquility","dev", "0"));
        TextView view = new TextView(myContext);
        view.setText(json);
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
    private String buildRequestURL(@NonNull TYPES t,@NonNull  String s,@NonNull  String l,@NonNull String i){

        String url;
        String type = "";
        if(i.equals("0")){
            switch (t){
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
                    url = "https://esi.tech.ccp.is/"+ l +"/markets/prices/?datasource=" + s;
                    return url;
                default:
                    break;
            }

            url = "https://esi.tech.ccp.is/"+ l +"/universe/"+ type +"/?datasource="+s+"&page=1";

        }else{
            switch (t){
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

            url = "https://esi.tech.ccp.is/"+ l +"/universe/"+ type +"/"+ Integer.parseInt(i) +"/?datasource="+s+"&page=1";
        }

        return url;
    }

    public String getJson(String u){

        HttpURLConnection connection;
        BufferedReader reader;

        try {
            URL url = new URL(u);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream is = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(is));

            StringBuffer buffer = new StringBuffer();
            String line = "";

            while((line = reader.readLine()) != null){
                buffer.append(line+"\n");
                Log.d("Response", "> " + line);
            }

            return buffer.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
