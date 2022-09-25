package com.I5E2.todo.test.vo;

import lombok.Data;

@Data
public class TestVO {
    private String testId;  // 테스트ID
    private String cn;      // 내용
    private String regDt;   // 등록일시

    public TestVO(String testId, String cn, String regDt) {
        this.testId = testId;
        this.cn = cn;
        this.regDt = regDt;
    }
}
