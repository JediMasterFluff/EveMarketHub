package ca.prairesunapplications.evemarkethub.screens;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import ca.prairesunapplications.evemarkethub.R;

/**
 * Created by fluffy on 27/01/18.
 */

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

	public DrawerLayout drawerLayout;
	public Toolbar toolbar;
	public ActionBarDrawerToggle toggle;
	private NavigationView navigationView;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	protected void onCreateDrawer() {
		toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		drawerLayout = findViewById(R.id.drawer_layout);
		toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawerLayout.addDrawerListener(toggle);

		navigationView = findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);

	}

	@Override
	public void setContentView(int layoutResID) {
		super.setContentView(layoutResID);
		onCreateDrawer();
	}

	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item) {
		int id = item.getItemId();

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
		item.setChecked(false);
		return true;
	}

	@Override
	public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
		super.onPostCreate(savedInstanceState, persistentState);
		toggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		toggle.onConfigurationChanged(newConfig);
	}
}

