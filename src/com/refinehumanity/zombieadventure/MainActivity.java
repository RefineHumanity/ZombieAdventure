package com.refinehumanity.zombieadventure;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Setting up onClickListeners for start menu buttons
		View newButton = findViewById(R.id.new_button);
		newButton.setOnClickListener(this);

		View aboutButton = findViewById(R.id.about_button);
		aboutButton.setOnClickListener(this);

		View exitButton = findViewById(R.id.exit_button);
		exitButton.setOnClickListener(this);

		
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.new_button:
			openNewGameDialog();
			break;
		case R.id.about_button:
			//Fix later
			/*
			Intent i = new Intent(this, About.class);
			startActivity(i);
			break;
			*/
		case R.id.exit_button:
			finish();
			break;
		}
	}
	
	private void openNewGameDialog() {
		//TODO stuff
		//For now just start game activity
		Intent i = new Intent(this, Game.class);
		startActivity(i);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
