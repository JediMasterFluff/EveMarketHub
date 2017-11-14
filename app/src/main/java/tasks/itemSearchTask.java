package tasks;

import android.app.ActionBar;
import android.app.Activity;
import android.os.AsyncTask;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import objects.Item;

/**
 * Created by fluffy on 13/11/17.
 */

public class itemSearchTask extends AsyncTask<Object,Void,List<Item>> {

    List<Item> items;
    ProgressBar progressBar;
    Activity item_list_activity;

    public itemSearchTask(Activity activity){
        item_list_activity = activity;
    }

    @Override
    protected void onPreExecute() {
        progressBar = new ProgressBar(item_list_activity);
        progressBar.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        progressBar.setIndeterminate(true);

        item_list_activity.addContentView(progressBar, progressBar.getLayoutParams());
    }

    @Override
    protected List<Item> doInBackground(Object... objects) {
        return null;
    }

    @Override
    protected void onPostExecute(List<Item> items) {
        progressBar.setVisibility(View.GONE);
    }
}


