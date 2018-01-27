package ca.prairesunapplications.evemarkethub.screens;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import ca.prairesunapplications.evemarkethub.R;
import ca.prairesunapplications.evemarkethub.objects.Station;
import ca.prairesunapplications.evemarkethub.utils.SharedPreference;

public class StationDetails extends BaseActivity {

	private int id;
	private SharedPreference preference;
	private Station station;

	public static final String STATION_ID = "Station ID";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_station_details);
		;
		toolbar.setTitle("");
		getSupportActionBar().setDisplayShowHomeEnabled(false);
		getSupportActionBar().setDisplayHomeAsUpEnabled(false); //This shows the back arrow in the toolbar

		Intent intent = getIntent();

		id = intent.getIntExtra(STATION_ID, 0);

		station = new Station();

		preference = new SharedPreference();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.details_toolbar_menu, menu);

		MenuItem favItem = menu.getItem(id);

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
					Log.e("EveMarketHub", "Removing From Favs");
					preference.removeFavourite(this, station, SharedPreference.FAV_PREF_NAME, SharedPreference.STATION_FAVOURITES);
					menuItem.setIcon(R.drawable.ic_unfavourite);
				} else {
					Log.e("EvemMarketHub", "Adding to Favs");
					preference.addFavourite(this, station, SharedPreference.FAV_PREF_NAME, SharedPreference.STATION_FAVOURITES);
					menuItem.setIcon(R.drawable.ic_favourite);
				}
				return true;
			case R.id.refresh_entry:

			case android.R.id.home:
				finish();
			default:

		}
		return true;
	}

	private void generateItemsList() {

	}
}