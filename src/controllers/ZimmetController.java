package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
import org.springframework.web.servlet.ModelAndView;

import service.DemirbasService;
import service.KullaniciService;
import service.MemurlarService;
import service.PersonelService;
import service.SabitTipsService;
import service.SabitlerService;
import service.ZimmetService;
import forms.Demirbas;
import forms.Kullanici;
import forms.Memurlar;
import forms.Sabitler;
import forms.Sabittips;
import forms.Unvanlar;
import forms.Zimmet;

@Controller
public class ZimmetController {
    static String conStr = "jdbc:sqlserver://localhost:1433;databaseName=java_db;user=sa;password=1234";
    ArrayList<Memurlar> personelListesi = new ArrayList<Memurlar>();
    ArrayList<Sabitler> sabitlerListesi = new ArrayList<Sabitler>();
    ArrayList<Zimmet> zimmetListesi = new ArrayList<Zimmet>();
    ArrayList<Unvanlar> unvanListesi = new ArrayList<Unvanlar>();
    ArrayList<Sabittips> alttipListesi = new ArrayList<Sabittips>();
    ArrayList<Sabittips> markavemodelListesi = new ArrayList<Sabittips>();

    String cekilenVeri = null;

    @Autowired
    private ZimmetService zimmetService;
    @Autowired
    private PersonelService personelService;
    @Autowired
    private SabitTipsService sabitTipsService;
    @Autowired
    private DemirbasService demirbasService;
    @Autowired
    private KullaniciService kullaniciService;

    @Autowired
    private MemurlarService memurlarService;
    @Autowired
    private SabitlerService sabitlerService;

    private String hata = "0";
    private Demirbas demirbas = null;
    private String tusYazisi = "Ekle";
    public Date now = new Date();
    public SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    @SuppressWarnings("unused")
    private Zimmet zimmet;

    @RequestMapping("/zimmet")
    public ModelAndView zimmet(@ModelAttribute("demirbas") Demirbas demirbas1,
	    ModelMap model, HttpServletResponse response, Long id, String zm)
	    throws ClassNotFoundException, SQLException {

	model.put("personelListesi", memurlarService.personelListesi());
	model.put("baslamatarihi", sdf.format(now));

	model.put("sabitlerListesi", sabitlerService.sabitlerListesi());
	// model.put("unvanListesi", unvanListesi);
	model.put("zimmetListesi", zimmetService.zimmetListesi(zm));
	model.put("demirbas", demirbas);
	model.put("tusYazisi", tusYazisi);
	model.put("error", hata);
	hata = "0";

	Class.forName("org.postgresql.Driver");
	Connection conn = DriverManager.getConnection(
		"jdbc:postgresql://localhost:5432/demirbasdb", "postgres",
		"1234");
	String sorgu = "select  * from Zimmet ";
	Statement statement = conn.createStatement();
	ResultSet rs0 = statement.executeQuery(sorgu);
	// ResultSet rs2 = statement.executeQuery(sorgu);

	int zimmetMemurid;

	zimmetListesi.clear();

	while (rs0.next()) {

	    Zimmet zimmet = new Zimmet();
	    Memurlar memur = new Memurlar();

	    zimmetMemurid = rs0.getInt(2);

	    // veri tabani islemleri
	    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	    // String url =
	    // "jdbc:sqlserver://10.1.0.10:1433;databaseName=MemurSQL";
	    String url = "jdbc:sqlserver://localhost:1433;databaseName=java_db";
	    String kullaniciad = "sa";
	    // String sifre = "saricam%tarim+2010";
	    String sifre = "1234";
	    Connection con = null;
	    Statement st = null;
	    ResultSet rs2 = null;

	    con = DriverManager.getConnection(url, kullaniciad, sifre);
	    st = con.createStatement();

	    // verileri siraladigimiz bölüm
	    // String vericek2 =
	    // "select MemurID,Isim,TCNo,kaza_id,Vazifesi from Memurlar order by Isim ASC";
	    String vericek = "select *  from Memurlar where MemurID =   "
		    + " ' " + zimmetMemurid + " ' " + "order by Isim ASC";

	    rs2 = st.executeQuery(vericek);
	    // personelListesi.clear();
	    while (rs2.next()) {

		System.out.println(" ID=*" + rs2.getLong("MemurID") + " "
			+ "Isim=" + rs2.getString("Isim") + "  Şube "
			+ rs0.getString("kazaid") + "  Kaydeden= "
			+ rs0.getInt("kaydeden") + " Demirbasno"
			+ rs0.getString("demirbasno") + "Alttip "
			+ rs0.getString("alttipid") + "Marka "
			+ rs0.getString("markaid") + "Model "
			+ rs0.getString("modelid") + "Ekleme Zamanı "
			+ rs0.getString("eklemezamani") + "Baslama Tarihi"
			+ rs0.getString("baslamatarihi") + "Bitiş Tarihi"
			+ rs0.getString("bitistarihi"));

		// cekilenVeri = (rs.getString("Isim") + "  "
		// + rs.getString("TCNo") + " " + rs
		// .getLong("MemurID"));
		memur.setMemurID(rs2.getInt("MemurID"));
		memur.setIsim(rs2.getString("Isim"));

		// zimmet.setMemurid((long) rs.getObject(4));
		personelListesi.add(memur);
		zimmetListesi.add(zimmet);

	    }

	    Class.forName("org.postgresql.Driver");
	    Connection conn2 = DriverManager.getConnection(
		    "jdbc:postgresql://localhost:5432/demirbasdb", "postgres",
		    "1234");
	    String sorgu2 = "select  * from unvanlar order by isim asc ";
	    Statement statement2 = conn2.createStatement();
	    ResultSet rs5 = statement2.executeQuery(sorgu2);
	    unvanListesi.clear();
	    while (rs5.next()) {
		Unvanlar unvan = new Unvanlar();
		unvan.setId(rs5.getLong("id"));
		unvan.setIsim(rs5.getString("isim"));

		unvanListesi.add(unvan);

	    }

	}
	ModelAndView mv = new ModelAndView("zimmet");
	// mv.addObject("personelListesi", personelListesi);
	mv.addObject("unvanListesi", unvanListesi);
	return mv;
    }

