package org.erratica.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="progress")
public class Progress {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String map;
	private String levelMap;
	private String time;
	private int idChamp;
	
	public Progress() {
		this.map="";
		this.levelMap="";
		this.time="";		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	public String getLevelMap() {
		return levelMap;
	}

	public void setLevelMap(String levelMap) {
		this.levelMap = levelMap;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getIdChamp() {
		return idChamp;
	}

	public void setIdChamp(int idChamp) {
		this.idChamp = idChamp;
	}

	@Override
	public String toString() {
		return "Progress [id=" + id + ", map=" + map + ", levelMap=" + levelMap + ", time=" + time + ", idChamp="
				+ idChamp + "]";
	}
	
}
