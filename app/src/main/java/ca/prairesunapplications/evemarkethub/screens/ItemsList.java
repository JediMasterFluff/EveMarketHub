package ca.prairesunapplications.evemarkethub.screens;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ca.prairesunapplications.evemarkethub.R;

public class ItemsList extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_list);
       /* ProgressBar progressBar = new ProgressBar(this);
        progressBar.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        progressBar.setIndeterminate(true);

        ViewGroup root = findViewById(android.R.id.content);
        root.addView(progressBar);*/
    }

}
