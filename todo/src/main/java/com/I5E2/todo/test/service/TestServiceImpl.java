package com.I5E2.todo.test.service;

import com.I5E2.todo.test.dao.TestMapper;
import com.I5E2.todo.test.vo.TestVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestMapper testMapper;

    @Override
    public List<TestVO> selectTestList() {
        return testMapper.selectTestList();
    }
}
