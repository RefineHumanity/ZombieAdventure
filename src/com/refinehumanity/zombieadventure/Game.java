package com.refinehumanity.zombieadventure;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class Game extends Activity {

	public String SITUATION_KEY = "SITUATION_KEY";
	public String LOG_TAG = "LOG";
	public String PARTY_MEMBER_NAMES = "PARTY_MEMBER_NAMES_ARRAY";
	
	private int supplies;
	
	PartyMembers mainCharacter = new PartyMembers();
	public List<PartyMembers> partyMembersList;
	List<String> partyMembersNameList;
	
	List<String> itemsList;
	List<String> statsList;
	List<String> groupList;
	List<String> childList;
	Map<String, List<String>> gameMenuCollection;
	ExpandableListView expListView;
	

	
	public boolean isFirstPass;
	public int day = 0;
	public int r = 0;

	
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		Log.v(LOG_TAG, "onCreate called.");
		
		super.onCreate(savedInstanceState);

	}
	
	protected void updateViews() {
		
		startGameLogic(day);
		//This currently reloads situation every time the view changes from portrait to layout..
		r = generateSituationNumber(isFirstPass);
		setContentView(R.layout.game_main_screen);
		
		
		
		Situation currentSituation = new Situation();
		currentSituation.createSituationLists();

		TextView situationView = (TextView) findViewById(R.id.situation_main_screen_summary);
		situationView.setText(Situation.situationSummaryList.get(r));
		situationView.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(getBaseContext(), Situation.class);
				i.putExtra (Situation.CURRENT_SITUATION_KEY, r);
				startActivity(i);
			}
		});
		
		//Creating the group and child lists and putting in collection
		createGroupList();
		
		createCollection();
		
		expListView = (ExpandableListView) findViewById(R.id.gameMenuList);
		final ExpandableListAdapter expListAdapter = new ExpandableListAdapter (
				this, groupList, gameMenuCollection);
		expListView.setAdapter(expListAdapter);
		
		expListView.setOnChildClickListener(new OnChildClickListener() {
			
			public boolean onChildClick(ExpandableListView parent, View v,
				int groupPosition, int childPosition, long id) {
				final String selected = (String) expListAdapter.getChild(groupPosition, childPosition);
				Toast.makeText(getBaseContext(), selected, Toast.LENGTH_LONG).show();
				return true;
			}
		});
		expListView.expandGroup(0);

	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Music.play(this, R.raw.improvemaj);
		updateViews();
		Log.v(LOG_TAG, "OnResume called.");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Music.stop(this);
		Log.v(LOG_TAG, "onPause called.");
	}
	

	
	private void createGroupList() {
		groupList = new ArrayList<String>();
		groupList.add("Party Members");
		groupList.add("Items");
		groupList.add("Stats");
	}
	
	private void createCollection() {
		//Preparing the children for the group elements
		
		updateParty();
		updateItems();
		updateStats();
		
		gameMenuCollection = new LinkedHashMap<String, List<String>>();
		
		for (String gameItem : groupList) {
			if (gameItem.equals("Party Members")) loadChild(partyMembersNameList);
			else if (gameItem.equals("Items")) loadChild(itemsList);
			else if (gameItem.equals("Stats")) loadChild(statsList);
			else return;
			gameMenuCollection.put(gameItem, childList);
		}
	}
	
	private void loadChild(List<String> menuChildList) {
		childList = new ArrayList<String>();
		for (String childItem : menuChildList) {
			childList.add(childItem);
		}
	}
	
	private void loadChild(String[] menuChildArray) {
		childList = new ArrayList<String>();
		for (String childItem : menuChildArray) {
			childList.add(childItem);
		}
	}
	
	private void loadChild(ArrayList<String> menuChildArrayList) {
		childList = new ArrayList<String>();
		for (String childItem : menuChildArrayList) {
			childList.add(childItem);
		}
	}
	
	public void updateParty() {
		partyMembersNameList = new ArrayList<String>();
		for (PartyMembers partyMembers : partyMembersList) {
			partyMembersNameList.add(partyMembers.getName());
		}
		//Later add updates to views for health and moral here.
	}
	
	public void updateItems() {
		itemsList = new ArrayList<String>();
		itemsList.add("Hot dog");
	}
	
	public void updateStats() {
		statsList = new ArrayList<String>();
		statsList.add("Zombies killed: 7");
	}
	
	//updateItems and updateStats list?

	
	public int generateSituationNumber(boolean isFirstPass) {
		if (isFirstPass) {isFirstPass = false; return 0;}
		else return (int) (Math.random()*Situation.situationSummaryList.size());
		//return Situation.generateSituationNumber();
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		//Inflates the menu
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void startGameLogic(int mDay) {
		//Where game loop starts as needed
		Log.v(LOG_TAG, "startGameLogic called, day = " + day);
		
		if (mDay == 0) {
			partyMembersList = new ArrayList<PartyMembers>();
			mainCharacter.createName();	
			partyMembersList.add(mainCharacter);
			day = 1;
		}
		else {
			return;			
		}
		
	}
	
	/*@Override
	public void onSaveInstanceState (Bundle savedInstanceState) {
		//save user's current game state
		savedInstanceState.putStringArrayList(PARTY_MEMBER_NAMES, partyMembersNameList<String>);
	}
	*/
}
