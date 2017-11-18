package tasks;

import android.os.AsyncTask;

import java.util.List;

import ca.prairesunapplications.evemarkethub.screens.MainActivity;
import database.EveMarketDatabaseHandler;
import objects.Item;

/**
 * Created by fluffy on 13/11/17.
 */

public class itemSearchTask extends AsyncTask<Object,Void,List<String>> {

    private EveMarketDatabaseHandler handler;
    @Override
    protected void onPreExecute() {
        //handler = new EveMarketDatabaseHandler();
    }

    @Override
    protected List<String> doInBackground(Object... objects) {
        // perform search here
        return null;
    }

    @Override
    protected void onPostExecute(List<Item> items) {

    }
}


