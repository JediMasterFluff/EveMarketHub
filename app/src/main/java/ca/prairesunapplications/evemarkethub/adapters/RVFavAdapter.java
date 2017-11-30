package ca.prairesunapplications.evemarkethub.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import ca.prairesunapplications.evemarkethub.R;
import ca.prairesunapplications.evemarkethub.objects.Item;
import ca.prairesunapplications.evemarkethub.screens.ItemDetails;
import ca.prairesunapplications.evemarkethub.utils.ItemTouchHelperViewHolder;
import ca.prairesunapplications.evemarkethub.utils.OnStartDragListener;
import ca.prairesunapplications.evemarkethub.utils.SharedPreference;

/**
 * Simple RecyclerView.Adapter that implements {@link ItemTouchHelperAdapter} to respond to move and
 * dismiss events from a {@link android.support.v7.widget.helper.ItemTouchHelper}.
 *
 * Used to display a users selected favourited items on the main screen.
 *
 * @author Christian Bell (Fluffy)
 */

public class RVFavAdapter extends RecyclerView.Adapter<RVFavAdapter.FavouriteViewHolder> implements ItemTouchHelperAdapter {

    private final List<Item> favourites;

    private SharedPreference preference;

    private final OnStartDragListener mDragListener;

    private Context mContext;

    public RVFavAdapter(Context context, List<Item> items, SharedPreference preference, OnStartDragListener dragListener) {
        this.favourites = items;
        this.mContext = context;
        this.preference = preference;
        this.mDragListener = dragListener;
    }

    @Override
    public FavouriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_favourite_item_card, parent, false);
        return new FavouriteViewHolder(v);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(final FavouriteViewHolder holder, int position) {

        Item i = getItem(position);

        holder.cv.setTag(i.getId());
        final int id = (Integer) holder.cv.getTag();
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, ItemDetails.class);
                intent.putExtra("Item ID", id);
                context.startActivity(intent);


            }
        });

        holder.cv.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    mDragListener.onStartDrag(holder);
                    view.performClick();
                }
                return true;
            }

        });

        holder.name.setText(i.getName());
        holder.category.setText(i.getCategory_name());
        holder.price.setText(String.format(Locale.CANADA, "%1$,.2f", i.getPrice()));
    }


    private Item getItem(int position) {
        return favourites.get(position);
    }

    @Override
    public int getItemCount() {
        return favourites.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(favourites, fromPosition, toPosition);
        preference.saveFavourites(mContext, favourites);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        favourites.remove(position);
        preference.saveFavourites(mContext, favourites);
        notifyItemRemoved(position);
    }

    public static class FavouriteViewHolder extends RecyclerView.ViewHolder implements
            ItemTouchHelperViewHolder {
        final CardView cv;
        final TextView name;
        final TextView category;
        final TextView price;

        public FavouriteViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.fav_cv);
            name = itemView.findViewById(R.id.fav_item_name);
            category = itemView.findViewById(R.id.fav_item_category);
            price = itemView.findViewById(R.id.fav_item_price);
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }
}
