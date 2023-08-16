package models.works;

import Validators.Validator;
import commons.MemberUtil;
import jakarta.servlet.http.HttpServletRequest;
import models.member.Users;

public class SaveService {

    private WorkDao workDao;

    private Validator<Work> validator;

    public SaveService(WorkDao workDao, Validator<Work> validator) {
        this.workDao = workDao;
        this.validator = validator;
    }

    public void save(Work work) {
        // 유효성 검사
        validator.Check(work);

        boolean result = workDao.save(work); // 의존
        if(!result) { // 등록 실패
            throw new WorkSaveException();
        }
    }

    public void save(HttpServletRequest req) {
        Work work = new Work();
        work.setSubject(req.getParameter("subject"));
        work.setContent(req.getParameter("content"));

        String status = req.getParameter("status");
        work.setStatus(status == null || status.isBlank() ? Status.READY : Status.valueOf(status));

        String workNo = req.getParameter("workNo");
        if(workNo != null && !workNo.isBlank()) {
            work.setWorkNo(Long.parseLong(workNo));
        }

        if(MemberUtil.isLogin(req)) { // 로그인한 경우 회원번호 DB에 기록
            Users users = MemberUtil.getUser(req);
            work.setUserNo(users.getUserNo());
        }

        save(work);
    }
}
