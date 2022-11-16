package com.I5E2.todo.service;

import java.util.List;

import com.I5E2.todo.domain.ScheduleVO;

public interface IScheduleService {
    public int createSchedule(ScheduleVO schedule);
    public int updateSchedule(ScheduleVO schedule);
    public int removeSchedule(ScheduleVO schedule);
    public List<ScheduleVO> getSchedule(String rgtr_id ,String date);
    public List<ScheduleVO> getSchedule(String rgtr_id ,String startDate,String endDate);
}
