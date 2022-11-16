package com.I5E2.todo.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
public class TaskVO {
	@Id
	private String task_id;//작업ID
	private String up_task_id;//상위작업ID
	private Integer indct_sn;//표시순번
	private String cn;//내용
	private String cmptn_yn;//완료여부
	private String rgtr_id;//등록자ID
	private LocalDate reg_dt;//등록일시
}
