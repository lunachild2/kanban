package controllers.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor // -> public 기본 생성자가 추가
public class UserForm {

    private long userNo;
    private String userId;
    private String userPw;
    private String userPwRe;
    private String userNm;
    private String email;
    private String mobile;
    private boolean agree;
}
