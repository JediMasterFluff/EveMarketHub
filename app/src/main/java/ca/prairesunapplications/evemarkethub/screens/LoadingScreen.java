package ca.prairesunapplications.evemarkethub.screens;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        new LoadingTask(new ProgressBar(this),null, this).execute();

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }


    @Override
    public void onTaskFinished() {

    }
}
