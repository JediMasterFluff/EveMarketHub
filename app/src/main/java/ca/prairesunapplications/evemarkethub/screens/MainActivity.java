package ca.prairesunapplications.evemarkethub.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import ca.prairesunapplications.evemarkethub.R;
import ca.prairesunapplications.evemarkethub.adapters.RVFavAdapter;
import ca.prairesunapplications.evemarkethub.objects.Item;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    private List<Item> fav_items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fav_items = getFav_items();

        rv = findViewById(R.id.favourite_list);
        rv.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);

        RVFavAdapter adapter = new RVFavAdapter(fav_items);
        rv.setAdapter(adapter);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, ItemDetails.class);

        startActivity(intent);
    }

    public void sendToItemList(View view) {
        Intent intent = new Intent(this, ItemsList.class);
        startActivity(intent);
    }

    public List<Item> getFav_items(){
        // This will need to load the list of names from a persistence object created by the user
        // but for now, this will be defaulted to 5 random things
        List<Item> list = new ArrayList<>();

        for(int i=0; i<5;i++){
            Item item = new Item(i, "Item "+i, "Category " + i, i * 1000000 );
            list.add(item);
        }

        return list;
    }
}
