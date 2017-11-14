package adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import objects.Item;

/**
 * Created by fluffy on 13/11/17.
 */

public class searchAdapter extends ArrayAdapter<Item> {
    private Context myContext;

    public searchAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        myContext = context;
    }
}


