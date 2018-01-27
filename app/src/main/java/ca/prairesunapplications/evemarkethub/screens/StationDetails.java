package ca.prairesunapplications.evemarkethub.screens;

import android.content.Intent;
import android.os.Bundle;

import ca.prairesunapplications.evemarkethub.R;

public class StationDetails extends BaseActivity {

	private int id;

	public static final String STATION_ID = "Station ID";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_station_details);
		toolbar.setTitle("");
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true); //This shows the back arrow in the toolbar

		Intent intent = getIntent();

		id = intent.getIntExtra(STATION_ID, 0);

	}

	private void generateItemsList() {

	}
}