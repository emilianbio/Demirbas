package controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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

import service.DemirbasService;
import service.KullaniciService;
import service.SabitTipsService;
import forms.Kullanici;
import forms.Sabittips;

@Controller
public class SabitlerController {

	@Autowired
	private SabitTipsService sabitTipsService;
	@Autowired
	private DemirbasService demirbasService;
	@Autowired
	private KullaniciService kullaniciService;
	private Sabittips tips;
	private String tusYazisi = "Ekle";

	@RequestMapping(value = "/sabitler")
	public String sabitlers(HttpSession session, ModelMap model) {
		model.put("modelListesi", sabitTipsService.tipGetir());
		if (tips == null) {
			tips = new Sabittips();
		} else {
			if (tips.getTip() != null)
				if (tips.getTip().getTip() == null) // hi� bir �ey se�ili de�il
													// misal : bilgisayar
				{
					// System.out.println("bilgisayar se�ili");
					// model.put("altTipListesi",
					// sabitTipsService.altTipGetir(tips.getId(), true));
					tips.getTip().setTip(new Sabittips(0));
					tips.getTip().getTip()
							.setTip(new Sabittips(tips.getTip().getId()));
					tips.getTip().setId(0);
					@SuppressWarnings("rawtypes")
					List sonuc = sabitTipsService.altTipGetir(tips.getTip()
							.getTip().getTip().getId(), true);
					model.put("altTipListesi", sonuc);
					model.put("modelListesi", sonuc);
					// tips.getTip().getTip().getTip().setId(tips.getId());
				} else if (tips.getTip().getTip().getTip() == null)// bilgisayar
																	// se�ili
																	// diz�st�
																	// se�ili
																	// de�il
				{
					tips.getTip()
							.getTip()
							.setTip(new Sabittips(tips.getTip().getTip()
									.getId()));
					tips.getTip().getTip().setId(tips.getTip().getId());
					tips.getTip().setId(0);
					// Systgfvem.out.println("diz�st� se�ili");
					// System.out.println(sabitTipsService.tipsGetir(tips.getTip().getTip().getTip().getId()).getIsim());
					@SuppressWarnings("rawtypes")
					List sonuc = sabitTipsService.altTipGetir(tips.getTip()
							.getTip().getId(), true);
					model.put(
							"altTipListesi",
							sabitTipsService.altTipGetir(tips.getTip().getTip()
									.getTip().getId(), true));
					model.put("markaListesi", sonuc);
					model.put("modelListesi", sonuc);
				} else {
					// System.out.println("acer se�ili");
					model.put(
							"altTipListesi",
							sabitTipsService.altTipGetir(tips.getTip().getTip()
									.getTip().getId(), true));
					model.put(
							"markaListesi",
							sabitTipsService.altTipGetir(tips.getTip().getTip()
									.getId(), true));
					model.put("modelListesi", sabitTipsService.altTipGetir(tips
							.getTip().getId(), true));
				}
		}

		model.put("tips", tips);
		
		model.put("tipListesi", sabitTipsService.tipGetir());// MOB�LYA VEYA
																// B�LG�SAYAR

		model.put("tusYazisi", tusYazisi);
		return "sabitler";
	}

	@RequestMapping(value = "/sabitonay", method = RequestMethod.POST)
	public String ekleme(HttpSession session, HttpServletResponse response,
			@ModelAttribute("tips") Sabittips tips1,
			@ModelAttribute("kullanici") Kullanici kullanici,
			@CookieValue(value = "memurid", required = false) Long memurid) {

		if (tips1.getTip().getTip().getTip().getId() == 0)

		{
			tips1.setTip(null);
		} else if (tips1.getTip().getTip().getId() == 0) {
			tips1.setTip(new Sabittips(tips1.getTip().getTip().getTip().getId()));
		} else if (tips1.getTip().getId() == 0) {
			tips1.setTip(new Sabittips(tips1.getTip().getTip().getId()));
		}

		Kullanici kaydeden = new Kullanici();
		kaydeden.setId(memurid);
		tips1.setEklemezamani(new Date());
		tips1.setMemurs(kaydeden);

		if (kaydeden.getId() != 2)
			if (kaydeden.getId() != 1) {
				return "redirect:/sabitler";
			} else {
				sabitTipsService.ekle(tips1);
				tips = tips1;
				tips.setId(0);
				tusYazisi = "Ekle";

			}
		if (!sabitTipsService.isimVarMi(tips1.getIsim()))

			sabitTipsService.ekle(tips1);
		tips = tips1;
		tips.setId(0);
		tusYazisi = "Ekle";
		return "redirect:/sabitler";
	}

