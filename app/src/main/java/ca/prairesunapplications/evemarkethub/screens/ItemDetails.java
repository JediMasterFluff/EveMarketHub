package ca.prairesunapplications.evemarkethub.screens;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import ca.prairesunapplications.evemarkethub.R;

public class ItemDetails extends AppCompatActivity {

    private TextView priceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        priceView = findViewById(R.id.itemPriceView);


        GraphView graph = findViewById(R.id.itemHistoryGraph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 8)
        });

        series.setTitle("Random Curve 1");
        series.setColor(Color.GREEN);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(10);
        series.setThickness(8);

        graph.setTitleColor(Color.WHITE);
        graph.setBackgroundColor(getResources().getColor(R.color.colorGraphBackground, getTheme()));
        graph.addSeries(series);

        priceView.setTextColor(getTextColour());
    }

    private int getTextColour() {
        int oldPrice = Integer.parseInt((String) priceView.getText());
        if (oldPrice > 0) {
            return getResources().getColor(R.color.colorNegativePriceDiff, getTheme());
        } else if (oldPrice < 0) {
            return getResources().getColor(R.color.colorPositivePriceDiff, getTheme());
        } else {
            return getResources().getColor(R.color.colorNoPriceDiff, getTheme());
        }
    }
}
