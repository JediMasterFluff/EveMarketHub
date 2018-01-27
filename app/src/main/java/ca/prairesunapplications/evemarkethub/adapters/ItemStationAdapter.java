package ca.prairesunapplications.evemarkethub.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ca.prairesunapplications.evemarkethub.R;
import ca.prairesunapplications.evemarkethub.objects.Station;
import ca.prairesunapplications.evemarkethub.screens.StationDetails;

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
	public void onBindViewHolder(ViewHolder holder, final int position) {
		Station st = data.get(position);
		String price = st.getItemPrice(0);
		String name = st.getName();

		holder.price.setText(price);
		holder.name.setText(name);


		holder.cv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Context context = view.getContext();
				Intent intent = new Intent(context, StationDetails.class);

				Station st = data.get(position);

				intent.putExtra(StationDetails.STATION_ID, st.getId());

				context.startActivity(intent);
			}
		});
	}

	@Override
	public int getItemCount() {
		return data.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		CardView cv;
		TextView price;
		TextView name;

		public ViewHolder(View itemView) {
			super(itemView);
			cv = itemView.findViewById(R.id.item_station_card);
			price = itemView.findViewById(R.id.station_item_price);
			name = itemView.findViewById(R.id.station_name);
		}
	}
}