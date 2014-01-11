package com.refinehumanity.zombieadventure;

import java.util.List;

public class PartyMembers {
	
	
	private int healthMax;
	private int moralMax;
	private int health;
	private int moral;
	private String sex;
	private String name;
	
	private static final String[] DEFAULT_NAMES_WOMAN = {"Jennifer", "Abigail", "Cindy", "Olivia", "Sally", "Ingela", "Angie", "Sarah", "Josephine", "Calipso", "Jay", "Kate",
		"Silvia", "Katja", "Nimi", "Kim", "Summer", "Hope", "Ji-eun", "Jackie", "Olive"};
	
	private static final String[] DEFAULT_NAMES_MEN = {"Robert", "Fransesco", "Ferdinand", "Enrique", "Gerald", "Tom", "Stewart", "Michael", "Tito", "Jack", "Alexander"
		, "Terry", "Phil", "Diego", "Clarence", "Bobby", "Sam", "Brosilini", "Kindell"};
	
	public PartyMembers() {
		//Every party member has a health value that starts between 15 and 20 and is their max health.
		//Each starts with a moral that decreases or increases based on 'morality' of chosen action.
		
		healthMax = 15 + (int) (Math.random() * 5);
		moralMax = 8 + (int) (Math.random() * 2);
		health= healthMax;
		moral=moralMax;
		
		//TODO stub
	}
	
	//On random character create, have a dialog pop up with a random default checkbox for sex, a type field
	//for name with the editText field already filled with a random DEFAULT NAME
	
	public String getSex() {
		//Prompt user for sex or let pick default.. 
		sex="Male";
		return sex;
	}
	
	public String getName() {
		//Prompt user for name or let pick default.. auto fill in default first
		int r = (int) (Math.random() * DEFAULT_NAMES_WOMAN.length);
		name = DEFAULT_NAMES_WOMAN[r];
		return name;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getMoral() {
		return moral;
	}
	
	public int modHealth(int deltaHealth) {
		if (health + deltaHealth > healthMax) {return healthMax;}
		else return health + deltaHealth;
	}
	
	public int modMoral(int deltaMoral) {
		if (moral + deltaMoral > moralMax) {return moralMax;}
		else return moral + deltaMoral;
	}
	
	public void refreshParty(List<PartyMembers> partyMemberList) {
		for (PartyMembers partyMember : partyMemberList) {
			//todo
		}
	}
	
	
	
}
