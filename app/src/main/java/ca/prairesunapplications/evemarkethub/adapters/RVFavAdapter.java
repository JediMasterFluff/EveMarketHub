package ca.prairesunapplications.evemarkethub.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import ca.prairesunapplications.evemarkethub.R;
import ca.prairesunapplications.evemarkethub.objects.Item;
import ca.prairesunapplications.evemarkethub.screens.ItemDetails;
import ca.prairesunapplications.evemarkethub.utils.SharedPreference;

/**
 * Created by fluffy on 19/11/17.
 */

public class RVFavAdapter extends RecyclerView.Adapter<RVFavAdapter.FavouriteViewHolder> {

    private final List<Item> favourites;
    private final SharedPreference preference;

    @Override
    public FavouriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_favourite_item_card,parent,false);
        return new FavouriteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FavouriteViewHolder holder, int position) {

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

    public static class FavouriteViewHolder extends RecyclerView.ViewHolder {
        final CardView cv;
        final TextView name;
        final TextView category;
        final TextView price;

        FavouriteViewHolder(View itemView){
            super(itemView);
            cv = itemView.findViewById(R.id.fav_cv);
            name = itemView.findViewById(R.id.fav_item_name);
            category = itemView.findViewById(R.id.fav_item_category);
            price = itemView.findViewById(R.id.fav_item_price);
        }
    }

    public RVFavAdapter(Context context, List<Item> items) {
        Context context1 = context;
        this.favourites = items;
        preference = new SharedPreference();
    }
}
