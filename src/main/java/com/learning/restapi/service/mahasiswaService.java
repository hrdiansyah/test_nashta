package com.learning.restapi.service;

import org.springframework.stereotype.Service;

import com.learning.restapi.dto.mahasiswaDto;
import com.learning.restapi.model.mahasiswa;

@Service
public interface mahasiswaService {
	public mahasiswa save(mahasiswa Mahasiswa);
	
}
