package models.member;

import Validators.MobileValidator;
import Validators.PasswordValidator;
import Validators.RequiredValidator;
import Validators.Validator;
import controllers.member.UserForm;

public class JoinValidator implements Validator<UserForm>, RequiredValidator, PasswordValidator, MobileValidator {

    private UsersDao usersDao;

    public JoinValidator(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    @Override
    public void Check(UserForm userForm) {
        /**
         * 1. 필수 항목 검증(userId, userPw, userPwRe, userNm, email, 약관동의)
         * 2. 아이디 자리수는 6자리 이상, 비밀번호는 8자리 이상
         * 3. 아이디 중복 여부
         * 4. userPw(비밀번호), userPwRe(비밀번호 확인) 일치
         * 5. 비밀번호 복잡성(1개 이상의 알파벳 + 대문자 1개 이상 포함, 숫자 1개 이상, 특수문자)
         * 6. 휴대폰번호 유효성 검사
         * 7. 회원가입 약관 동의 여부
         */

        String userId = userForm.getUserId();
        String userPw = userForm.getUserPw();
        String userPwRe = userForm.getUserPwRe();
        String userNm = userForm.getUserNm();
        String email = userForm.getEmail();
        String mobile = userForm.getMobile();

        // 1. 필수 항목 검증
        checkRequired(userId, new JoinValidationException("아이디를 입력하세요."));
        checkRequired(userPw, new JoinValidationException("비밀번호를 입력하세요."));
        checkRequired(userPwRe, new JoinValidationException("비밀번호를 확인하세요."));
        checkRequired(userNm, new JoinValidationException("회원명을 입력하세요."));
        checkRequired(email, new JoinValidationException("이메일을 입력하세요."));

        // 2. 아이디 자리수는 6자리 이상, 비밀번호는 8자리 이상
        if(userId.length() < 6) {
            throw new JoinValidationException("아이디는 6자리 이상 입력하세요.");
        }

        if(userPw.length() < 8) {
            throw new JoinValidationException("비밀번호는 8자리 이상 입력하세요.");
        }

        // 3. 아이디 중복 여부
        if(usersDao.exists(userId)) {
            throw new JoinValidationException("이미 가입된 회원아이디 입니다.");
        }

        // 4. userPw(비밀번호), userPwRe(비밀번호 확인) 일치
        if(!userPw.equals(userPwRe)) {
            throw new JoinValidationException("비밀번호가 일치하지 않습니다.");
        }

        // 5. 비밀번호 복잡성(1개 이상의 알파벳 + 대문자 1개 이상 포함, 숫자 1개 이상, 특수문자)
        //checkTrue(passwordCheck(userPw,3), new JoinValidationException("비밀번호는 양식에 맞게 입력하세요."));

        // 6. 휴대폰번호 유효성 검사
        if(mobile != null && !mobile.isBlank()) {
            mobile = mobile.replaceAll("\\D", "");
            checkTrue(mobileCheck(mobile), new JoinValidationException("휴대전화번호 형식이 아닙니다."));
            userForm.setMobile(mobile);
        }

        // 7. 회원가입 약관 동의 여부
        checkTrue(userForm.isAgree(), new JoinValidationException("약관에 동의해 주세요."));
    }
}
