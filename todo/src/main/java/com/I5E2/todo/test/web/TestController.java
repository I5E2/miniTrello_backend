package com.I5E2.todo.test.web;

import com.I5E2.todo.test.service.TestService;
import com.I5E2.todo.test.vo.TestVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;

    @GetMapping("/test")
    public List<TestVO> selectTestList() {
        List<TestVO> testList = testService.selectTestList();
        log.info("testList = {}", testList);

        return testService.selectTestList();
    }
}
