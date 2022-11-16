package com.I5E2.todo.dao;

import com.I5E2.todo.domain.ScheduleVO;
import com.I5E2.todo.domain.TaskVO;
import com.I5E2.todo.test.vo.TestVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TaskMapper {
    List<TaskVO> findByRegDt(@Param("rgtr_id") String rgtr_id, @Param("date")String date);
    Integer updateTask(TaskVO vo);
    Integer removeTask(TaskVO vo);
    Integer insertTask(TaskVO vo);
}
