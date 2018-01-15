package ca.prairesunapplications.evemarkethub.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ca.prairesunapplications.evemarkethub.R;
import ca.prairesunapplications.evemarkethub.objects.Station;

public class ItemStationAdapter extends RecyclerView.Adapter<ItemStationAdapter.ViewHolder> {

	private ArrayList<Station> data = new ArrayList<>();
	private LayoutInflater inflater;

	public ItemStationAdapter(Context context, ArrayList<Station> stations) {
		this.inflater = LayoutInflater.from(context);
		this.data = stations;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = inflater.inflate(R.layout.card_item_details_item_station, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		Station st = data.get(position);
		String price = st.getItemPrice(0);
		String name = st.getName();

		holder.price.setText(price);
		holder.name.setText(name);
	}

	@Override
	public int getItemCount() {
		return data.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		TextView price;
		TextView name;

		public ViewHolder(View itemView) {
			super(itemView);
			price = itemView.findViewById(R.id.station_item_price);
			name = itemView.findViewById(R.id.station_name);
		}
	}
}