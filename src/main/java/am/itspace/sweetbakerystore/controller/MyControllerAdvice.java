package am.itspace.sweetbakerystore.controller;


import am.itspace.sweetbakerystore.dto.BasketDto;
import am.itspace.sweetbakerystore.entity.User;
import am.itspace.sweetbakerystore.security.CurrentUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class MyControllerAdvice {
    @Resource(name = "basketDto")
    private BasketDto basketDto;


    public MyControllerAdvice(BasketDto basketDto) {
        this.basketDto = basketDto;
    }

    @ModelAttribute(name = "currentUser")
    public User currentUser(@AuthenticationPrincipal CurrentUser currentUser) {
        return currentUser == null ? null : currentUser.getUser();
    }

    @ModelAttribute(name = "currentUrl")
    public String currentUrl(HttpServletRequest request) {
        return request.getRequestURI();

    }

    @ModelAttribute(name = "basketDto")
    public BasketDto getBasketDto() {
        return basketDto;
    }
}
