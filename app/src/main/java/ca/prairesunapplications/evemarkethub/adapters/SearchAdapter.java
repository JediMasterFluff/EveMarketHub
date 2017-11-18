package ca.prairesunapplications.evemarkethub.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import ca.prairesunapplications.evemarkethub.R;

/**
 * Created by fluffy on 13/11/17.
 */

@SuppressWarnings("ALL")
public class SearchAdapter extends CursorAdapter {


    public SearchAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    // Used to create a new view per-item created
    // No data is set here, just initial creation
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_search, viewGroup, false);
    }

    // Binds data from task to each view
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView name = view.findViewById(R.id.itemSearchName);
        TextView desc = view.findViewById(R.id.itemSearchDesc);

        // get data from cursor
        String itemName = cursor.getString(cursor.getColumnIndexOrThrow("name")); // change name to actual name of final column name
        String itemDesc = cursor.getString(cursor.getColumnIndexOrThrow("description")); // change description to name of final column name

        name.setText(itemName);
        desc.setText(itemDesc);
    }
}


