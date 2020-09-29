package com.learning.restapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "matakuliah")
public class matakuliah {
//	@Id
//	private long id;
	@Id
	private long idmatkul;
	private String mataKuliah;
	private long idmhs;
	private int nilai;
	

	@Override
	public String toString() {
		return "matakuliah [idMatakuliah=" + idmatkul + ", namaMatakuliah=" + mataKuliah
				+ ", idMahasiswa=" + idmhs +" , nilai=" + nilai +"]";
	}
}
