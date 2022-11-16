package com.I5E2.todo.dao;

import com.I5E2.todo.domain.ScheduleVO;
import com.I5E2.todo.test.vo.TestVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ScheduleMapper {
    List<ScheduleVO> findByDaysSchedule(@Param("rgtr_id") String rgtr_id,@Param("startDate")String startDate, @Param("endDate")String endDate);
    List<ScheduleVO> findByDaySchedule(@Param("rgtr_id") String rgtr_id,@Param("date") String date);
    Integer updateSchedule(ScheduleVO vo);
    Integer removeSchedule(ScheduleVO vo);
    Integer insertSchedule(ScheduleVO vo);
}