    @RequestMapping("/zimmet2")
    public String zimmet2(@ModelAttribute("zimmet") Zimmet zimmet1,
	    @CookieValue(value = "isim", required = false) String cookie,
	    ModelMap model, HttpServletResponse response, Long id, String zm)
	    throws ClassNotFoundException, SQLException {
	if (cookie == null) {
	    model.put("kullanici", new Memurlar());
	    return "giris";
	}
	model.put("personelListesi", memurlarService.personelListesi());
	model.put("baslamatarihi", sdf.format(now));
	model.put("zimmetListesi1", zimmetService.zimmetListesi(zm));
	model.put("demirbas", demirbas);
	model.put("tusYazisi", tusYazisi);
	model.put("error", hata);
	hata = "0";

	return "zimmet2";
    }

    @RequestMapping("/zimmetlistesi")
    // ZİMMETLİSTESİ JSP SAYFASI
    public String zimmetlistesi(ModelMap model,
	    @RequestParam("memurid") Long memurid,
	    @RequestParam(value = "sayfano", required = false) Integer sayfaNo,
	    @ModelAttribute("zimmet") Zimmet zimmet,
	    @CookieValue(value = "isim", required = false) String cookie,
	    String zm, Long MemurID) throws ClassNotFoundException,
	    SQLException {

	if (cookie == null) {
	    model.put("kullanici", new Memurlar());
	    return "giris";
	}
	model.put("zimet", zimmet);
	model.put("zimmetListesi", zimmetService.zimmetListesi(zm));
	model.put("personelListesi", memurlarService.personelListesi());
	model.put("zimmet",
		memurlarService.üzerindeZimmetOlanPersonelGetir(memurid));

	return "/zimmetlistesi";
    }

