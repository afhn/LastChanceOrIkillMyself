package org.erratica.app.repository;

import java.util.List;

import org.erratica.app.model.Champion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChampionRepository extends JpaRepository<Champion, Integer> {
	List <Champion> findByidAccount(int idAccount);
}
