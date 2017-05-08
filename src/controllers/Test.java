package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import forms.Zimmet;

public class Test {

    public static void main(String[] args) {

	javax.xml.ws.Endpoint.publish(
		"http://localhost:8080/demirbas/services/Test", new Test());

    }

    private List<Zimmet> zimmetListele() {

	List<Zimmet> zimmetListesi = new ArrayList<Zimmet>();
	Zimmet zimmet = new Zimmet();
	try {
	    Class.forName("org.postgresql.Driver");
	} catch (ClassNotFoundException e) {

	    e.printStackTrace();
	}

	Connection conn = null;
	try {
	    conn = DriverManager.getConnection(
		    "jdbc:postgresql://localhost:5432/demirbasdb", "postgres",
		    "1234");
	} catch (SQLException e) {

	    e.printStackTrace();
	}
	String sorgu = "select  z.memurid,z.kazaid, z.id,z.alttipid,z.markaid,z.modelid , s1.isim ,s2.isim ,s3.isim,p.isim,b.isim,k.isim from zimmet z inner join sabittips s1 on z.alttipid = s1.id inner join sabittips s2 on z.markaid = s2.id inner join sabittips s3 on z.modelid = s3.id inner join personel p on z.memurid = p.id inner join birimler b on z.kazaid = b.kazaid inner join kullanici k on z.kaydeden = k.id ";
	Statement statement = null;
	try {
	    statement = conn.createStatement();
	} catch (SQLException e1) {
	    e1.printStackTrace();
	}
	ResultSet rs = null;
	try {
	    rs = statement.executeQuery(sorgu);
	} catch (SQLException e) {
	    e.printStackTrace();
	}

	try {
	    while (rs.next()) {

		zimmet.setId(rs.getLong("id"));
		zimmet.setMemurid(rs.getLong("memurid"));
		zimmet.setKazaid(rs.getLong("kazaid"));

		zimmetListesi.add(zimmet);

		zimmetListesi.clear();

	    }

	} catch (SQLException e) {

	    e.printStackTrace();
	}
	return zimmetListesi;
    }
}