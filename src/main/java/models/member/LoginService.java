package models.member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LoginService {

    private LoginValidator validator;
    private UsersDao usersDao;

    public LoginService(LoginValidator validator, UsersDao usersDao) {
        this.validator = validator;
        this.usersDao = usersDao;
    }

    public void login(HttpServletRequest req) {

        validator.Check(req);

        // 검증 성공 -> 로그인 처리
        HttpSession session = req.getSession();
        String userId = req.getParameter("userId");
        Users users = usersDao.get(userId);
        session.setAttribute("users", users);
    }
}
