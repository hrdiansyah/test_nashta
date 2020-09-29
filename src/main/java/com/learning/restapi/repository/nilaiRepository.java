package com.learning.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.learning.restapi.model.nilai;

@Repository
public interface nilaiRepository extends JpaRepository<nilai, Long> {
//	nilai findByIdMahasiswa(long idMahasiswa);
	nilai findOneByIdmhsAndIdmatkul(long idmhs, long idmatkul);
	
	@Query(value = "SELECT AVG(nilai) FROM test_nashta.nilai WHERE idmhs = ?1" , nativeQuery = true)long getRatarata(long idmhs);
//	@Transactional
//	@Query(value = "SELECT AVG(nilai)" 
//			+" "+
//			"FROM test_nashta.nilai A"
//			+" "+ 
//			"WHERE A.idmhs = ?1" , 
//			nativeQuery=true)
//	nilai getRatarata(long idmhs);
	
//	@Query(value = "SELECT avg(A.nilai)"
//			+" "+
//			"FROM test_nashta.nilai AS A"
//			+" "+
//			"WHERE (A.idmhs = ?1)", nativeQuery = true)nilai getRatarata(long idmhs);
}
