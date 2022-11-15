package com.I5E2.todo.domain;

import com.I5E2.todo.jpa.IdGenerator;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GenericGenerator(name="useridGenerator",strategy="com.I5E2.todo.jpa.IdGenerator",
       parameters = {@Parameter(name=IdGenerator.ENTITY_NAME,value="USER")})
    @GeneratedValue(generator="useridGenerator")
    private String userId;

    //소셜 로그인시 이메일을 loginId로 저장한다.
    @Column(name="LGN_ID")
    private String loginId;

    //소셜 로그인시 RefreshToken값을 password로 저장한다.
    @Column(name="PSWD")
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="JOIN_CD",referencedColumnName = "CMMN_CD")
    private CommonCode joinCode;

    @Column(name="JOIN_TOKEN")
    private String joinToken;

    @Column(name="USER_NM",nullable = false)
    private String name;

    @Column(name="EML_ADDR")
    private String email;

    /*@Column
    private String picture;
     */

    @Builder
    public User(String loginId,String password,CommonCode joinCode ,String joinToken,String name, String email){
        this.loginId = loginId;
        this.password = password;
        this.joinCode = joinCode;
        this.joinToken = joinToken;
        this.name = name;
        this.email = email;
    }
    public void changeInfo(String password,String name,String email){
        this.password = password;
        this.name = name;
        this.email = email;
    }


}
