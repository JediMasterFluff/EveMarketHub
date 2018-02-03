package ca.prairesunapplications.evemarkethub.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import ca.prairesunapplications.evemarkethub.R;
import ca.prairesunapplications.evemarkethub.adapters.StationItemAdapter;
import ca.prairesunapplications.evemarkethub.objects.Item;
import ca.prairesunapplications.evemarkethub.objects.Station;
import ca.prairesunapplications.evemarkethub.utils.SharedPreference;

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
		switch(menuItem.getItemId()) {
			case R.id.favourite_entry:
				// check if item is already favourited
				// if yes, remove from favourite list and change icon to ic_unfavourite
				// else, add to favourites list and change icon to ic_favourite
				if(preference.isFavourite(this, station, SharedPreference.FAV_PREF_NAME, SharedPreference.STATION_FAVOURITES)) { // if this item is already a fav
					preference.removeFavourite(this, station, SharedPreference.FAV_PREF_NAME, SharedPreference.STATION_FAVOURITES, Station[].class);
					menuItem.setIcon(R.drawable.ic_unfavourite);
				} else {
					preference.addFavourite(this, station, SharedPreference.FAV_PREF_NAME, SharedPreference.STATION_FAVOURITES, Station[].class);
					menuItem.setIcon(R.drawable.ic_favourite);
				}
				return true;
			case R.id.refresh_entry:
				break;
			case android.R.id.home:
				finish();
			default:

		}
		return true;
	}

	private void generateItemsList() {

	}
}