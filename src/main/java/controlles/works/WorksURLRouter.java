package controlles.works;

import controlles.URLRouter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class WorksURLRouter implements URLRouter {
    @Override
    public void route(HttpServletRequest req, HttpServletResponse resp, String location) {

    }

    @Override
    public String getMode(String URL, String location) {
        return URLRouter.super.getMode(URL, location);
    }
}
