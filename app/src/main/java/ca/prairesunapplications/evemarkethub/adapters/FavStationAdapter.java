package ca.prairesunapplications.evemarkethub.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ca.prairesunapplications.evemarkethub.R;
import ca.prairesunapplications.evemarkethub.objects.Station;
import ca.prairesunapplications.evemarkethub.screens.StationDetails;

/**
 * Created by fluffy on 29/12/17.
 */

public class FavStationAdapter extends RecyclerView.Adapter<FavStationAdapter.ViewHolder> {

	private ArrayList<Station> data = new ArrayList<>();
	private LayoutInflater inflater;

	public FavStationAdapter(Context context, ArrayList<Station> list) {
		this.data = list;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = inflater.inflate(R.layout.card_favourite_station, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		Station st = data.get(position);
		holder.cv.setTag(st.getId());
		final int id = (Integer) holder.cv.getTag();
		holder.cv.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				Context context = view.getContext();
				Intent intent = new Intent(context, StationDetails.class);
				intent.putExtra(StationDetails.STATION_ID, id);
				context.startActivity(intent);
			}
		});
	}

	@Override
	public int getItemCount() {
		return data.size();
	}

	static class ViewHolder extends RecyclerView.ViewHolder {
		final CardView cv;
		Station st;

		ViewHolder(View view) {
			super(view);
			cv = view.findViewById(R.id.fav_station_card);

		}
	}
}
