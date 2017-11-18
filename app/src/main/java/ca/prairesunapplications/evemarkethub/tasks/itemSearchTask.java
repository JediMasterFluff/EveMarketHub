package ca.prairesunapplications.evemarkethub.tasks;

import android.os.AsyncTask;

import java.util.List;

import ca.prairesunapplications.evemarkethub.database.EveMarketDatabaseHandler;
import ca.prairesunapplications.evemarkethub.objects.Item;


/**
 * Created by fluffy on 13/11/17.
 */

@SuppressWarnings("ALL")
public class itemSearchTask extends AsyncTask<Object, Void, List<String>> {

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

    protected void onPostExecute(List<Item> items) {
        //Intent intent = new Intent(items);

    }
}


