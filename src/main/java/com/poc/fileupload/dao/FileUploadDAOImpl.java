package com.poc.fileupload.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.poc.fileupload.model.UploadFile;

@Repository
public class FileUploadDAOImpl implements FileUploadDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public FileUploadDAOImpl() {
	}

	public FileUploadDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public void save(UploadFile uploadFile) {
		sessionFactory.getCurrentSession().save(uploadFile);
	}

	@Override
	@Transactional
	public UploadFile getFileFor(String fileName) {
		Session session = sessionFactory.getCurrentSession();
		UploadFile uploadFile = (UploadFile) session.get(UploadFile.class, 1);
		return uploadFile;
	}

}
