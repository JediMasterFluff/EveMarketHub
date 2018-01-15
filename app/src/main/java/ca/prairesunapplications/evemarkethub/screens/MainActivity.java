package ca.prairesunapplications.evemarkethub.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import ca.prairesunapplications.evemarkethub.R;
import ca.prairesunapplications.evemarkethub.adapters.FavItemAdapter;
import ca.prairesunapplications.evemarkethub.adapters.FavStationAdapter;
import ca.prairesunapplications.evemarkethub.objects.Item;
import ca.prairesunapplications.evemarkethub.objects.Station;
import ca.prairesunapplications.evemarkethub.utils.SharedPreference;
import xdroid.toaster.Toaster;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		DrawerLayout drawer = findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.addDrawerListener(toggle);
		toggle.syncState();

		NavigationView navigationView = findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);

		generateItemFavourites();
		generateStationFavourites();

	}

	private void generateStationFavourites() {
		//TODO Create Station favourite logic in SharedPreferences

		//For now, filling with default data to test views

		ArrayList<Station> data = new ArrayList<>();
		for(int i = 0; i < 9; i++) {
			Station st = new Station("Station " + i, "System Name");
			data.add(st);
		}

		RecyclerView rv = findViewById(R.id.stations_list);
		rv.setHasFixedSize(true);

		int columns = 2;
		RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, columns);
		rv.setLayoutManager(layoutManager);

		FavStationAdapter adapter = new FavStationAdapter(this, data);
		rv.setAdapter(adapter);
	}

	private void generateItemFavourites() {

		SharedPreference preference = new SharedPreference();

		List<Item> fav_items = preference.getFavourites(this);

		RecyclerView rv = findViewById(R.id.favourite_list);
		rv.setHasFixedSize(true);

		RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
		rv.setLayoutManager(layoutManager);

		FavItemAdapter adapter = new FavItemAdapter(this, fav_items, preference);
		rv.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main_bar_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch(item.getItemId()) {
			case R.id.refresh_lists:
				//TODO Create data refresh logic - will need to pull the preference lists for objects to refresh
				Toaster.toastLong("Will refresh both lists");

		}
		return true;
	}

	@Override
	public void onBackPressed() {
		DrawerLayout drawer = findViewById(R.id.drawer_layout);
		if(drawer.isDrawerOpen(GravityCompat.START)) drawer.closeDrawer(GravityCompat.START);
		else super.onBackPressed();
	}

	@Override
	protected void onResume() {
		super.onResume();
		generateItemFavourites();
	}

	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
		int id = menuItem.getItemId();

		Intent intent;

		if(id == R.id.nav_manage) {
			intent = new Intent(this, ItemDetails.class);
			startActivity(intent);
		} else if(id == R.id.nav_account) {
			//TODO create Account Activity

			//Open Account Activity

		} else if(id == R.id.nav_share) {

		} else if(id == R.id.nav_send) {

		} else intent = new Intent(this, ItemDetails.class);

		DrawerLayout drawer = findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		menuItem.setChecked(false);
		return true;
	}

	public void sendMessage(View view) {
		Intent intent = new Intent(this, ItemDetails.class);

		startActivity(intent);
	}

	public void sendToItemList(View view) {
		Intent intent = new Intent(this, ItemsList.class);
		startActivity(intent);
	}
}
