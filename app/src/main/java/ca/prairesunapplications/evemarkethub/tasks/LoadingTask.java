package ca.prairesunapplications.evemarkethub.tasks;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ProgressBar;

import ca.prairesunapplications.evemarkethub.database.EveMarketDatabaseHandler;

/**
 * Created by fluffy on 18/11/17.
 */

public class LoadingTask extends AsyncTask<String,Integer,Integer> {

    private final ProgressBar progressBar;
    private final LoadingTaskFinishedListener finishedListener;
    private Context myContext;
    private EveMarketDatabaseHandler handler;

    public interface LoadingTaskFinishedListener{
        void onTaskFinished();
    }

    public LoadingTask(ProgressBar progressBar, @Nullable LoadingTaskFinishedListener finishedListener, Context context){
        this.progressBar = progressBar;
        this.finishedListener = finishedListener;
        this.myContext = context;
    }

    @Override
    protected Integer doInBackground(String... strings) {
        Log.i("EveMarketHub", "Starting task");
        handler = new EveMarketDatabaseHandler(myContext);
        SQLiteDatabase db = handler.getWritableDatabase();
        return null;
    }

    protected void onProgressUpdate() {
        onProgressUpdate();
    }


    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
    }
}
