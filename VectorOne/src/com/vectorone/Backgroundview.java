package com.vectorone;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;

public class Backgroundview extends View {

	public Backgroundview(Context context, Drawable bar) {
		super(context);
		super.setBackgroundDrawable(bar);
	}

}