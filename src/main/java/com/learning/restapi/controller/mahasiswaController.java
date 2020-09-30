package com.learning.restapi.controller;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.learning.restapi.dto.ResponseJson;
import com.learning.restapi.dto.ResponseMessage;
import com.learning.restapi.enumeration.responseCodes;
import com.learning.restapi.model.mahasiswa;
import com.learning.restapi.repository.mahasiswaRepository;
import com.learning.restapi.service.ExcelHelper;
import com.learning.restapi.service.ExcelService;
import com.learning.restapi.service.mahasiswaService;

import org.springframework.validation.annotation.Validated;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
//import com.learning.dto.ResponseMessage;
//import com.learning.exception.ResourceNotFoundException;
import java.util.List;

@RestController

@RequestMapping("/api/v2")
public class mahasiswaController {
	@Autowired
	mahasiswaRepository MahasiswaRepository;
	
	@Autowired
	mahasiswaService MahasiswaService;
	
	@Autowired
	ExcelService excelService;
	
	
	@GetMapping("/getAll")
	public List<mahasiswa> getAll(){
		return (List<mahasiswa>) MahasiswaRepository.findAll();
	}
	
	@PostMapping("/upload")
	  public ResponseEntity<ResponseJson> uploadFile(@RequestParam("file") MultipartFile file) {
	    String message = "";

	    if (ExcelHelper.hasExcelFormat(file)) {
	      try {
	    	excelService.save(file);
	        message = "Uploaded the file successfully: " + file.getOriginalFilename();
//	        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	        return ResponseEntity.ok(new ResponseJson(responseCodes.SUCCESS, file));
	      } catch (Exception e) {
	    	StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String sStackTrace = sw.toString();
	        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
//	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(message));
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseJson(responseCodes.OTHER, e.getMessage()));
	      }
	    }
	    message = "Please upload an excel file!";
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseJson(responseCodes.SUCCESS, message));
	  }
	
	  @GetMapping("/mahasiswas")
	  public ResponseEntity<List<mahasiswa>> getAllMahasiswa() {
	    try {
	      List<mahasiswa> mahasiswas = excelService.getAllMahasiswas();

	      if (mahasiswas.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(mahasiswas, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  
	    @PostMapping("/import")
	    public ResponseEntity<ResponseJson> getExcelToDB(@RequestParam("file") MultipartFile reapExcelDataFile) 
	    		throws IOException {
	    try {
//	       List<mahasiswa> tempStudentList = new ArrayList<mahasiswa>();
//	        XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
//	        XSSFSheet worksheet = workbook.getSheetAt(0);
	        excelService.ReadDataFromExcel(reapExcelDataFile);
//	        for(int i=0; i<worksheet.getPhysicalNumberOfRows(); i++) {
//	            mahasiswa tempStudent = new mahasiswa();
//	            XSSFRow row = worksheet.getRow(i);
	            
//	            tempStudent.setIdmhs((int) row.getCell(0).getNumericCellValue());
//	            tempStudent.setNama(row.getCell(1).getStringCellValue());
//	            tempStudent.setAlamat(row.getCell(2).getStringCellValue());
//	            tempStudentList.add(tempStudent);
//	            System.out.println("DATA" + tempStudentList.add(tempStudent));
//	            MahasiswaRepository.saveAll(tempStudent);
//	            return ResponseEntity.ok(new ResponseJson(responseCodes.SUCCESS, tempStudent));
//	        	} 
	        } catch (Exception e) {
		    	StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				String sStackTrace = sw.toString();
//		        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
//		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(message));
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseJson(responseCodes.OTHER, e.getMessage()));
		      }
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseJson(responseCodes.SUCCESS, "GAK OKE"));
	    }

}
