package org.erratica.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="champion")
public class Champion {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String championName;
	private int attackDamage;
	private int defense;
	private int healthPoints;
	private int mana;
	private int experience;
	private int level;
	private int idAccount;
	
	public Champion(String championName, int idAccount) {
		this.championName = championName;
		this.idAccount = idAccount;
		this.attackDamage = 5;
		this.defense = 0;
		this.healthPoints = 200;
		this.mana = 100;
		this.experience=0;
		this.level=1;
	}
	
	public Champion() {
		this.attackDamage = 5;
		this.defense = 0;
		this.healthPoints = 200;
		this.mana = 100;
		this.experience=0;
		this.level=1;
	}
	
	public String getChampionName() {
		return championName;
	}

	public void setChampionName(String championName) {
		this.championName = championName;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getAttackDamage() {
		return attackDamage;
	}

	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getHealthPoints() {
		return healthPoints;
	}

	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}

	@Override
	public String toString() {
		return "Champion [id=" + id + ", championName=" + championName + ", attackDamage=" + attackDamage + ", defense="
				+ defense + ", healthPoints=" + healthPoints + ", mana=" + mana + ", experience=" + experience
				+ ", level=" + level + ", idAccount=" + idAccount + "]";
	}

	
}
