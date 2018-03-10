package org.erratica.app.service;

import java.util.List;

import org.erratica.app.model.Champion;

public interface IChampion {
	public int countByIdAcc(int idAcc);
	public void insertQuery(Champion champ);
	public List<Champion> findByidAccount(int idAccount);
	public void deleteById(int id);
	public Champion findById(int idChamp);
}
