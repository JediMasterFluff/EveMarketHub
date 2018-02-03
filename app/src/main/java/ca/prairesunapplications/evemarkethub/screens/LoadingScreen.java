package ca.prairesunapplications.evemarkethub.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import ca.prairesunapplications.evemarkethub.R;
import ca.prairesunapplications.evemarkethub.tasks.LoadingTask;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class LoadingScreen extends AppCompatActivity implements LoadingTask.LoadingTaskFinishedListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading_screen);
		ProgressBar progressBar = findViewById(R.id.progressBar);

		//this.getSharedPreferences(SharedPreference.FAV_PREF_NAME, 0).edit().clear().commit();

		new LoadingTask(progressBar, this, this).execute();

	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
	}


	@Override
	public void onTaskFinished() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}
}
