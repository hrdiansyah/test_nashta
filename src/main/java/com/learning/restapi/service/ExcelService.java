package com.learning.restapi.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.learning.restapi.model.mahasiswa;
import com.learning.restapi.repository.mahasiswaRepository;

@Service
public class ExcelService {
	  @Autowired
	  mahasiswaRepository repository;

	  public void save(MultipartFile file) {
	    try {
	      List<mahasiswa> mahasiswas = ExcelHelper.excelToMahasiswa(file.getInputStream());
	      repository.saveAll(mahasiswas);
	    } catch (IOException e) {
	      throw new RuntimeException("fail to store excel data: " + e.getMessage());
	    }
	  }

	  public List<mahasiswa> getAllMahasiswas() {
	    return repository.findAll();
	  }
}
