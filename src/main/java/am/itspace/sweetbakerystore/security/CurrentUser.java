package am.itspace.sweetbakerystore.security;

import am.itspace.sweetbakerystore.entity.User;
import org.springframework.security.core.authority.AuthorityUtils;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private final User user;

    public CurrentUser(User user) {
        super(user.getEmail(), user.getPassword(),user.isActive(),true,true,true, AuthorityUtils.createAuthorityList(user.getRole().name()));
        this.user = user;
    }


    public User getUser() {
        return user;
    }
}

