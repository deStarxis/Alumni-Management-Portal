package waa.miu.alumnimgmtportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import waa.miu.alumnimgmtportal.entity.Person;

@Repository
public interface PersonRepo extends JpaRepository<Person, Integer> {
    Person findPersonByUsernameIgnoreCase(String username);
}
