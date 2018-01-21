package ca.prairesunapplications.evemarkethub.screens;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

import ca.prairesunapplications.evemarkethub.R;
import ca.prairesunapplications.evemarkethub.adapters.ItemListAdapter;
import ca.prairesunapplications.evemarkethub.objects.Item;

public class ItemsList extends AppCompatActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_items_list);

		ListView listView = findViewById(R.id.listView);
		ArrayList<Item> items = new ArrayList<>();

		Item default_item = new Item();

		items.add(default_item);

		ItemListAdapter adapter = new ItemListAdapter(this, items);
		listView.setAdapter(adapter);

		listView.setEmptyView(findViewById(R.id.emptyElement));

	}

}
