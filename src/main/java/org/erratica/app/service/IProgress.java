package org.erratica.app.service;

import java.util.List;

import org.erratica.app.model.Progress;

public interface IProgress {

	public void insertQuery(Progress progress);
	public Progress findByIdQuery(int idProgress);
	public List<Progress> findAllByIdChampQuery(int idChampion);
	public void deleteQuery(int idProgress);
	
}
