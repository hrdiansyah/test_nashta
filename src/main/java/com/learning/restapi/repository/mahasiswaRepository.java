package com.learning.restapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.learning.restapi.model.mahasiswa;

public interface mahasiswaRepository extends CrudRepository<mahasiswa, Long> {
//	mahasiswa findByIdMahasiswa(long idMahasiswa);
//	mahasiswa findOneByIdMhsAndIdMatkul(long idmhs, long idMatkul);
}
