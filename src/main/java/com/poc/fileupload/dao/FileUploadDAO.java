package com.poc.fileupload.dao;

import com.poc.fileupload.model.UploadFile;

public interface FileUploadDAO {
	void save(UploadFile uploadFile);

	UploadFile getFileFor(String fileName);
}
