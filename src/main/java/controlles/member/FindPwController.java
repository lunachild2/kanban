package controlles.member;

import commons.ViewUtils;
import controlles.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FindPwController implements Controller {
    @Override
    public void get(HttpServletRequest req, HttpServletResponse resp) {
        ViewUtils.load(req, resp, "member", "find_pw");
    }

    @Override
    public void post(HttpServletRequest req, HttpServletResponse resp) {

    }
}
