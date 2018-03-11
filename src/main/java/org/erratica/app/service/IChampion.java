package org.erratica.app.service;

import java.util.List;

import org.erratica.app.model.Champion;

public interface IChampion {
	public int countByIdAccQuery(int idAccount);
	public void insertQuery(Champion champion);
	public List<Champion> findByidAccountQuery(int idAccount);
	public void deleteByIdQuery(int idChampion);
	public Champion findByIdQuery(int idChampion);
	public Champion findBychampionName(String championName);
}