    @RequestMapping("/zimmetlistesi2")
    // ZİMMETLİSTESİ2 JSP SAYFASI
    public ModelAndView zimmetListesi2(
	    @ModelAttribute("zimmet") Zimmet zimmet2, ModelMap model,
	    HttpServletResponse response, Long id,@CookieValue(value = "isim", required = false) String cookie, String zm)
	    throws ClassNotFoundException, SQLException {

	if (cookie == null) {
	    model.put("kullanici", new Memurlar());
	    ModelAndView giris = new ModelAndView("giris");
		// mv.addObject("personelListesi", personelListesi);
		// mv.addObject("sabitlerListesi", sabitlerListesi);
		return giris;
	}
	
	model.put("zimmet", zimmet2);
	model.put("personelListesi", memurlarService.personelListesi());
	model.put("baslamatarihi", sdf.format(now));

	model.put("sabitlerListesi", sabitlerService.sabitlerListesi());

	model.put("zimmetListesi", zimmetService.zimmetListesi(zm));
	// model.put("demirbas", demirbas);
	model.put("tusYazisi", tusYazisi);
	model.put("error", hata);
	hata = "0";

	Class.forName("org.postgresql.Driver");
	Connection conn = DriverManager.getConnection(
		"jdbc:postgresql://localhost:5432/demirbasdb", "postgres",
		"1234");
	String sorgu = "select  * from Zimmet ";
	Statement statement = conn.createStatement();
	ResultSet rs0 = statement.executeQuery(sorgu);
	// ResultSet rs2 = statement.executeQuery(sorgu);

	int zimmetMemurid;

	zimmetListesi.clear();

	while (rs0.next()) {

	    Zimmet zimmet = new Zimmet();
	    Memurlar memur = new Memurlar();

	    zimmetMemurid = rs0.getInt(2);

	    // veri tabani islemleri
	    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	    // String url =
	    // "jdbc:sqlserver://10.1.0.10:1433;databaseName=MemurSQL";
	    String url = "jdbc:sqlserver://localhost:1433;databaseName=java_db";
	    String kullaniciad = "sa";
	    // String sifre = "saricam%tarim+2010";
	    String sifre = "1234";
	    Connection con = null;
	    Statement st = null;
	    ResultSet rs2 = null;

	    con = DriverManager.getConnection(url, kullaniciad, sifre);
	    st = con.createStatement();

	    // verileri siraladigimiz bölüm
	    // String vericek2 =
	    // "select MemurID,Isim,TCNo,kaza_id,Vazifesi from Memurlar order by Isim ASC";
	    String vericek = "select *  from Memurlar where MemurID =   "
		    + " ' " + zimmetMemurid + " ' " + "order by Isim ASC";

	    rs2 = st.executeQuery(vericek);
	    // personelListesi.clear();
	    while (rs2.next()) {

		System.out.println(" ID=*" + rs2.getLong("MemurID") + " "
			+ "Isim=" + rs2.getString("Isim") + "  Şube "
			+ rs0.getString("kazaid") + "  Kaydeden= "
			+ rs0.getInt("kaydeden") + " Demirbasno"
			+ rs0.getString("demirbasno") + "Alttip "
			+ rs0.getString("alttipid") + "Marka "
			+ rs0.getString("markaid") + "Model "
			+ rs0.getString("modelid") + "Ekleme Zamanı "
			+ rs0.getString("eklemezamani") + "Baslama Tarihi"
			+ rs0.getString("baslamatarihi") + "Bitiş Tarihi"
			+ rs0.getString("bitistarihi"));

		// cekilenVeri = (rs.getString("Isim") + "  "
		// + rs.getString("TCNo") + " " + rs
		// .getLong("MemurID"));
		memur.setMemurID(rs2.getInt("MemurID"));
		memur.setIsim(rs2.getString("Isim"));

		// zimmet.setMemurid((long) rs.getObject(4));
		personelListesi.add(memur);
		zimmetListesi.add(zimmet);

	    }

	}
	ModelAndView mv = new ModelAndView("zimmetlistesi2");
	// mv.addObject("personelListesi", personelListesi);
	// mv.addObject("sabitlerListesi", sabitlerListesi);
	return mv;
    }

    @Transactional
    @RequestMapping(value = "/zimmetle", method = RequestMethod.POST)
    // ZİMMETLİSTESİ2 JSPDEKİ BUTTON SUBMİT
    public String zimmetEkle(@ModelAttribute("zimmet") Zimmet zimmet,
	    @CookieValue(value = "memurid", required = false) Long memurid,
	    @CookieValue(value = "isim", required = false) String cookie,
	    Object errorMessage, ModelMap model) {

	if (zimmet.getMarka().getId() != 0 && zimmet.getModel().getId() != 0) {
	    Kullanici kullanici = new Kullanici();
	    kullanici.setId(memurid);
	    zimmet.setMemurs(kullanici);
	    zimmet.setEklemezamani(new Date());
	    zimmet.setId(0);
	    zimmetService.zimmetEkle(zimmet);

	} else {

	    hata = "1";
	}
	return ("redirect:/zimmetlistesi2");
    }

