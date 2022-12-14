package waa.miu.alumnimgmtportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import waa.miu.alumnimgmtportal.entity.Tag;

import java.util.List;

public interface TagRepo extends JpaRepository<Tag, Integer> {
    List<Tag> findTagsByTagStartingWithIgnoreCase(String filter);
}
