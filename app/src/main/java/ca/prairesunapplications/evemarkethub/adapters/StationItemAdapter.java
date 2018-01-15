package ca.prairesunapplications.evemarkethub.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ca.prairesunapplications.evemarkethub.R;
import ca.prairesunapplications.evemarkethub.objects.Item;

/**
 * Created by fluffy on 14/01/18.
 */

public class StationItemAdapter extends RecyclerView.Adapter<StationItemAdapter.ViewHolder> {

	private ArrayList<Item> data = new ArrayList<>();
	private LayoutInflater inflater;


	public StationItemAdapter(Context context, ArrayList<Item> list) {
		this.inflater = LayoutInflater.from(context);
		this.data = list;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = inflater.inflate(R.layout.card_station_item, parent);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		Item item = data.get(position);

		holder.name.setText(item.getName());
		holder.category.setText(item.getCategory_name());
		holder.price.setText(String.valueOf(item.getPrice()));
	}

	@Override
	public int getItemCount() {
		return data.size();
	}

	static class ViewHolder extends RecyclerView.ViewHolder {
		final CardView cv;
		final TextView name;
		final TextView category;
		final TextView price;
		final TextView avg_price;

		ViewHolder(View itemView) {
			super(itemView);
			cv = itemView.findViewById(R.id.station_item_cv);
			name = itemView.findViewById(R.id.station_item_name);
			category = itemView.findViewById(R.id.station_item_category);
			price = itemView.findViewById(R.id.station_item_price);
			avg_price = itemView.findViewById(R.id.station_item_avg_price);
		}
	}
}