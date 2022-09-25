package com.I5E2.todo.test.dao;

import com.I5E2.todo.test.vo.TestVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {
    List<TestVO> selectTestList();
}
