package ca.prairesunapplications.evemarkethub.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ProgressBar;

import ca.prairesunapplications.evemarkethub.database.EveMarketDatabaseHandler;
import ca.prairesunapplications.evemarkethub.database.LoadDb;

/**
 * An Asyncronous task to handle app startup loading. Will be used to create DB, check for DB updates
 * and any other asset maintenance
 * Created by fluffy on 18/11/17.
 */

public class LoadingTask extends AsyncTask<String, Integer, Integer> {

	private final ProgressBar progressBar;
	private final LoadingTaskFinishedListener finishedListener;
	private final Context myContext;

	public LoadingTask(ProgressBar progressBar, @Nullable LoadingTaskFinishedListener finishedListener, Context context) {
		this.progressBar = progressBar;
		this.finishedListener = finishedListener;
		this.myContext = context;
	}

	@Override
	protected Integer doInBackground(String... strings) {
		Log.i("EveMarketHub", "Starting task");
		if(resourcesExist()) {
			// downloadResources();
		}
		return 1234;
	}

	private boolean resourcesExist() {
		return true;
	}

	protected void onPostExecute(Integer integer) {
		super.onPostExecute(integer);
		assert finishedListener != null;
		finishedListener.onTaskFinished();
	}

	@Override
	protected void onProgressUpdate(Integer... integers) {
		super.onProgressUpdate(integers);
		progressBar.setProgress(integers[0]);
	}

	private void downloadResources() {
		EveMarketDatabaseHandler handler = new EveMarketDatabaseHandler(myContext);
		handler.cleanSlate();
		new LoadDb(myContext);
	}

	public interface LoadingTaskFinishedListener {
		void onTaskFinished();
	}
}
