package waa.miu.alumnimgmtportal.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import waa.miu.alumnimgmtportal.entity.Person;
import waa.miu.alumnimgmtportal.repository.PersonRepo;
import waa.miu.alumnimgmtportal.service.PersonService;

@Service("userDetailsService")
@Transactional
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final PersonRepo personRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepo.findPersonByUsernameIgnoreCase(username);
        var userDetails = new MyUserDetails(person);
        return userDetails;
    }
}
