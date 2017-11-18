package ca.prairesunapplications.evemarkethub.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ca.prairesunapplications.evemarkethub.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, ItemDetails.class);

        startActivity(intent);
    }

    public void sendToItemList(View view) {
        Intent intent = new Intent(this, ItemsList.class);
        startActivity(intent);
    }
}
