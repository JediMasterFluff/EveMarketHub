package ca.prairesunapplications.evemarkethub.adapters;

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

/**
 * Created by fluffy on 19/11/17.
 */

public class RVFavAdapter extends RecyclerView.Adapter<RVFavAdapter.FavouriteViewHolder> {

    @Override
    public FavouriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_favourite_item_card,parent,false);
        FavouriteViewHolder fvh = new FavouriteViewHolder(v);
        return fvh;
    }

    @Override
    public void onBindViewHolder(FavouriteViewHolder holder, int position) {
        holder.cv.setTag(items.get(position).getId());
        holder.name.setText(items.get(position).getName());
        holder.category.setText(items.get(position).getCategory_name());
        holder.price.setText(String.format(Locale.CANADA,"%1$,.2f",items.get(position).getPrice()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class FavouriteViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView name;
        TextView category;
        TextView price;

        FavouriteViewHolder(View itemView){
            super(itemView);
            cv = itemView.findViewById(R.id.fav_cv);
            name = itemView.findViewById(R.id.fav_item_name);
            category = itemView.findViewById(R.id.fav_item_category);
            price = itemView.findViewById(R.id.fav_item_price);
        }
    }

    List<Item> items;

    public RVFavAdapter(List<Item> items){
        this.items = items;
    }
}
