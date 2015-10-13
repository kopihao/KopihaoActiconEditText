package com.view.plugin.aet;

import android.content.Context;
import android.util.AttributeSet;

public class YetAnotherAET extends ActiconEditText {

	public YetAnotherAET(final Context context) {
		super(context);
	}

	public YetAnotherAET(final Context context, final AttributeSet attrs) {
		super(context, attrs);
	}

	public YetAnotherAET(final Context context, final AttributeSet attrs, final int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void initView(Context context) {
		super.initView(context);
		setActicon(android.R.drawable.btn_star_big_on);
	}

}
