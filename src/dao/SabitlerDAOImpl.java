package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import forms.Sabitler;

@Repository
public class SabitlerDAOImpl implements SabitlerDAO {

    // String url = "jdbc:sqlserver://10.1.0.10:1433;databaseName=MemurSQL";
    String url = "jdbc:sqlserver://localhost:1433;databaseName=java_db";
    String kullaniciad = "sa";
    // String sifre = "saricam%tarim+2010";
    String sifre = "1234";
    Connection con1, con2 = null;
    Statement st1, st2 = null;
    ResultSet rs1, rs2 = null;

    List<Sabitler> sabitlerListesi = new ArrayList<Sabitler>();

    @Override
    public List<Sabitler> sabitlerListesi() throws ClassNotFoundException,
	    SQLException {
	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	con1 = DriverManager.getConnection(url, kullaniciad, sifre);
	st1 = con1.createStatement();

	String vericek = "select KazaID,IlceIsmi from Sabitler";

	rs1 = st1.executeQuery(vericek);
	sabitlerListesi.clear();

	while (rs1.next()) {
	    Sabitler sabit= new Sabitler();
	    System.out.println(" ID=" + rs1.getLong("KazaID") + " " + "Isim="
		    + rs1.getString("IlceIsmi"));

	    sabit.setKazaID(rs1.getLong(1));
	    sabit.setIlceIsmi(rs1.getString(2));
	   

	    sabitlerListesi.add(sabit);

	}
	return sabitlerListesi;

    }
}
