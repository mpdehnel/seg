package com.data;

import com.vectorone.R;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SpinnerAdapter extends ArrayAdapter<String> {
	private Context context;
	private String[] strings;

	public SpinnerAdapter(Context context, int textViewResourceId,
			String[] objects) {
		
		super(context, textViewResourceId, objects);
		this.context=context;
		this.strings=objects;
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		return getCustomView(position, convertView, parent);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return getCustomView(position, convertView, parent);
	}

	public View getCustomView(int position, View convertView, ViewGroup parent) {
		Typeface font = Typeface
				.createFromAsset(context.getAssets(), "fonts/bebas.ttf");
		int textcolor = Color.parseColor("#DECD87");
		
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View row = inflater.inflate(R.layout.spinner_row, parent, false);
		RelativeLayout layout=(RelativeLayout) row.findViewById(R.id.relativspinner);
		layout.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.backgroundsmall));
		TextView label = (TextView) row.findViewById(R.id.spinnerElement);
		label.setTextSize(17);
		label.setTypeface(font);
		label.setTextColor(textcolor);
		label.setText(strings[position]);

		return row;
	}
}