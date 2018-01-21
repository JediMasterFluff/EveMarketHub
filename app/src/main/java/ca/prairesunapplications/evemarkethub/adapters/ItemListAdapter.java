package ca.prairesunapplications.evemarkethub.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ca.prairesunapplications.evemarkethub.R;
import ca.prairesunapplications.evemarkethub.objects.Item;

/**
 * Created by fluffy on 20/01/18.
 */

public class ItemListAdapter extends BaseAdapter {

	private ArrayList<Item> list;
	private LayoutInflater inflater;

	public ItemListAdapter(Context context, ArrayList<Item> data) {
		this.list = data;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int i) {
		return list.get(i);
	}

	@Override
	public long getItemId(int i) {
		return list.indexOf(list.get(i));
	}

	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {

		ViewHolder holder;

		if(view == null) {

			holder = new ViewHolder();

			view = inflater.inflate(R.layout.item_list_entry, null);
			holder.itemName = view.findViewById(R.id.list_item_text_view);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		Item listItem = list.get(i);
		if(listItem != null) {
			if(holder.itemName != null) {
				holder.itemName.setText(listItem.getName());
			}
		}

		return view;
	}

	private static class ViewHolder {
		protected TextView itemName;
	}
}
