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
		super.setContentView(R.layout.toolbar_station_details);

		Intent intent = getIntent();

		id = intent.getIntExtra(STATION_ID, 0);

	}

	private void generateItemsList() {

	}
}