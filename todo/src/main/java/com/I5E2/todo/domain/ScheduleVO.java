package com.I5E2.todo.domain;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
//@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleVO {

//	@Id
	private String schdl_id;// 일정ID
	private String schdl_nm;// 일정명

	private LocalDateTime schdl_bgng_dt;// 일정시작일시

	private LocalDateTime schdl_end_dt;// 일정종료일시

	private Integer prgrsrt;// 진행률
	private String cmptn_yn; // 완료여부
	private String ctgr_id;// 카테고리 ID

	private LocalDate reg_dt;// 등록일시
	
	
	private String rgtr_id;// 등록자ID
	private String atch_doc_id; // 첨부문서ID
	private String pstn;// 위치
	private Integer indct_sn;// 표시순번

	private CatagoryVO ctgr;

	@Override
	public String toString() {
		
		ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule()); 
		
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "{}";
		}
	}
	
	
}
