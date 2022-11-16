package com.I5E2.todo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.I5E2.todo.domain.BasicResponse;
import com.I5E2.todo.domain.ScheduleVO;
import com.I5E2.todo.domain.TaskVO;
import com.I5E2.todo.service.ITaskService;

@RestController
@RequestMapping("task")
public class TaskController {

	@Autowired
	private ITaskService service;
	
	
	@GetMapping("date")
	public ResponseEntity<BasicResponse> getTask(String date) {
		BasicResponse basicResponse;
		List<TaskVO> list = service.getTask("00000001", date);
		basicResponse = BasicResponse.builder().code(HttpStatus.OK.value()).httpStatus(HttpStatus.OK)
				.message("Task 조회 성공").result(Arrays.asList(list)).count(list.size()).build();
		
		return new ResponseEntity<>(basicResponse, basicResponse.getHttpStatus());
	}

	@PutMapping("")
	public ResponseEntity<BasicResponse> putTask(TaskVO task) {
		task.setRgtr_id("00000001");
		BasicResponse basicResponse;
		
		if(service.createTask(task)==1) {
			basicResponse = BasicResponse.builder().code(HttpStatus.OK.value()).httpStatus(HttpStatus.OK)
					.message("Task 등록 성공").result(Arrays.asList(task)).count(1).build();
		}else {
			basicResponse = BasicResponse.builder().code(HttpStatus.BAD_REQUEST.value()).httpStatus(HttpStatus.BAD_REQUEST)
					.message("Task 등록 실패").result(Arrays.asList(task)).count(1).build();
		}
		
		
		return new ResponseEntity<>(basicResponse, basicResponse.getHttpStatus());
	}

	@PostMapping("")
	public ResponseEntity<BasicResponse> updateTask(TaskVO task) {
		task.setRgtr_id("00000001");
		BasicResponse basicResponse;
		
		if(service.updateTask(task)==1) {
			basicResponse = BasicResponse.builder().code(HttpStatus.OK.value()).httpStatus(HttpStatus.OK)
					.message("Task 수정 성공").result(Arrays.asList(task)).count(1).build();
		}else {
			basicResponse = BasicResponse.builder().code(HttpStatus.BAD_REQUEST.value()).httpStatus(HttpStatus.BAD_REQUEST)
					.message("Task 수정 실패").result(Arrays.asList(task)).count(1).build();
		}
		
		
		return new ResponseEntity<>(basicResponse, basicResponse.getHttpStatus());
	}

	@DeleteMapping("")
	public ResponseEntity<BasicResponse> removeTask(TaskVO task) {
		task.setRgtr_id("00000001");
		BasicResponse basicResponse;
		
		if(service.removeTask(task)==1) {
			basicResponse = BasicResponse.builder().code(HttpStatus.OK.value()).httpStatus(HttpStatus.OK)
					.message("Task 삭제 성공").result(Arrays.asList(task)).count(1).build();
		}else {
			basicResponse = BasicResponse.builder().code(HttpStatus.BAD_REQUEST.value()).httpStatus(HttpStatus.BAD_REQUEST)
					.message("Task 삭제 실패").result(Arrays.asList(task)).count(1).build();
		}
		
		return new ResponseEntity<>(basicResponse, basicResponse.getHttpStatus());
	}
	


}
