package controllers;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import service.DemirbasService;
import service.KullaniciService;
import service.SabitTipsService;
import forms.Kullanici;
import forms.Memurlar;

@Controller
public class KullaniciController {

    @Autowired
    private SabitTipsService sabitTipsService;
    @Autowired
    private DemirbasService demirbasService;
    @Autowired
    private KullaniciService kullaniciService;

    @RequestMapping(value = "/kullanicionay", method = RequestMethod.POST)
    public ModelAndView kullaniciOnay(
	    @ModelAttribute("kullanici") Kullanici kullanici,
	    HttpServletRequest request, HttpServletResponse response,
	    ModelMap model) throws UnsupportedEncodingException {

	Kullanici kayitliKullanici = kullaniciService.kullaniciGetir(kullanici);
	
	System.out.println("giriş11111");
	if (kayitliKullanici == null) {
	    JOptionPane panel = new JOptionPane();
	    JOptionPane.showMessageDialog(panel, "Yanlış Bilgi Girdiniz....",
		    "Hatalı Giriş", JOptionPane.ERROR_MESSAGE);

	    return new ModelAndView("baab");
	} else {

	    response.addCookie(new Cookie("isim", kayitliKullanici.getIsim()));
	    response.addCookie(new Cookie("memurid", Long
		    .toString(kayitliKullanici.getId())));
	    System.out.println("giriş222222");
	    return new ModelAndView("redirect:/demirbaslistesi");
	}
    }

    @Transactional
    @RequestMapping(value = "/cikis", method = RequestMethod.GET)
    public String cikis(ModelMap model, HttpServletResponse response) {
	// model.put("kullanici", new Memurlar());

	Cookie cookie = new Cookie("unvan", "");
	Cookie cookie1 = new Cookie("memurid", "");
	Cookie cookie2 = new Cookie("isim", "");
//	cookie.setValue("");
//	cookie1.setValue("");
//	cookie2.setValue("");
	cookie.setMaxAge(0);
	cookie1.setMaxAge(0);
	cookie2.setMaxAge(0);
	response.addCookie(cookie);
	response.addCookie(cookie1);
	response.addCookie(cookie2);
	return ("redirect:/giris");
    }

    @RequestMapping("/giris")
    public String giris(Memurlar kullanici, HttpServletRequest request,
	    HttpServletResponse response, ModelMap model)
	    throws ClassNotFoundException, SQLException {

	return "giris";
    }

    @RequestMapping("/ala")
    public String ala() {

	return "ala";
    }

    @RequestMapping(value = "/girisonay", method = RequestMethod.POST)
    public ModelAndView girisOnay(@ModelAttribute("memur") Memurlar kullanici,
	    HttpServletRequest request, HttpServletResponse response,
	    ModelMap model) throws ClassNotFoundException, SQLException,
	    UnsupportedEncodingException {

	Memurlar kayitliKullanici = kullaniciService.kullaniciGiris(kullanici,
		request, response);

	if (request.getParameter("Isim") == null
		|| request.getParameter("sifre").equals("")
		|| request.getParameter("Isim") == ""
		|| request.getParameter("sifre") == null
		|| !request.getParameter("Isim").equalsIgnoreCase(
			kayitliKullanici.getIsim())
		&& !request.getParameter("sifre").equalsIgnoreCase(
			kayitliKullanici.getSifre())) {

	    JOptionPane panel = new JOptionPane();
	    JOptionPane.showMessageDialog(panel,
		    "Eksik veya Yanlış Bilgi Girdiniz....", "Hatalı Giriş",
		    JOptionPane.ERROR_MESSAGE);

	    return new ModelAndView("giris");

	} else

	    // response.addCookie(new Cookie("isim",
	    // kayitliKullanici.getIsim()));

	    response.addCookie(new Cookie("memurid", Long
		    .toString(kayitliKullanici.getMemurID())));

	String isim = request.getParameter("Isim");// kayitliKullanici.getIsim();
						   // //
	String ünvan = kayitliKullanici.getUnvan().replace("%C3%BC", "ü");

	String encodedisim = URLEncoder.encode(isim.toString(), "utf-8");

	String encodedünvan = URLEncoder.encode(
		ünvan.toString().replace("]", "").replace("[", ""), "UTF-8");
	response.addCookie(new Cookie("isim", encodedisim));
	response.addCookie(new Cookie("unvan", encodedünvan));

	System.out.println("BAŞARILI GİRİŞ");

	return new ModelAndView("redirect:/demirbaslistesi");
    }
}
