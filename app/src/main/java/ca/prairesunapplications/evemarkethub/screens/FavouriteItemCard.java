package ca.prairesunapplications.evemarkethub.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

import ca.prairesunapplications.evemarkethub.R;

public class FavouriteItemCard extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_favourite_item_card);
	}

	public void getItemDetails(View view) {
		Intent intent = new Intent(this, ItemDetails.class);
		CardView card = findViewById(view.getId());
		intent.putExtra("Item ID", (Integer) card.getTag());
		startActivity(intent);
	}
}
