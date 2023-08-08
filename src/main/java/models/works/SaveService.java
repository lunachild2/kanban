package models.works;

import Validators.Validator;

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
}
