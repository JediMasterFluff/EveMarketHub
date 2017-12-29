package ca.prairesunapplications.evemarkethub.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ca.prairesunapplications.evemarkethub.R;
import ca.prairesunapplications.evemarkethub.objects.Station;

/**
 * Created by fluffy on 29/12/17.
 */

public class FavStationAdapter extends RecyclerView.Adapter<FavStationAdapter.ViewHolder> {

	ArrayList<Station> data = new ArrayList<>();
	LayoutInflater inflater;

	public FavStationAdapter(Context context, ArrayList<Station> list) {
		this.data = list;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = inflater.inflate(R.layout.activity_main_favourite_station_card, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		Station st = data.get(position);
	}

	@Override
	public int getItemCount() {
		return data.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {

		public ViewHolder(View view) {
			super(view);
		}
	}
}