    @Transactional
    @RequestMapping(value = "/zimmetonay", method = RequestMethod.POST)
    // ZİMMET JSPDEKİ BUTTON SUBMİT
    public String zimmetOnay(@RequestParam("aded") Long aded,
	    HttpServletRequest req, Zimmet zimmet, Memurlar memur,
	    @CookieValue(value = "memurid", required = false) Long memurid,
	    Object errorMessage) {
	Kullanici kaydeden = new Kullanici();
	kaydeden.setId(memurid);
	zimmet.setMemurs(kaydeden);
	zimmet.setEklemezamani(new Date());
	zimmet.setId(0);
	zimmet.setAlttip(demirbas.getAlttip());
	zimmet.setMarka(demirbas.getMarka());
	zimmet.setModel(demirbas.getModel());
	zimmet.setDemirbasID(demirbas.getDemirbasID());

	System.out.println("11111111111111111111111");

	System.out.println("22222222222222222");
	if (!zimmet.getBaslamatarihi().after(now) && zimmet.getKazaid() != 0
		&& zimmet.getMemurid() != 0) {
	    System.out.println("33333333333333333333");

	    zimmetService.zimmetEkle(zimmet);

	    System.out.println("11111111111111111111111");
	    demirbas.setAded(demirbas.getAded() - aded);
	    demirbasService.updateDemirbas(demirbas);
	    demirbas.setAlttip(null);
	    demirbas.setMarka(null);
	    demirbas.setModel(null);
	    demirbas.setDemirbasno(null);
	    System.out.println("4444444444444444444");
	}

	else {

	    hata = "1";

	}

	return ("redirect:/zimmetlistesi5");
    }

    @Transactional
    @RequestMapping(value = "/zimmetİcinDemirbasGetir/{id}")
    public String zimmetEdit(@PathVariable("id") Long id,
	    @RequestParam(value = "sayfano", required = false) Integer sayfaNo) {

	demirbas = demirbasService.demirbasGetir(id);

	return ("redirect:/zimmet");
    }

    @Transactional
    @RequestMapping(value = "/zimmetEdit", method = RequestMethod.GET)
    public String zimmetEdit(@RequestParam("id") Long id, ModelMap model,
	    Demirbas demirbas, String zm) {
	model.put("zimmetListesi1", zimmetService.zimmetListesi(zm));
	System.out.println("1*********************");
	model.put("m", zimmetService.zimmetPersonelGetir(id));
	// zimmet = zimmetService.zimmetGetir(id);
	System.out.println("2*********************");
	return "zimmet2";
    }

    @RequestMapping(value = "/zimmetVazgec")
    public ModelAndView zimmetVazgec(HttpSession session) {

	if (demirbas.getAlttip() != null || demirbas.getMarka() != null
		|| demirbas.getModel() != null
		|| demirbas.getDemirbasno() != null) {
	    demirbas.setAlttip(null);
	    demirbas.setModel(null);
	    demirbas.setMarka(null);
	    demirbas.setDemirbasno(null);
	} else {

	    hata = "1";
	}
	return new ModelAndView("redirect:/demirbaslistesi");
    }

    @Transactional
    @RequestMapping(value = "/zimmetSil", method = RequestMethod.GET)
    public String zimmetSil(
	    @RequestParam(value = "id", required = true) Long id,
	    @CookieValue(value = "hitcounter", defaultValue = "0") Long hitCounter,
	    HttpServletResponse response, ModelMap model, Zimmet zimmet) {

	model.put("id", id);

	zimmetService.zimmetSil(id);

	response.setCharacterEncoding("UTF-8");

	return "redirect:/yonlendir";

    }

    @Transactional
    @RequestMapping(value = "/demirbasnoarama", method = RequestMethod.GET)
    public String arama(@RequestParam(value = "demirbasno") String demirbasno,
	    ModelMap model, String zm, Long memurid) {

	model.put("personellistesi", zimmetService.personelIdGetir(memurid));
	model.put("demirbasnolistesi", zimmetService.zimmetListesi(zm));
	model.put("m", zimmetService.zimmetDemirbasNoGetir(demirbasno));

	return "zimmetlistesi3";
    }

