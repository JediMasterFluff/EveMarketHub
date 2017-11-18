package ca.prairesunapplications.evemarkethub.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ProgressBar;

import ca.prairesunapplications.evemarkethub.database.EveMarketDatabaseHandler;
import ca.prairesunapplications.evemarkethub.database.LoadDb;

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
        if(resourcesExist()){
            downloadResources();
        }
        return 1234;
    }

    private void downloadResources() {
        handler = new EveMarketDatabaseHandler(myContext);

        new LoadDb(myContext, handler.getWritableDatabase());
    }

    private boolean resourcesExist() {
        return true;
    }

    @Override
    protected void onProgressUpdate(Integer... integers) {
        super.onProgressUpdate(integers);
        progressBar.setProgress(integers[0]);
    }


    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        finishedListener.onTaskFinished();

    }
}
