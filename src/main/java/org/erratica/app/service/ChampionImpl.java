package org.erratica.app.service;

import java.util.List;

import org.erratica.app.model.Champion;
import org.erratica.app.repository.ChampionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChampionImpl implements IChampion{
	
	@Autowired
	ChampionRepository championRepository;
	
	@Override
	public int countByIdAccQuery(int idAccount) {
		return championRepository.findByidAccount(idAccount).size();
	}

	@Override
	public void insertQuery(Champion champion) {
		championRepository.save(champion);		
	}

	@Override
	public List<Champion> findByidAccountQuery(int idAccount) {
		return championRepository.findByidAccount(idAccount);
	}

	@Override
	public void deleteByIdQuery(int idChampion) {
		championRepository.deleteById(idChampion);		
	}

	@Override
	public Champion findByIdQuery(int idChampion) {;
		return (championRepository.findById(idChampion).isPresent())?championRepository.findById(idChampion).get():null;
	}

}
