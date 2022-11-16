package com.I5E2.todo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.I5E2.todo.dao.ScheduleMapper;
import com.I5E2.todo.domain.ScheduleVO;
import com.I5E2.todo.test.dao.TestMapper;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements IScheduleService {

	private final ScheduleMapper mapper;
	
	
	@Override
	public int createSchedule(ScheduleVO schedule) {
		
		return mapper.insertSchedule(schedule);
	}

	@Override
	public int updateSchedule(ScheduleVO schedule) {
		return mapper.updateSchedule(schedule);
	}

	@Override
	public int removeSchedule(ScheduleVO schedule) {
		return mapper.removeSchedule(schedule);
	}

	@Override
	public List<ScheduleVO> getSchedule(String rgtr_id,String date) {
		return mapper.findByDaySchedule(rgtr_id,date);
	}

	@Override
	public List<ScheduleVO> getSchedule(String rgtr_id,String startDate, String endDate) {
		return mapper.findByDaysSchedule(rgtr_id,startDate, endDate);
	}

}
