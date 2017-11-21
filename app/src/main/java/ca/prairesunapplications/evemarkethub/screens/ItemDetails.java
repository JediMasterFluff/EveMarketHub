package ca.prairesunapplications.evemarkethub.screens;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class ItemDetails extends AppCompatActivity {

    private TextView priceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        ActionBar bar = getActionBar();
        //bar.setTitle("");

        priceView = findViewById(R.id.itemPriceView);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        int id = extras.getInt("Item ID");

        Item item = new Item(); //getItem(id);

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
        CreateMenu(menu);
        return true;
    }

    private void CreateMenu(Menu menu) {
        MenuItem item = menu.add(0, 0, 0, "Toggle Favourite Item");
        {
            item.setIcon(R.drawable.ic_unfavourite);
            item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }
    }

    private boolean MenuChoice(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                // check if item is already favourited
                // if yes, remove from favourite list and change icon to ic_unfavourite
                // else, add to favourites list and change icon to ic_favourite
                return true;
            default:
                return true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return MenuChoice(item);
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
