package org.erratica.app.repository;

import java.util.List;

import org.erratica.app.model.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgressRepository extends JpaRepository<Progress, Integer> {
	List <Progress> findByidChamp(int idChampion);
}
