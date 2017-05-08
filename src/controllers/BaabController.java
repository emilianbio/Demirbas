package controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import forms.Kullanici;
@Controller
public class BaabController {

    @RequestMapping("/baab")
    public String baab(@ModelAttribute("kullanici") Kullanici kullanici,
	    ModelMap model, HttpServletResponse response) {
	model.put("kullanici", new Kullanici());
	Cookie cookie = new Cookie("isim", "");
	cookie.setMaxAge(0);

	response.addCookie(cookie);

	return "baab";
    }

}