	@RequestMapping(value = "/edit/{id}")
	public String sabitEdit(@PathVariable("id") Long Id) {
		tips = sabitTipsService.tipsGetir(Id);
		tusYazisi = "Güncelle";
		return "redirect:/sabitler";
	}

	@RequestMapping(value = "/vazgec")
	public String vazgec(HttpSession session) {
		// sabitlerService.ekle(sabit1);
		// sabit = sabit1;
		tips.setId(0);
		// tips=new Sabittips(); //bo�alt�r
		tusYazisi = "Ekle";
		return "redirect:/sabitler";
	}

	@RequestMapping(value = "/tipsil", method = RequestMethod.POST)
	public @ResponseBody String tipsil(
			@RequestParam(value = "id", required = true) Long id,
			@CookieValue(value = "hitCounter", defaultValue = "0") Long hitCounter,
			HttpServletResponse response) {
		sabitTipsService.tipsil(id);
		response.setCharacterEncoding("UTF-8");

		hitCounter++;

		// create cookie and set it in response
		Cookie cookie = new Cookie("hitCounter", hitCounter.toString());
		response.addCookie(cookie);

		return "{}";
	}

	@RequestMapping(value = "/tipsekle", method = RequestMethod.POST)
	public @ResponseBody byte[] tipsekle(
			@RequestParam(value = "isim", required = true) String isim,
			@RequestParam(value = "katid", required = true) Long katid,
			@RequestParam(value = "durum", required = true) Boolean durum,
			HttpServletResponse response) throws Exception {
		Sabittips tipsi = new Sabittips();
		tipsi.setIsim(isim);
		tipsi.setTip(new Sabittips(katid));
		sabitTipsService.kaydet(tipsi);
		return modellerListesi(katid, durum).toJSONString().getBytes("UTF-8");
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/markagetir", method = RequestMethod.POST)
	@ResponseBody
	public byte[] markaGetir(
			@RequestParam(value = "altTipId", required = true) Long altTipId,
			HttpServletResponse response) throws Exception {
		JSONObject jsonObject = new JSONObject();
		List<Sabittips> altTipListesi = new ArrayList<Sabittips>();
		altTipListesi = sabitTipsService.altTipGetir(altTipId, true);
		Iterator<Sabittips> iterator = altTipListesi.iterator();
		while (iterator.hasNext()) {
			Sabittips tip = iterator.next();
			jsonObject.put(tip.getId(), tip.getIsim());
		}
		return jsonObject.toJSONString().getBytes("UTF-8");
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/alttiplerigetir", method = RequestMethod.POST)
	@ResponseBody
	public byte[] altTipleriGetir(
			@RequestParam(value = "katid", required = true) Long katid,
			HttpServletResponse response) throws Exception {
		// System.out.println(ustId+"****");
		JSONObject jsonObject = new JSONObject();
		List<Sabittips> altTipListesi = new ArrayList<Sabittips>();
		altTipListesi = sabitTipsService.altTipGetir(katid, true);
		Iterator<Sabittips> iterator = altTipListesi.iterator();
		while (iterator.hasNext()) {
			Sabittips tip = iterator.next();
			jsonObject.put(tip.getId(), tip.getIsim());
		}
		return jsonObject.toJSONString().getBytes("UTF-8");
	}

	@RequestMapping(value = "/modelgetir", method = RequestMethod.POST)
	public @ResponseBody byte[] modelGetir(
			@RequestParam(value = "altTipId", required = true) Long altTipId,
			@RequestParam(value = "durum", required = true) Boolean durum)
			throws Exception {

		return modellerListesi(altTipId, durum).toJSONString()
				.getBytes("UTF-8");
	}

	@SuppressWarnings("unchecked")
	private JSONArray modellerListesi(Long altTipId, Boolean durum) {
		JSONArray donecek = new JSONArray();
		List<Sabittips> altTipListesi = new ArrayList<Sabittips>();
		altTipListesi = sabitTipsService.altTipGetir(altTipId, durum);
		Iterator<Sabittips> iterator = altTipListesi.iterator();
		while (iterator.hasNext()) {
			JSONObject jsonObject = new JSONObject();
			Sabittips tip = iterator.next();
			jsonObject.put("id", tip.getId());
			jsonObject.put("isim", tip.getIsim());
			jsonObject.put("kaydeden", tip.getMemurs().getIsim());

			String drm = "Aktif";
			if (tip.getDurum() == false)
				drm = "Pasif";

			jsonObject.put("durum", drm);

			String date = "Bilinmiyor";
			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
					"dd.MM.yyyy HH:mm:ss");
			if (tip.getEklemezamani() != null)
				date = DATE_FORMAT.format(tip.getEklemezamani());
			jsonObject.put("ekleme", date);
			donecek.add(jsonObject);
		}
		return donecek;
	}

}