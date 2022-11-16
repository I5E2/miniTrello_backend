package com.I5E2.todo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.I5E2.todo.dao.TaskMapper;
import com.I5E2.todo.domain.TaskVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements ITaskService {

	private final TaskMapper mapper;
	
	@Override
	public int createTask(TaskVO task) {
		return mapper.insertTask(task);
	}

	@Override
	public int updateTask(TaskVO task) {
		return mapper.updateTask(task);
	}

	@Override
	public int removeTask(TaskVO task) {
		return mapper.removeTask(task);
	}

	@Override
	public List<TaskVO> getTask(String rgtr_id,String date) {
		return mapper.findByRegDt(rgtr_id, date);
	}


}
