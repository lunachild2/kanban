package controlles.works;

import commons.ViewUtils;
import controlles.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ListController implements Controller {
    @Override
    public void get(HttpServletRequest req, HttpServletResponse resp) {
        ViewUtils.load(req, resp, "works", "list");
    }

    @Override
    public void post(HttpServletRequest req, HttpServletResponse resp) {

    }
}
