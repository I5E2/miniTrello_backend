package com.I5E2.todo.service;

import java.util.List;

import com.I5E2.todo.domain.TaskVO;

public interface ITaskService {
    public int createTask(TaskVO task);
    public int updateTask(TaskVO task);
    public int removeTask(TaskVO task);
    public List<TaskVO> getTask(String rgtr_id, String date);
}
