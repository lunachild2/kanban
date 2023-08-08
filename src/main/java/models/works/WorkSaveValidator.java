package models.works;

import Validators.RequiredValidator;
import Validators.Validator;

public class WorkSaveValidator implements Validator<Work>, RequiredValidator {
    @Override
    public void Check(Work work) {
        /** 필수 항목 검증 - status, subject, content */
        checkRequired(work.getStatus(), new WorkValidationException("작업 상태를 선택하세요."));
        checkRequired(work.getSubject(), new WorkValidationException("작업 제목을 입력하세요."));
        checkRequired(work.getContent(), new WorkValidationException("작업 내용을 입력하세요."));
    }
}
