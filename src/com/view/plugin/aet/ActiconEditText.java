package com.view.plugin.aet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.text.Editable;
import android.text.TextUtils.TruncateAt;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class ActiconEditText extends EditText implements View.OnTouchListener, TextWatcher, View.OnFocusChangeListener {

	private Drawable acticon;
	private boolean hasFocus;
	private OnActiconClickListener oic_acticon;

	public ActiconEditText(final Context context) {
		super(context);
		initView(context);
	}

	public ActiconEditText(final Context context, final AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	public ActiconEditText(final Context context, final AttributeSet attrs, final int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
	}

	public void setActicon(Drawable d) {
		this.invalidateDrawable(d);
		this.acticon = d;
		d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
		this.refreshDrawableState();
		this.invalidate();
		this.requestLayout();

	}

	public void setActicon(int id) {
		this.invalidateDrawable(acticon);
		this.acticon = ResourcesCompat.getDrawable(getResources(), id, null);
		acticon.setBounds(0, 0, acticon.getIntrinsicWidth(), acticon.getIntrinsicHeight());
		this.refreshDrawableState();
		this.invalidate();
		this.requestLayout();
	}

	// --------------$$$$$$$$$$$$$$$4---------------

	@SuppressLint("ClickableViewAccessibility")
	protected void initView(final Context context) {
		acticon = ResourcesCompat.getDrawable(getResources(), android.R.drawable.presence_offline, null);
		acticon.setBounds(0, 0, acticon.getIntrinsicWidth(), acticon.getIntrinsicHeight());
		this.setOnTouchListener(this);
		this.addTextChangedListener(this);
		this.setOnFocusChangeListener(this);
		this.setSingleLine(true);
		this.setEllipsize(TruncateAt.END);
		this.setOnActiconClickListener(new OnActiconClickListener() {
			@Override
			public void onClick(String input) {
				Log.d("Jasper/xxx", "Touch me nae nae");
			}
		});
	}

	public void setOnActiconClickListener(@NonNull OnActiconClickListener l) {
		oic_acticon = l;
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public void onFocusChange(final View v, final boolean hasFocus) {
		this.hasFocus = hasFocus;

		// Only show clearing icon if the view is focussed
		if (hasFocus) {
			this.setCompoundDrawables(null, null, this.getText().toString().isEmpty() ? null : acticon, null);
		} else {
			this.setCompoundDrawables(null, null, null, null);
		}
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouch(final View v, final MotionEvent event) {
		if (this.getCompoundDrawables()[2] == null) {
			return false;
		}
		if (event.getAction() != MotionEvent.ACTION_UP) {
			return false;
		}

		if (event.getX() > this.getWidth() - this.getPaddingRight() - acticon.getIntrinsicWidth()) {
			oic_acticon.onClick(this.getText() + "");
			this.setCompoundDrawables(null, null, null, null);
			this.clearFocus();
			InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(this.getWindowToken(), 0);
		}

		this.setEnabled(true);
		this.setSelection(this.getText().length());
		return false;
	}

	@Override
	public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {
		// Nothing to do
	}

	@Override
	public void onTextChanged(final CharSequence text, final int start, final int lengthBefore, final int lengthAfter) {
		super.onTextChanged(text, start, lengthBefore, lengthAfter);
		// If it is not in focus, the text was added programmatically and we
		// should not display the clear icon
		if (!this.hasFocus) {
			return;
		}

		this.setCompoundDrawables(null, null, this.getText().toString().isEmpty() ? null : acticon, null);
	}

	@Override
	public void afterTextChanged(final Editable s) {
		// Nothing to do
	}

	public interface OnActiconClickListener {
		public void onClick(String input);
	}
}