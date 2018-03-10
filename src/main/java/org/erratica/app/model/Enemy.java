package org.erratica.app.model;

public class Enemy {
	
	private int id;
	private int healthPoints;
	private int attackDamage;
	private String map;
	
	public Enemy(int id, int healthPoints, int attackDamage, String map) {
		this.id = id;
		this.healthPoints = healthPoints;
		this.attackDamage = attackDamage;
		this.map = map;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHealthPoints() {
		return healthPoints;
	}

	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
	}

	public int getAttackDamage() {
		return attackDamage;
	}

	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}
	
}
