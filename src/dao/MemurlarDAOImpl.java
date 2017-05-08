package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import forms.Memurlar;

@Repository
public class MemurlarDAOImpl implements MemurlarDAO {
    @Autowired
    private SessionFactory sessionFactory;

    // String url = "jdbc:sqlserver://10.1.0.10:1433;databaseName=MemurSQL";
    String url = "jdbc:sqlserver://localhost:1433;databaseName=java_db";
    String kullaniciad = "sa";
    // String sifre = "saricam%tarim+2010";
    String sifre = "1234";
    Connection con1, con2 = null;
    Statement st1, st2 = null;
    ResultSet rs1, rs2 = null;

    List<Memurlar> personelListesi = new ArrayList<Memurlar>();
    List<Memurlar> üzerindeZimmetOlanPersonel = new ArrayList<Memurlar>();

    @Override
    public List<Memurlar> personelListesi() throws ClassNotFoundException,
	    SQLException {

	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	con1 = DriverManager.getConnection(url, kullaniciad, sifre);
	st1 = con1.createStatement();

	String vericek = "select MemurID,Isim,TCNo,kaza_id from Memurlar order by Isim ASC";

	rs1 = st1.executeQuery(vericek);
	personelListesi.clear();

	while (rs1.next()) {
	    Memurlar memur = new Memurlar();
	    System.out.println(" ID=" + rs1.getLong("MemurID") + " " + "Isim="
		    + rs1.getString("Isim"));

	    memur.setMemurID(rs1.getInt("MemurID"));
	    memur.setIsim(rs1.getString("Isim"));
	    memur.setTCNo(rs1.getString("TCNo"));
	    memur.setKaza_id(rs1.getInt("kaza_id"));
	    // memur.setVazifesi(rs1.getString("Vazifesi"));

	    personelListesi.add(memur);

	}
	return personelListesi;

    }

    @Override
    public List<Memurlar> üzerindeZimmetOlanPersonelGetir(Long memurid)
	    throws ClassNotFoundException, SQLException {

	Class.forName("org.postgresql.Driver");
	con2 = DriverManager.getConnection(
		"jdbc:postgresql://localhost:5432/demirbasdb", "postgres",
		"1234");
	String sorgu = "select  * from Zimmet where memurid= '"+memurid+"'";
	st2 = con2.createStatement();
	rs2 = st2.executeQuery(sorgu);

	int zimmetMemurID = 0;
	üzerindeZimmetOlanPersonel.clear();
	while (rs2.next()) {

	    zimmetMemurID = rs2.getInt(2);

	    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	    con1 = DriverManager.getConnection(url, kullaniciad, sifre);
	    st1 = con1.createStatement();

	    String vericek = "select *  from Memurlar where MemurID =   "
		    + " ' " + zimmetMemurID + " ' " + "order by Isim ASC";

	    rs1 = st1.executeQuery(vericek);

	    while (rs1.next()) {

		System.out.println("");

		Memurlar memur = new Memurlar();
		memur.setMemurID(rs1.getInt(1));
		memur.setIsim(rs1.getString(2));
		memur.setTCNo(rs1.getString(3));
		memur.setUnvan(rs1.getString(5));
		memur.setVazifesi(rs1.getString(8));
		// memur.setIsim(rs1.getString(6));

		// zimmetListesi.clear();
		üzerindeZimmetOlanPersonel.add(memur);

	    }
	    ;rs1.close();
	    
	}rs2.close();
	

	return üzerindeZimmetOlanPersonel;

    }

    @Override
    public List<Memurlar> memurlarListesi() {
	Criteria criteriaMemur = sessionFactory.getCurrentSession()
		.createCriteria(Memurlar.class);
	criteriaMemur.addOrder(Order.desc("MemurID"));
	@SuppressWarnings("unchecked")
	List<Memurlar> sonuc = criteriaMemur.list();
	return sonuc;

    }
}
