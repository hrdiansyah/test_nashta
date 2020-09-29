package com.learning.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.restapi.model.mahasiswa;

public interface mahasiswaRepository extends JpaRepository<mahasiswa, Long> {
//	mahasiswa findByIdMahasiswa(long idMahasiswa);
//	mahasiswa findOneByIdMhsAndIdMatkul(long idmhs, long idMatkul);
}
