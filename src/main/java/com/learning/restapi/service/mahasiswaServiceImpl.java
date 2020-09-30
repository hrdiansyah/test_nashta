package com.learning.restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.restapi.model.mahasiswa;
import com.learning.restapi.repository.mahasiswaRepository;

@Service
public class mahasiswaServiceImpl implements mahasiswaService {

	@Autowired
	private mahasiswaRepository MahasiswaRepo;
	
	@Override
	public mahasiswa save(mahasiswa Mahasiswa) {
		return MahasiswaRepo.save(Mahasiswa);
	}
	
}
