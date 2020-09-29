package com.learning.restapi.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.learning.restapi.dto.ResponseJson;
import com.learning.restapi.dto.nilaiDto;
import com.learning.restapi.enumeration.responseCodes;
import com.learning.restapi.exception.notFoundException;
import com.learning.restapi.model.nilai;
import com.learning.restapi.repository.nilaiRepository;
import com.learning.restapi.service.nilaiService;

@RestController

@RequestMapping("/api/v2")
public class nilaiController {
	@Autowired
	private nilaiRepository NilaiRepository;
	
	@Autowired
	private nilaiService NilaiService;

	@GetMapping("/nilai")
	public List<nilai> getAllNilai() {
		return NilaiRepository.findAll();
	}
	
	//GET
	@RequestMapping(value = "/{idmhs}/{idmatkul}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ResponseJson>getByData(
			@PathVariable(name="idmhs", required=true, value="")long Idmhs,
			@PathVariable(name="idmatkul", required=true, value="") long Idmatkul)
	{
		try {
			
			nilai Nilai = NilaiRepository.findOneByIdmhsAndIdmatkul(Idmhs, Idmatkul);
			if(Nilai!=null) {
				nilaiDto NilaiDto = NilaiService.transformToDto(Nilai);
				return ResponseEntity.ok(new ResponseJson(responseCodes.SUCCESS, NilaiDto));
			}else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseJson(responseCodes.NOTFOUND, "Data Not Found"));
		} 
		}catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String sStackTrace = sw.toString();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseJson(responseCodes.OTHER, e.getMessage()));
			}
	}
	
	@RequestMapping(value = "ratarata/{idmhs}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ResponseJson>getRata2(
			@PathVariable(name="idmhs", required=true, value="") long Idmhs
			)
	{
		try {
//			public double getRatarata(Idmhs)
			long Nilai = NilaiRepository.getRatarata(Idmhs);
//			System.out.println(Nilai);
			if(Nilai!=0) {
				System.out.println(Nilai);
				return ResponseEntity.ok(new ResponseJson(responseCodes.SUCCESS, Nilai));
			}else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseJson(responseCodes.NOTFOUND, "Data Not Found"));
//			return null;
					} 
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String sStackTrace = sw.toString();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseJson(responseCodes.OTHER, e.getMessage()));
			}
	}
	
	@GetMapping("/nilai/{id}")
	public ResponseEntity<nilai> getById(@PathVariable(value = "id") Long courseId)
			throws notFoundException {
		nilai course = NilaiRepository.findById(courseId)
				.orElseThrow(() -> new notFoundException("Course not found for this id :: " + courseId));
		return ResponseEntity.ok().body(course);
	}
	
	//POST
	@PostMapping("/nilai")
	public nilai postNilai(@Validated @RequestBody nilai course) {
		return NilaiRepository.save(course);
	}

	//PUT
	@PutMapping("/nilai/{id}")
	public ResponseEntity<nilai> putNilai(@PathVariable(value = "id") Long courseId,
			@Validated @RequestBody nilai courseDetails) throws notFoundException {
		nilai course = NilaiRepository.findById(courseId)
				.orElseThrow(() -> new notFoundException("Course not found for this id :: " + courseId));
		
		course.setIdmhs(courseDetails.getIdmhs());
		course.setIdmatkul(courseDetails.getIdmatkul());
		course.setNilai(courseDetails.getNilai());
		course.setKeterangan(courseDetails.getKeterangan());
		final nilai updatedCourse = NilaiRepository.save(course);
		return ResponseEntity.ok(updatedCourse);
	}

	//DELETE
	@DeleteMapping("/nilai/{id}")
	public Map<String, Boolean> deleteNilai(@PathVariable(value = "id") Long courseId)
			throws notFoundException {
		nilai course = NilaiRepository.findById(courseId)
				.orElseThrow(() -> new notFoundException("Course not found for this id :: " + courseId));

		NilaiRepository.delete(course);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	
}
