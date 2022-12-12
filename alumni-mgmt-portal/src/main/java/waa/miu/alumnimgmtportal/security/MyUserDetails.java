package waa.miu.alumnimgmtportal.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import waa.miu.alumnimgmtportal.entity.Person;
import waa.miu.alumnimgmtportal.entity.Role;

import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class MyUserDetails implements UserDetails {

    private int userId;
    private String username;
    private String firstname;
    private String lastname;
    @JsonIgnore
    private String  password;
    private List<Role> roles;

    public MyUserDetails(Person person){
        this.userId = person.getId();
        this.username = person.getUsername();
        this.password = person.getPassword();
        this.roles = person.getRoles();
        this.firstname = person.getFirstname();
        this.lastname = person.getLastname();
        this.roles=person.getRoles();

    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var result = roles.stream()
                .map(role-> new SimpleGrantedAuthority("ROLE_"+role.getRole().toUpperCase(Locale.ROOT)))
                .toList();
        return result;
    }

    public int getUserId() {
        return userId;
    }
    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {
        return lastname;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
