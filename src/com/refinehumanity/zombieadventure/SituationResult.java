package com.refinehumanity.zombieadventure;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SituationResult extends Activity {
	
	public static final String CURRENT_SITUATION_RESULT_KEY = "com.refinehumanity.zombieadventure.situation.SITUATION_RESULT_KEY";
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.situation_result);
		
		TextView situationResultTextView = (TextView) findViewById(R.id.situation_result_text);
		((TextView) situationResultTextView.findViewById(R.id.situation_result_text)).setText(getIntent().getStringExtra(CURRENT_SITUATION_RESULT_KEY));
		
		Button situationResultButton = (Button) findViewById(R.id.situation_result_button);
		situationResultButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				finish();
			}
		});
	}

}
