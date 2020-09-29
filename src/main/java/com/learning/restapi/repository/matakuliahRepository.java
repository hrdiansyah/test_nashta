package com.learning.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.restapi.model.matakuliah;

@Repository
public interface matakuliahRepository extends JpaRepository<matakuliah, Long>{

}
