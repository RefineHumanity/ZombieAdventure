package com.refinehumanity.zombieadventure;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

//Class for all of the situations (random / scripted events)
//@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Situation extends Activity {
	
	//Summary list is for game_main_screen text, text list is for Situation activity scrollview text,
	//choice one, two, three are for choice text at bottom of dialog's view
	public static  List<String> situationSummaryList = new ArrayList<String>();
	public static  List<String> situationTextList = new ArrayList<String>();
	public static  List<String> situationChoiceOneList = new ArrayList<String>();
	public static  List<String> situationChoiceTwoList = new ArrayList<String>();
	public static  List<String> situationChoiceThreeList = new ArrayList<String>();
	
	public String situationResultText = "";
	public static final String CURRENT_SITUATION_KEY = 
			"com.refinehumanity.zombieadventure.SITUATION_KEY";
	public static final String SITUATION_RESULT_KEY = "SITUATION_RESULT_KEY";
	
	public String currentSituationKey;
	public int r = 7;
	
	private boolean situationUnfinished = true;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		updateViews();
	}
	
	
	protected void updateViews() {
		setContentView(R.layout.situation);
		
		TextView situationTextView = (TextView) findViewById(R.id.situation_text);
		((TextView) situationTextView.findViewById(R.id.situation_text)).setText(situationTextList.get(getIntent().getIntExtra(CURRENT_SITUATION_KEY, r)));
		TextView situationChoiceOneView = (TextView) findViewById(R.id.situation_choice_1);
		((TextView) situationChoiceOneView.findViewById(R.id.situation_choice_1)).setText(situationChoiceOneList.get(getIntent().getIntExtra(CURRENT_SITUATION_KEY, r)));
		TextView situationChoiceTwoView = (TextView) findViewById(R.id.situation_choice_2);
		((TextView) situationChoiceTwoView.findViewById(R.id.situation_choice_2)).setText(situationChoiceTwoList.get(getIntent().getIntExtra(CURRENT_SITUATION_KEY, r)));
		
		
		situationChoiceOneView.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				situationUnfinished=false;
				
				//Find way to pass a lot of info back to main screen
				//but that can vary with each situation.. 
				//For now, just close this activity
				finish();
				Intent intent = new Intent(getBaseContext(), SituationResult.class);
				intent.putExtra(SituationResult.CURRENT_SITUATION_RESULT_KEY, situationResult(getIntent().getIntExtra(CURRENT_SITUATION_KEY, r), 1));
				startActivity(intent);
			}
		});
		
		situationChoiceTwoView.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				situationUnfinished=false;
				//Find way to pass a lot of info back to main screen
				//but that can vary with each situation.. 
				//For now, just close this activity
				finish();
				Intent intent = new Intent(getBaseContext(), SituationResult.class);
				intent.putExtra(SituationResult.CURRENT_SITUATION_RESULT_KEY, situationResult(getIntent().getIntExtra(CURRENT_SITUATION_KEY, r), 2));
				startActivity(intent);
			}
		});
		
		
		//view.setAlpha((float) .7);
	}
	
	protected void onResume() {
		super.onResume();
		updateViews();
	}
		

	Map<String, List<String>> situationMap;
	public Situation() {
		//TODO
	}
	
	private void startSituation1() {
		//TODO
	}
	
	public void createSituationLists() {
		//Currently 10 situations
		
		//Each situation list is a string[] with a situation summary for the main game screen, 
		//a situation text for the situation activity, and at least two choices for the situation that occurs
		String[] situationSummaryArray = {"Situation 1", "Situation 2", "Situation 3", "Situation 4", "Situation 5", "Situation 6", "Situation 7", "Situation 8", 
				"Situation 9", "Situation 10"};
		for (String s : situationSummaryArray) {
			situationSummaryList.add(s);
		}
		
		String[] situationTextArray = {"A zombie attacks you.", "You feel unhappy.", "Situation 3", "Situation 4", "Situation 5", "Situation 6", "Situation 7", "Situation 8", 
				"Situation 9", "Situation 10"};
		for (String s : situationTextArray) {
			situationTextList.add(s);
		}
		String[] situationChoiceOneArray = {"Kill it.", "Alright then.", "Situation 3", "Situation 4", "Situation 5", "Situation 6", "Situation 7", "Situation 8", 
				"Situation 9", "Situation 10"};
		for (String s : situationChoiceOneArray) {
			situationChoiceOneList.add(s);
		}
		String[] situationChoiceTwoArray = {"Run!", "Smile!", "Situation 3", "Situation 4", "Situation 5", "Situation 6", "Situation 7", "Situation 8", 
				"Situation 9", "Situation 10"};
		for (String s : situationChoiceTwoArray) {
			situationChoiceTwoList.add(s);
		}
		
	}
	
	public String situationResult(int mSituationKey, int choice ) {
		
		switch(mSituationKey) {
		case 0:
			if (choice == 1) {
				String s = "You killed the zombie!";
				//Put alterations to partyMembers here, same for all situations
				return s;						
			}
			if (choice == 2) {
				String s = "You ran from the zombie! Luckily, it wasn't all that fast and you escaped with most of your limbs.";
				return s;
			}
			else return "This is default text";
		default: 
			return "This is default text because the situation doesn't exist yet.";
		
		}
			
		
		
	}
	
	public void onBackPressed() {
		//TODO
	}
	
}

	
