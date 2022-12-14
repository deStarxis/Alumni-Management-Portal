package waa.miu.alumnimgmtportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import waa.miu.alumnimgmtportal.entity.Verification;

import java.util.UUID;

public interface VerificationRepo extends JpaRepository<Verification,Integer> {
    Verification findVerificationByToken(UUID token);
}
