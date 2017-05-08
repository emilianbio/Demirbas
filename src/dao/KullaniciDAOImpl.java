package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import forms.Kullanici;
import forms.Memurlar;

@Repository
public class KullaniciDAOImpl implements KullaniciDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Kullanici kullaniciGetir(Kullanici kullanici) {

	@SuppressWarnings("unused")
	Kullanici sonuc = null;

	Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
		Kullanici.class);
	System.out.println("giriş33333");
	criteria.add(Example.create(kullanici));
	System.out.println("giriş444444");
	@SuppressWarnings("unchecked")
	List<Kullanici> kullaniciListe = criteria.list();
	System.out.println("giriş555555");
	if (kullaniciListe != null && kullaniciListe.size() > 0) {
	    System.out.println("giriş666666");
	    return kullaniciListe.get(0);
	}
	System.out.println("giriş777777");
	return null;
    }

    @Override
    public Memurlar kullaniciGiris(Memurlar kullanici,
	    HttpServletRequest request, HttpServletResponse response)
	    throws SQLException, ClassNotFoundException {

	String Isim = request.getParameter("Isim");
	String sifre = request.getParameter("sifre");
	String url = "jdbc:sqlserver://localhost:1433;databaseName=java_db";
	String kullaniciad = "sa";
	// String sifre = "saricam%tarim+2010";
	String sifreSQL = "1234";
	Connection con = null;
	@SuppressWarnings("unused")
	Statement st = null;
	ResultSet rs = null;

	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	con = DriverManager.getConnection(url, kullaniciad, sifreSQL);
	// st = con.createStatement();

	String sorgu = "select Isim,sifre,MemurID,unvan from Memurlar where Isim= '"
		+ Isim + "' and sifre = '" + sifre + "'";

	PreparedStatement ps = con.prepareStatement(sorgu);
	// ps.setString(2, Isim);
	// ps.setString(7, sifre);
	rs = ps.executeQuery();

	Memurlar kullanici2 = new Memurlar();
	List<Memurlar> kullaniciListe = new ArrayList<Memurlar>();

	try {
	    if (rs.next()) {

		kullanici2.setMemurID(rs.getLong("MemurID"));
		kullanici2.setIsim(rs.getString("Isim"));
		kullanici2.setSifre(rs.getString("sifre"));
		kullanici2.setUnvan(rs.getString("unvan"));
		// kullaniciListe.add(kullanici2);

	    }
	} catch (Exception ex) {
	    System.err.println("RS:" + ex.getMessage());
	}

	if (kullaniciListe != null && kullaniciListe.size() > 0) {
	    return kullaniciListe.get(0);
	}
	return kullanici2;

    }

}
