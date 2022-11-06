package com.I5E2.todo.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class CommonCode {
    @Id
    @Column(name="CMMN_CD")
    private String id;

    @Column(name="CMMN_CD_NM")
    private String codeName;

    @OneToMany(mappedBy = "userId")
    private final List<User> users = new ArrayList<>();
}