    @RequestMapping("/zimmetlistesi3")
    public String zimmetle(ModelMap model, String zm, Long memurid) {

	model.put("demirbasnolistesi", zimmetService.zimmetListesi(zm));
	model.put("personellistesi", zimmetService.personelIdGetir(memurid));

	return "zimmetlistesi3";
    }

    @RequestMapping("/yonlendir")
    public String yönlendir() {

	return "yonlendir";
    }

    @Transactional
    @RequestMapping(value = "/resultsetarama", method = RequestMethod.GET)
    public String resultsetArama(@RequestParam(value = "id") Long id,
	    ModelMap model) throws ClassNotFoundException, SQLException {

	ArrayList<Zimmet> zimmetListesi = new ArrayList<Zimmet>();
	model.put("islemtarihi", sdf.format(now));
	Class.forName("org.postgresql.Driver");
	Connection conn = DriverManager.getConnection(
		"jdbc:postgresql://localhost:5432/demirbasdb", "postgres",
		"1234");

	Statement statement = conn.createStatement();

	// String memurid = request.getParameter("memurid");
	ResultSet rsZimmet = statement
		.executeQuery("select  z.id,s1.isim ,s2.isim ,s3.isim,z.demirbasno,z.baslamatarihi,z.bitistarihi,z.eklemezamani, k.isim ,z.memurid,z.kazaid ,z.unvan,u.isim,z.serino from zimmet z full join unvanlar u on z.unvan=u.id inner join sabittips s1 on z.alttipid = s1.id inner join sabittips s2 on z.markaid = s2.id  inner join sabittips s3 on z.modelid = s3.id inner join kullanici k on z.kaydeden = k.id where z.memurid= "
			+ id + "order by eklemezamani desc ");

	// ResultSet rs2 = statement.executeQuery(sorgu);
	int zimmetId = 0;
	int zimmetKazaid = 0;
	// zimmetListesi.clear();

	alttipListesi.clear();
	markavemodelListesi.clear();

	while (rsZimmet.next()) {

	    System.out.println(rsZimmet.getString("id"));

	    zimmetId = rsZimmet.getInt("id");
	    zimmetKazaid = rsZimmet.getInt("kazaid");
	    Zimmet zimmet1 = new Zimmet();
	    zimmet1.setDemirbasno(rsZimmet.getString(5));
	    String altTip = rsZimmet.getString(2);
	    String ozellikleri = rsZimmet.getString(3) + "/ "
		    + rsZimmet.getString(4);
	    String seriNo = rsZimmet.getString(14);
	    String zimmetYapılanPersonelÜnvan = rsZimmet.getString(13);

	    Sabittips alttip = new Sabittips();
	    Sabittips markavemodel = new Sabittips();

	    alttip.setIsim(rsZimmet.getString(2));
	    markavemodel.setIsim(ozellikleri);

	    alttipListesi.add(alttip);
	    markavemodelListesi.add(markavemodel);
	    zimmetListesi.add(zimmet1);
	    model.put("alttipListesi", alttipListesi);
	    model.put("markavemodelListesi", markavemodelListesi);
	    // model.put("modelListesi", modelListesi);
	    model.put("demirbasno", zimmetListesi);
	    model.put("alttip", altTip);
	    model.put("özellikler", ozellikleri);
	    model.put("ünvan", zimmetYapılanPersonelÜnvan);
	    model.put("seriNo", seriNo);

	    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	    // String url =
	    // "jdbc:sqlserver://10.1.0.10:1433;databaseName=MemurSQL";
	    String url = "jdbc:sqlserver://localhost:1433;databaseName=java_db";
	    String kullaniciad = "sa";
	    // String sifre = "saricam%tarim+2010";
	    String sifre = "1234";
	    Connection con = null;
	    Statement st = null;
	    ResultSet rsMemurlar = null;
	    con = DriverManager.getConnection(url, kullaniciad, sifre);
	    st = con.createStatement();

	    String vericek = "select *  from Memurlar where MemurID =   "
		    + " ' " + zimmetId + " ' " + "order by Isim ASC";

	    rsMemurlar = st.executeQuery(vericek);
	    while (rsMemurlar.next()) {

		String personelIsmi = rsMemurlar.getString("Isim");

		model.put("personelismi", personelIsmi);

		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		// String url2 =
		// "jdbc:sqlserver://10.1.0.10:1433;databaseName=MemurSQL";
		String url2 = "jdbc:sqlserver://localhost:1433;databaseName=java_db";
		String kullaniciad2 = "sa";
		// String sifre2 = "saricam%tarim+2010";
		String sifre2 = "1234";
		Connection con2 = null;
		Statement st2 = null;
		ResultSet rsSabitler = null;
		con2 = DriverManager.getConnection(url2, kullaniciad2, sifre2);
		st2 = con2.createStatement();

		String vericek2 = "select *  from Sabitler where KazaID =   "
			+ " ' " + zimmetKazaid + " ' ";

		rsSabitler = st2.executeQuery(vericek2);

		while (rsSabitler.next()) {

		    String ilçeismi = rsSabitler.getString("IlceIsmi");
		    model.put("ilçeismi", ilçeismi);

		}
		rsSabitler.close();
	    }
	    rsMemurlar.close();
	}
	rsZimmet.close();

	return "deneme";
    }

