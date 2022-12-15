package waa.miu.alumnimgmtportal.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import waa.miu.alumnimgmtportal.entity.ActivityLog;
import waa.miu.alumnimgmtportal.repository.ActivityLogRepo;
import waa.miu.alumnimgmtportal.security.MyUserDetails;
import waa.miu.alumnimgmtportal.service.ActivityLogService;

@Service
@RequiredArgsConstructor
public class ActivityLogServiceImpl implements ActivityLogService {

    private final ActivityLogRepo activityLogRepo;
    @Override
    public void save(String task) {
        var log = new ActivityLog();
        log.setTask(task);
        log.setPersonId(getCurrentPersonId());//set this from user context
        activityLogRepo.save(log);
    }

    private int getCurrentPersonId() {
        try{
            return ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        }catch (Exception ex){
            return 0;
        }
    }
}
