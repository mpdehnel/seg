package com.data;

import java.util.List;

import com.vectorone.CacheShowActivity;
import com.vectorone.MenuActivity;
import com.vectorone.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class ListAdapter extends ArrayAdapter<Model> {

	private final List<Model> list;
	private final Activity context;

	public ListAdapter(Activity context, List<Model> list) {
		super(context, R.layout.cache_list_element, list);
		this.context = context;
		this.list = list;
	}

	static class ViewHolder {
		protected TextView text;
		protected CheckBox checkbox;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = null;
		if (convertView == null) {
			LayoutInflater inflator = context.getLayoutInflater();
			view = inflator.inflate(R.layout.cache_list_element, null);
			final ViewHolder viewHolder = new ViewHolder();
			viewHolder.text = (TextView) view.findViewById(R.id.label);
			if(DataClass.caches.get(position).getCach().isFound()){
				viewHolder.text.setTextColor(Color.GREEN);
			}else{
				viewHolder.text.setTextColor(Color.BLACK);
			}
			
			viewHolder.text.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					intent = new Intent(getContext(), CacheShowActivity.class);
					intent.putExtra("CacheIndex", position);
					getContext().startActivity(intent);
				}
			});

			viewHolder.checkbox = (CheckBox) view.findViewById(R.id.check);
			viewHolder.checkbox
					.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

						@Override
						public void onCheckedChanged(CompoundButton buttonView,
								boolean isChecked) {
							Model element = (Model) viewHolder.checkbox
									.getTag();
							element.setSelected(buttonView.isChecked());

							if (!isChecked)
								DataClass.routing = -1;

						}
					});

			view.setTag(viewHolder);
			viewHolder.checkbox.setTag(list.get(position));
		} else {
			view = convertView;
			((ViewHolder) view.getTag()).checkbox.setTag(list.get(position));
		}
		ViewHolder holder = (ViewHolder) view.getTag();
		holder.text.setText(list.get(position).getName());
		holder.checkbox.setChecked(list.get(position).isSelected());
		return view;
	}
}