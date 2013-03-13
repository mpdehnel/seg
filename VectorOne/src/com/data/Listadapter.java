package com.data;

import java.util.List;

import com.vectorone.CacheSelectActivity;
import com.vectorone.CacheShowActivity;
import com.vectorone.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class ListAdapter extends ArrayAdapter<Model> {

	private final List<Model> list;
	private final Context context;
	private final CacheSelectActivity sec;

	public ListAdapter(Context context, List<Model> list,CacheSelectActivity sec) {
		super(context, R.layout.cache_list_element, list);
		this.context = context;
		this.list = list;
		this.sec=sec;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		// if (convertView == null) {
		LayoutInflater inflator = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflator
				.inflate(R.layout.cache_list_element, parent, false);
		TextView text = (TextView) view.findViewById(R.id.label);
		final CheckBox box = (CheckBox) view.findViewById(R.id.check);
		Typeface font = Typeface.createFromAsset(view.getContext().getAssets(),
				"fonts/bebas.ttf");

		text.setTypeface(font);
		text.setText(list.get(position).getCach().getName());
		if (list.get(position).getCach().isFound()) {
			text.setTextColor(Color.GREEN);
		} else {
			text.setTextColor(Color.parseColor("#DECD87"));
		}
			box.setChecked(list.get(position).isSelected());
		

		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.i("ADAPTER", "posi" + position);
				Intent intent = new Intent();
				intent = new Intent(getContext(), CacheShowActivity.class);
				intent.putExtra("CacheIndex", position);
				sec.finish();
				getContext().startActivity(intent);
			}
		});
		box.setTag(list.get(position));
		box.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				Model element = (Model) box.getTag();
				element.setSelected(buttonView.isChecked());

				if (!isChecked) {
					DataClass.routing = -1;
				}

			}
		});

		return view;
	}
}