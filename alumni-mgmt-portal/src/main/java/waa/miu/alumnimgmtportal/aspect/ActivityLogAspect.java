package waa.miu.alumnimgmtportal.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import waa.miu.alumnimgmtportal.entity.ActivityLog;
import waa.miu.alumnimgmtportal.service.ActivityLogService;

@Aspect
@Component
@RequiredArgsConstructor
public class ActivityLogAspect {
    private final ActivityLogService activityLogService;

    @Pointcut("within(waa.miu.alumnimgmtportal.service..*)")
    private void allPackages(){}
    @Pointcut("within(waa.miu.alumnimgmtportal.service.impl.ActivityLogServiceImpl)")
    private void activityLogService(){}

    @Before("allPackages() && !activityLogService()")
    public void logActivity(JoinPoint joinPoint){
        String task = joinPoint.getSignature().toShortString();
        activityLogService.save(task);
    }
}
