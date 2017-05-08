package controllers;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import service.DemirbasService;
import service.SabitTipsService;
import forms.Demirbas;
import forms.Memurlar;

@Controller
public class DemirbasController {

    @Autowired
    private SabitTipsService sabitTipsService;
    @Autowired
    private DemirbasService demirbasService;
    private String hata = "0";
    private Demirbas demirbas;
    private String tusYazisi = "Ekle";

    @RequestMapping("/demirbaslistesi")
    public String demirbaslistesi(
	    @RequestParam(value = "sayfano", required = false) Integer sayfaNo,
	    @ModelAttribute("demirbas") Demirbas demirbas1,
	    @CookieValue(value = "isim", required = false) String cookie,
	    ModelMap model, HttpServletResponse response, Long id) {
	if (sayfaNo == null)
	    sayfaNo = 1;
	if (cookie == null) {
	    model.put("kullanici", new Memurlar());
	    return "giris";
	} else {
	    model.put("tipListesi", sabitTipsService.tipGetir());
	}
	if (demirbas == null) {
	    demirbas = new Demirbas();
	}

	else {

	    if (demirbas.getTip() != null) {
		model.put("altTipListesi", sabitTipsService.altTipGetir(
			demirbas.getTip().getId(), true));
		if (demirbas.getAlttip() != null) {
		    model.put("markaListesi", sabitTipsService.altTipGetir(
			    demirbas.getAlttip().getId(), true));
		    if (demirbas.getMarka() != null) {
			model.put("modelListesi", sabitTipsService.altTipGetir(
				demirbas.getMarka().getId(), true));
		    }
		}
	    }
	}

	model.put("demirbas", demirbas);
	model.put("sayfa", sayfaNo);
	model.put("sayfalar", araclar.Genel.sayfalar(sayfaNo,
		demirbasService.kayitSayisi(), "/demirbas/demirbaslistesi"));
	model.put("demirbasListesi1", demirbasService.demirbasListesi(sayfaNo));
	model.put("tusYazisi", tusYazisi);
	model.put("error", hata);
	hata = "0";
	return "demirbaslistesi";
    }

    @Transactional
    @RequestMapping(value = "/demirbasonay", method = RequestMethod.POST)
    public String demirbasEkleme(HttpSession session,
	    @ModelAttribute("demirbas") Demirbas demirbas1,
	    @CookieValue(value = "memurid", required = false) Long memurid,
	    HttpServletRequest request, ModelMap model, Object errorMessage)

    {
	// Kullanici kaydeden = new Kullanici();

	demirbas1.setKaydeden(memurid);
	demirbas1.setEklemezamani(new Date());
	if (tusYazisi == "Ekle")
	    if (demirbas1.getTip().getId() != 0
		    && demirbas1.getAlttip() != null
		    && demirbas1.getAlttip().getId() != 0
		    && demirbas1.getMarka() != null
		    && demirbas1.getModel() != null
		    && !demirbasService.demirbasnoVarMi(demirbas1
			    .getDemirbasno())
		    && !demirbas1.getDemirbasno().isEmpty()
		    && demirbas1.getDemirbasno() != null)

	    {
		demirbas.setDemirbasID(0);
		demirbasService.demirbasEkle(demirbas1);

	    }

	    else {

		hata = "1";
	    }
	else {
	    demirbasService.updateDemirbas(demirbas1);
	}

	return ("redirect:/demirbaslistesi");
    }

    @RequestMapping(value = "/demirbasEdit/{id}")
    public String demirbasEdit(@PathVariable("id") Long id,
	    @RequestParam(value = "sayfano", required = false) Integer sayfaNo) {
	tusYazisi = "Güncelle";
	demirbas = demirbasService.demirbasGetir(id);

	return ("redirect:/demirbaslistesi");
    }

    @RequestMapping(value = "/demirbasVazgec")
    public ModelAndView demirbasVazgec(HttpSession session) {
	demirbas.setAded(0);
	demirbas.setDemirbasno(null);
	demirbas.setTip(null);
	demirbas.setMarka(null);
	demirbas.setTutar(null);
	demirbas.setSerino(null);
	demirbas.setIzahat(null);
	demirbas.setDemirbasID(0);
	tusYazisi = "Ekle";
	return new ModelAndView("redirect:/demirbaslistesi");
    }

    @RequestMapping(value = "/demirbassil", method = RequestMethod.POST)
    public @ResponseBody String demirbassil(
	    @RequestParam(value = "id", required = true) Long id,
	    @CookieValue(value = "hitcounter", defaultValue = "0") Long hitCounter,
	    HttpServletResponse response) {
	demirbasService.demirbassil(id);
	response.setCharacterEncoding("UTF-8");
	
	hitCounter++;

	// create cookie and set it in response
	Cookie cookie = new Cookie("hitCounter", hitCounter.toString());
	response.addCookie(cookie);
	return "{}";
    }

}