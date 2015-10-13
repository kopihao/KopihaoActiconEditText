package com.kopihao.aet.demo;

import com.kopihao.cet.demo.R;
import com.view.plugin.aet.ActiconEditText;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * This project demo an EditText which able to do sth with its icon<br>
 * This UI component is absolutely inherited from native EditText.<br>
 * When this UI component is needed, my first thought is<br>
 * A ViewGroup which contain EditText + ImageView.<br>
 * Afterward, I abandoned the idea and ask myself why not EditText?<br>
 * Few snippet online, inspired and helped me to build this UI component. <br>
 * Thanks to all those great Custom UI pioneer out here.<br>
 * In the end, here is my version.<br>
 * Hope you enjoy.<br>
 *
 * <u>Why use this</u><br>
 * Custom UI component as a View rather than a View Group<br>
 * Inherit from EditText<br>
 * Native EditText attributes are usable directly in xml<br>
 * Provide method to change Drawable on the fly<br>
 * </p>
 *
 * <p>
 * <u>Things to take note:</u><br>
 * Drawable has to be resized (in xhdpi, hdpi...) wisely.<br>
 * Drawable might stretch EditText bigger.<br>
 * </p>
 *
 * @author Jasper
 *
 */
public class Activity_Main extends Activity {

	private ActiconEditText cetInput;
	private ImageView ivIcon1;
	private ImageView ivIcon2;
	private ImageView ivIcon3;
	private ImageView ivIcon4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_main);
		cetInput = (ActiconEditText) findViewById(R.id.cetInput);
		ivIcon1 = (ImageView) findViewById(R.id.ivIcon1);
		ivIcon2 = (ImageView) findViewById(R.id.ivIcon2);
		ivIcon3 = (ImageView) findViewById(R.id.ivIcon3);
		ivIcon4 = (ImageView) findViewById(R.id.ivIcon4);

		ivIcon1.setOnClickListener(oic_ivIcon);
		ivIcon2.setOnClickListener(oic_ivIcon);
		ivIcon3.setOnClickListener(oic_ivIcon);
		ivIcon4.setOnClickListener(oic_ivIcon);

	}

	OnClickListener oic_ivIcon = new OnClickListener() {
		@Override
		public void onClick(View v) {
			cetInput.clearFocus();
			v.requestFocus();
			switch (v.getId()) {

			case R.id.ivIcon1:
				cetInput.setActicon(android.R.drawable.ic_delete);
				break;
			case R.id.ivIcon2:
				cetInput.setActicon(android.R.drawable.ic_btn_speak_now);

				break;
			case R.id.ivIcon3:
				cetInput.setActicon(android.R.drawable.ic_input_add);

				break;
			case R.id.ivIcon4:
				cetInput.setActicon(android.R.drawable.ic_input_get);
				break;

			default:
				return;
			}
			Toast.makeText(Activity_Main.this, "CET.Icon changed.", Toast.LENGTH_SHORT).show();
			cetInput.requestFocus();

		}
	};
}
