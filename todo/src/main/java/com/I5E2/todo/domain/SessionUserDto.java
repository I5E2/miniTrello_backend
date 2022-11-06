package com.I5E2.todo.domain;


import lombok.Getter;

import java.io.Serializable;


/**
 * session에 사용자 정보를 저장하기 위한 dto
 *
 */
@Getter
public class SessionUserDto implements Serializable {
    private final String userId;
    private final String name;
    private final String email;
    private final String picture;

    public SessionUserDto(User user,String picture){
        this.userId = user.getUserId();
        this.name = user.getName();
        this.email = user.getLoginId();
        this.picture = picture;
    }


}
