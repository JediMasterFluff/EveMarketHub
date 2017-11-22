package ca.prairesunapplications.evemarkethub.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.List;

import ca.prairesunapplications.evemarkethub.R;
import ca.prairesunapplications.evemarkethub.adapters.RVFavAdapter;
import ca.prairesunapplications.evemarkethub.objects.Item;
import ca.prairesunapplications.evemarkethub.utils.SharedPreference;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generateFavourites();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("EveMarket", "Called onResume");
        generateFavourites();
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, ItemDetails.class);

        startActivity(intent);
    }

    public void sendToItemList(View view) {
        Intent intent = new Intent(this, ItemsList.class);
        startActivity(intent);
    }

    private List<Item> getFav_items() {

        SharedPreference preference = new SharedPreference();

        return preference.getFavourites(this);
    }

    private void generateFavourites() {
        List<Item> fav_items = getFav_items();

        rv = findViewById(R.id.favourite_list);
        rv.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);

        RVFavAdapter adapter = new RVFavAdapter(this, fav_items);
        rv.setAdapter(adapter);
    }
}
