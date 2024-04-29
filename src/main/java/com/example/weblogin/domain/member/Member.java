package com.example.weblogin.domain.member;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class Member {

    private Long id;

    @NotEmpty
    private String loginId; //로그인 ID

    @NotEmpty
    private String name; //사용자 이름

    @NotEmpty
    private String password;

}



//중복된 이메일인지 여부 체크하는 기능.
//MemberService에서??? 아니면 Member Dto에서??
//이기능은 데이터베이스 연결이 필요하기 때문에, Service레이어에서 해야할듯..


