package com.refinehumanity.zombieadventure;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class Game extends Activity {

	//private int supplies;
	String[] situations = {"You get rabies and die.", "You are attacked by a zombie!", "You gain a party member", "You got cheetos.", "Crickets." };
	String[] partyMembers = {"Gerald", "Elizabeth", "Robert", "Periwinkle", "Alistaire", "Rebecca", "Civilized Zombie", "Rabid Cinnabon Employee" };
	String[] Items = {"Hot dog", "Cereal", "Silver Bullet", "Anchovies", "Balloon", "Shotgun", "Shotgun Shell", "Shotgun Shell", "Vampire Bat", "Sterile Gauze" };
	String[] Stats = {"Zombies killed: 7", "Arrows to the knee: 38", "Hobbits encountered: 0", "Maximum party size: 7", "Maximum days survived: 0" };
	
	List<PartyMembers> partyMembersList;
	List<String> groupList;
	List<String> childList;
	Map<String, List<String>> gameMenuCollection;
	ExpandableListView expListView;
	
	public boolean isFirstPass;
	public int day = 0;
	public int r = 0;
	public String SITUATION_KEY = "SITUATION_KEY";
	
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		updateViews();

	}
	
	protected void updateViews() {
		
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
		
		startGameLogic();

	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Music.play(this, R.raw.improvemaj);
		updateViews();
	}

	@Override
	protected void onPause() {
		super.onPause();
		Music.stop(this);
	}
	

	
	private void createGroupList() {
		groupList = new ArrayList<String>();
		groupList.add("Situation");
		groupList.add("Party Members");
		groupList.add("Items");
		groupList.add("Stats");
	}
	
	private void createCollection() {
		//Preparing the children for the group elements
		//Using string arrays
		PartyMembers mainCharacter = new PartyMembers();
		
		String[] situations = {"You get rabies and die.", "You are attacked by a zombie!", "You gain a party member", "You got cheetos.", "Crickets." };
		String[] partyMembers = {"Gerald", "Elizabeth", "Robert", "Periwinkle", "Alistaire", "Rebecca", "Civilized Zombie", "Rabid Cinnabon Employee",
				mainCharacter.getName(), "Sex: " + mainCharacter.getSex(), "Health: " + Integer.toString(mainCharacter.getHealth()), "Moral: " + Integer.toString(mainCharacter.getMoral())};
		String[] Items = {"Hot dog", "Cereal", "Silver Bullet", "Anchovies", "Balloon", "Shotgun", "Shotgun Shell", "Shotgun Shell", "Vampire Bat", "Sterile Gauze" };
		String[] Stats = {"Zombies killed: 7", "Arrows to the knee: 38", "Hobbits encountered: 0", "Maximum party size: 7", "Maximum days survived: 0" };
		gameMenuCollection = new LinkedHashMap<String, List<String>>();
		
		for (String gameItem : groupList) {
			if (gameItem.equals("Situation")) loadChild(situations);
			else if (gameItem.equals("Party Members")) loadChild(partyMembers);
			else if (gameItem.equals("Items")) loadChild(Items);
			else if (gameItem.equals("Stats")) loadChild(Stats);
			else loadChild(Stats);
			gameMenuCollection.put(gameItem, childList);
		}
	}
	
	private void loadChild(String[] gameItem) {
		childList = new ArrayList<String>();
		for (String childItem : gameItem) {
			childList.add(childItem);
		}
	}
	
	private void startGameLogic() {
		//Where game loop starts as needed
		
	}

	
	
	public int generateSituationNumber(boolean isFirstPass) {
		if (isFirstPass) {isFirstPass = false; return 0;}
		else return (int) (Math.random()*Situation.situationSummaryList.size());
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		//Inflates the menu
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
}
