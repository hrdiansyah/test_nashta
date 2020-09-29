package com.learning.restapi.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "mahasiswa")
public class mahasiswa {
//	@Id
//	private long id;
	@Id
	private long idmhs;
	private String nama;
	private String alamat;
	
	@Override
	public String toString() {
		return "Mahasiswa [idmhs=" + idmhs + ", nama=" + nama + ", alamat=" + alamat  + "]";
	}
}
