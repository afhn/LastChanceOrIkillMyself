package org.erratica.app.service;

import java.util.List;

import org.erratica.app.model.Progress;

public interface IProgress {

	public void insertQuery(Progress prog);
	public Progress findById(int idProgress);
	public List<Progress> findAllByIdChamp(int idChampion);
	public void deleteQuery(int idProgress);
	
}
