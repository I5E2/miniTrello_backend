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
import com.I5E2.todo.service.IScheduleService;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

	@Autowired
	private IScheduleService service;

	@GetMapping("date")
	public ResponseEntity<BasicResponse> getScheclude(@RequestParam("date") String date) {

		BasicResponse basicResponse;
		List<ScheduleVO> list = service.getSchedule("00000001",date);
		basicResponse = BasicResponse.builder().code(HttpStatus.OK.value()).httpStatus(HttpStatus.OK)
				.message("스케줄 조회(하루) 성공").result(Arrays.asList(list)).count(list.size()).build();

		return new ResponseEntity<>(basicResponse, basicResponse.getHttpStatus());
	}

	@GetMapping("dates")
	public ResponseEntity<BasicResponse> getSchecludes(@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate) {

		BasicResponse basicResponse;
		List<ScheduleVO> list = service.getSchedule("00000001",startDate, endDate);
		basicResponse = BasicResponse.builder().code(HttpStatus.OK.value()).httpStatus(HttpStatus.OK)
				.message("스케줄 조회(범위) 성공").result(Arrays.asList(list)).count(list.size()).build();

		return new ResponseEntity<>(basicResponse, basicResponse.getHttpStatus());

	}

	@PutMapping("")
	public ResponseEntity<BasicResponse> putSchedule(ScheduleVO schedule) {
		BasicResponse basicResponse;
		if (service.createSchedule(schedule) == 1) {
			basicResponse = BasicResponse.builder().code(HttpStatus.OK.value()).httpStatus(HttpStatus.OK)
					.message("스케줄 등록 성공").result(Arrays.asList(schedule)).count(1).build();
		} else {
			basicResponse = BasicResponse.builder().code(HttpStatus.BAD_REQUEST.value())
					.httpStatus(HttpStatus.BAD_REQUEST).message("스케줄 등록 실패").result(Arrays.asList(schedule)).count(1)
					.build();
		}

		return new ResponseEntity<>(basicResponse, basicResponse.getHttpStatus());
	}

	@PostMapping("")
	public ResponseEntity<BasicResponse> updateSchedule(ScheduleVO schedule) {
		BasicResponse basicResponse;
		if (service.updateSchedule(schedule) == 1) {
			basicResponse = BasicResponse.builder().code(HttpStatus.OK.value()).httpStatus(HttpStatus.OK)
					.message("스케줄 업데이트 성공").result(Arrays.asList(schedule)).count(1).build();
		} else {
			basicResponse = BasicResponse.builder().code(HttpStatus.BAD_REQUEST.value())
					.httpStatus(HttpStatus.BAD_REQUEST).message("스케줄 업데이트 실패").result(Arrays.asList(schedule)).count(1)
					.build();
		}
		return new ResponseEntity<>(basicResponse, basicResponse.getHttpStatus());
	}

	@DeleteMapping("")
	public ResponseEntity<BasicResponse> removeSchedule(ScheduleVO schedule) {
		BasicResponse basicResponse;
		if (service.removeSchedule(schedule) == 1) {
			basicResponse = BasicResponse.builder().code(HttpStatus.OK.value()).httpStatus(HttpStatus.OK)
					.message("스케줄 삭제 성공").result(Arrays.asList(schedule)).count(1).build();
		} else {
			basicResponse = BasicResponse.builder().code(HttpStatus.BAD_REQUEST.value())
					.httpStatus(HttpStatus.BAD_REQUEST).message("스케줄 삭제 실패").result(Arrays.asList(schedule)).count(1)
					.build();
		}
		return new ResponseEntity<>(basicResponse, basicResponse.getHttpStatus());
	}

}
