package ca.prairesunapplications.evemarkethub.screens;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;
import java.util.Map;

import ca.prairesunapplications.evemarkethub.R;
import ca.prairesunapplications.evemarkethub.database.EveMarketDatabaseHandler;
import ca.prairesunapplications.evemarkethub.objects.Item;
import ca.prairesunapplications.evemarkethub.utils.SharedPreference;

public class ItemDetails extends AppCompatActivity {

    private SharedPreference preference;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details_bar);

        Toolbar toolbar = findViewById(R.id.items_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView priceView = findViewById(R.id.itemPriceView);

        preference = new SharedPreference();

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        int id;
        if (extras != null)
            id = extras.getInt("Item ID");
        else
            id = 0;

        item = new Item(); //getItem(id);

        GraphView graph = findViewById(R.id.itemHistoryGraph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();//getPricingHistory(id);

        series.setTitle("Random Curve 1");
        series.setColor(Color.GREEN);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(10);
        series.setThickness(8);

        graph.setTitleColor(Color.WHITE);
        graph.setBackgroundColor(getResources().getColor(R.color.colorGraphBackground, getTheme()));
        graph.addSeries(series);

        double price = item.getPrice();
        priceView.setTextColor(getTextColour(id));
        priceView.setText(String.format(Locale.CANADA, "%1$,.2f", price));
    }

    private int getTextColour(int id) {
        double oldPrice = getOldPrice(id);
        if (oldPrice > 0) {
            return getResources().getColor(R.color.colorNegativePriceDiff, getTheme());
        } else if (oldPrice < 0) {
            return getResources().getColor(R.color.colorPositivePriceDiff, getTheme());
        } else {
            return getResources().getColor(R.color.colorNoPriceDiff, getTheme());
        }
    }

    private Item getItem(int id) {
        EveMarketDatabaseHandler handler = new EveMarketDatabaseHandler(this);
        JSONObject obj = handler.getItem(id);

        Item item = new Item();
        try {
            item.setName(obj.getString("name"));
            item.setId(id);
            item.setPrice(obj.getDouble("price"));
            item.setCategory_name(obj.getString("category"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return item;
    }

    private double getOldPrice(int id) {
        return 0.0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_items_detail_bar_menu, menu);

        MenuItem favItem = menu.getItem(0);

        if (preference.isFavourite(this, item))
            favItem.setIcon(R.drawable.ic_favourite);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.favourite_item:
                // check if item is already favourited
                // if yes, remove from favourite list and change icon to ic_unfavourite
                // else, add to favourites list and change icon to ic_favourite
                if (preference.isFavourite(this, item)) { // if this item is already a fav
                    preference.removeFavourite(this, item);
                    menuItem.setIcon(R.drawable.ic_unfavourite);
                } else {
                    preference.addFavourite(this, item);
                    menuItem.setIcon(R.drawable.ic_favourite);
                }
                return true;
            case R.id.refresh_item:
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return true;
        }
    }

    private LineGraphSeries<DataPoint> getPricingHistory(int id) {
        EveMarketDatabaseHandler handler = new EveMarketDatabaseHandler(this);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
        Map<Float, Float> map = handler.gerPricingHistory(id);

        for (Map.Entry<Float, Float> point : map.entrySet()) {
            series.appendData(new DataPoint(point.getKey(), point.getValue()), false, map.size(), true);
        }
        return series;
    }

}
