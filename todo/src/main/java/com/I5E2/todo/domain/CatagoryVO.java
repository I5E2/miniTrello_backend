package com.I5E2.todo.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString
public class CatagoryVO {

	@Id
	private String ctgr_id;// 카테고리ID
	private String ctgr_nm;// 카테고리명
	private String color;// 색상
	private String rgtr_id;// 등록자ID

	private Integer indct_cn;// 표시순번

}
