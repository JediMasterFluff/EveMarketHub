package ca.prairesunapplications.evemarkethub.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ca.prairesunapplications.evemarkethub.R;
import ca.prairesunapplications.evemarkethub.adapters.StationItemAdapter;
import ca.prairesunapplications.evemarkethub.objects.Item;
import ca.prairesunapplications.evemarkethub.objects.Station;
import ca.prairesunapplications.evemarkethub.utils.EveRestClient;
import ca.prairesunapplications.evemarkethub.utils.SharedPreference;
import cz.msebera.android.httpclient.Header;
import xdroid.toaster.Toaster;

@SuppressWarnings("ConstantConditions")
public class StationDetails extends BaseActivity {

    private SharedPreference preference;
    private Station station;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_station_details);
        toolbar.setTitle("");
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false); //This shows the back arrow in the toolbar

        Intent intent = getIntent();

        int id = intent.getIntExtra(STATION_ID, 0);

        station = new Station();

        preference = new SharedPreference();

        RecyclerView rv = findViewById(R.id.item_list_station);
        rv.setLayoutManager(new GridLayoutManager(this, 4));
        rv.setAdapter(new StationItemAdapter(this, new ArrayList<Item>()));

        //generateItemsList();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details_toolbar_menu, menu);

        MenuItem favItem = menu.getItem(0);

        //if(preference.isFavourite(this, station)) favItem.setIcon(R.drawable.ic_favourite);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.favourite_entry:
                // check if item is already favourited
                // if yes, remove from favourite list and change icon to ic_unfavourite
                // else, add to favourites list and change icon to ic_favourite
                if (preference.isFavourite(this, station, SharedPreference.FAV_PREF_NAME, SharedPreference.STATION_FAVOURITES)) { // if this item is already a fav
                    preference.removeFavourite(this, station, SharedPreference.FAV_PREF_NAME, SharedPreference.STATION_FAVOURITES, Station[].class);
                    menuItem.setIcon(R.drawable.ic_unfavourite);
                } else {
                    preference.addFavourite(this, station, SharedPreference.FAV_PREF_NAME, SharedPreference.STATION_FAVOURITES, Station[].class);
                    menuItem.setIcon(R.drawable.ic_favourite);
                }
                return true;
            case R.id.refresh_entry:
                generateItemsList();
                break;
            case android.R.id.home:
                finish();
            default:

        }
        return true;
    }

    private void generateItemsList() {
        RequestParams params = new RequestParams("datasource", "tranquility");
        params.add("page", "1");
        Toaster.toastLong("Generating List");

        EveRestClient.get("/universe/types/", params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if (response.length() == 0)
                    Toaster.toastLong("Nothing returned");
                else
                    Toaster.toastLong("JSONObject Returned");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                if (response.length() == 0)
                    Toaster.toastLong("Nothing returned");
                else
                    try {
                        int id = response.getInt(56);
                        Toaster.toastLong(Integer.toString(id));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
            }
        });

    }
}