    @RequestMapping("/zimmetyadırResultSet")
    public String result(Long memurid, ModelMap model)
	    throws ClassNotFoundException, SQLException {

	return "zimmetyadırResultSet";

    }

    @RequestMapping("/zimmetlistesi5")
    public String memurlar(ModelMap model, String zm) {

	model.put("zimmetlistesi", zimmetService.zimmetListesi(zm));

	return "zimmetlistesi5";

    }

    @Transactional
    @RequestMapping(value = "/yazdır")
    public String yazdırma(@RequestParam(value = "id") Long id, ModelMap model,
	    Zimmet zimmet, String siraNo, HttpServletResponse response,
	    Object errorMessage) {

	model.put("zimmet", zimmet);
	model.put("islemtarihi", sdf.format(now));

	model.put("sn", siraNo);
	// if (!unvan.isEmpty()) {

	model.put("zimmetyapılanpersonel",
		zimmetService.zimmetPersonelGetir(id));
	// return "zimmetyazdır";

	// } else {

	// hata = "1";
	// model.put("error", hata);
	// System.out.println("********************");

	// }
	return "zimmetyazdır";
	// return ("redirect:/zimmetlistesi");
    }

    @RequestMapping("/zimmetyazdır")
    public String zimmetl(@PathVariable("id") Long id, ModelMap model,
	    String zm, Long memurid, Object errorMessage) {

	hata = "1";
	model.put("error", hata);
	System.out.println("********************");

	return "zimmetyazdır";
    }

    @RequestMapping("/deneme")
    public String deneme(@RequestParam("memurid") Long memurid, Long id,
	    HttpServletRequest request, HttpServletResponse response,
	    ModelMap model) throws ClassNotFoundException, SQLException {

	ArrayList<Zimmet> zimmetListesi = new ArrayList<Zimmet>();
	model.put("islemtarihi", sdf.format(now));
	Class.forName("org.postgresql.Driver");
	Connection conn = DriverManager.getConnection(
		"jdbc:postgresql://localhost:5432/demirbasdb", "postgres",
		"1234");

	Statement statement = conn.createStatement();

	// String memurid = request.getParameter("memurid");
	ResultSet rsZimmet = statement
		.executeQuery("select  z.id,s1.isim ,s2.isim ,s3.isim,z.demirbasno,z.baslamatarihi,z.bitistarihi,z.eklemezamani, k.isim ,z.memurid,z.kazaid ,z.unvan,u.isim,z.serino from zimmet z full join unvanlar u on z.unvan=u.id inner join sabittips s1 on z.alttipid = s1.id inner join sabittips s2 on z.markaid = s2.id  inner join sabittips s3 on z.modelid = s3.id inner join kullanici k on z.kaydeden = k.id where z.memurid= "
			+ memurid + "order by eklemezamani desc ");

	// ResultSet rs2 = statement.executeQuery(sorgu);
	int zimmetMemurid = 0;
	int zimmetKazaid = 0;
	// zimmetListesi.clear();

	alttipListesi.clear();
	markavemodelListesi.clear();

	while (rsZimmet.next()) {

	    System.out.println(rsZimmet.getString("memurid"));

	    zimmetMemurid = rsZimmet.getInt("memurid");
	    zimmetKazaid = rsZimmet.getInt("kazaid");
	    Zimmet zimmet1 = new Zimmet();
	    zimmet1.setDemirbasno(rsZimmet.getString(5));
	    zimmet1.setSerino(rsZimmet.getString("serino"));
	    String altTip = rsZimmet.getString(2);
	    String ozellikleri = rsZimmet.getString(3) + "/ "
		    + rsZimmet.getString(4);
	    @SuppressWarnings("unused")
	    String seriNo = rsZimmet.getString(14);
	    String zimmetYapılanPersonelÜnvan = rsZimmet.getString(13);

	    Sabittips alttip = new Sabittips();
	    Sabittips markavemodel = new Sabittips();

	    alttip.setIsim(rsZimmet.getString(2));
	    markavemodel.setIsim(ozellikleri);

	    alttipListesi.add(alttip);
	    markavemodelListesi.add(markavemodel);
	    zimmetListesi.add(zimmet1);
	    model.put("alttipListesi", alttipListesi);
	    model.put("markavemodelListesi", markavemodelListesi);
	    // model.put("modelListesi", modelListesi);
	    model.put("demirbasno", rsZimmet.getString(5));
	    model.put("alttip", altTip);
	    model.put("özellikler", ozellikleri);
	    model.put("ünvan", zimmetYapılanPersonelÜnvan);
	    model.put("seriNo", zimmetListesi);
	    model.put("zimmetyapılanpersonel",
		    zimmetService.zimmetPersonelGetir(memurid));
	    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	    // String url =
	    // "jdbc:sqlserver://10.1.0.10:1433;databaseName=MemurSQL";
	    String url = "jdbc:sqlserver://localhost:1433;databaseName=java_db";
	    String kullaniciad = "sa";
	    // String sifre = "saricam%tarim+2010";
	    String sifre = "1234";
	    Connection con = null;
	    Statement st = null;
	    ResultSet rsMemurlar = null;
	    con = DriverManager.getConnection(url, kullaniciad, sifre);
	    st = con.createStatement();

	    String vericek = "select *  from Memurlar where MemurID =   "
		    + " ' " + zimmetMemurid + " ' " + "order by Isim ASC";

	    rsMemurlar = st.executeQuery(vericek);
	    while (rsMemurlar.next()) {

		String personelIsmi = rsMemurlar.getString("Isim");

		model.put("personelismi", personelIsmi);

		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		// String url2 =
		// "jdbc:sqlserver://10.1.0.10:1433;databaseName=MemurSQL";
		String url2 = "jdbc:sqlserver://localhost:1433;databaseName=java_db";
		String kullaniciad2 = "sa";
		// String sifre2 = "saricam%tarim+2010";
		String sifre2 = "1234";
		Connection con2 = null;
		Statement st2 = null;
		ResultSet rsSabitler = null;
		con2 = DriverManager.getConnection(url2, kullaniciad2, sifre2);
		st2 = con2.createStatement();

		String vericek2 = "select *  from Sabitler where KazaID =   "
			+ " ' " + zimmetKazaid + " ' ";

		rsSabitler = st2.executeQuery(vericek2);

		while (rsSabitler.next()) {

		    String ilçeismi = rsSabitler.getString("IlceIsmi");
		    model.put("ilçeismi", ilçeismi);

		}
		rsSabitler.close();
	    }
	    rsMemurlar.close();
	}
	rsZimmet.close();

	return "deneme";
    }

    @Transactional
    @RequestMapping(value = "/personelarama", method = RequestMethod.GET)
    public String personelArama(@RequestParam(value = "memurid") Long id,
	    ModelMap model, String zm, Long memurid)
	    throws ClassNotFoundException, SQLException {

	model.put("personellistesi", zimmetService.personelIdGetir(memurid));
	model.put("demirbasnolistesi", zimmetService.zimmetListesi(zm));
	model.put("m", zimmetService.zimmetPersonelGetir(id));

	return "zimmetlistesi3";
    }
}
