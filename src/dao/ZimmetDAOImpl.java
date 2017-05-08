package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import forms.Zimmet;


@Repository
public class ZimmetDAOImpl implements ZimmetDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void zimmetEkle(Zimmet zimmet) {
	sessionFactory.getCurrentSession().saveOrUpdate(zimmet);
    }

    
    @Override
    public List<Zimmet> zimmetListesi(String zm) {
	Criteria criteriaDemirbas = sessionFactory.getCurrentSession()
		.createCriteria(Zimmet.class);
	criteriaDemirbas.addOrder(Order.desc("eklemezamani"));
	@SuppressWarnings("unchecked")
	List<Zimmet> sonuc = criteriaDemirbas.list();
	return sonuc;
    }

    @Override
    public Zimmet zimmetGetir(Long id) {
	@SuppressWarnings("unused")
	org.hibernate.Session session = sessionFactory.openSession();
	Zimmet zimmett = (Zimmet) sessionFactory.getCurrentSession()
		.load(Zimmet.class, id);

	zimmett.getId();
	return zimmett;
    }

    @Override
    public void zimmetSil(Long id) {

	sessionFactory.getCurrentSession().delete(zimmetGetir(id));
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Zimmet> zimmetDemirbasNoGetir(String demirbasno) {
	Session session = sessionFactory.getCurrentSession();

	Query sorgu = session
		.createQuery("select z from Zimmet z where z.demirbasno like :x");
	sorgu.setParameter("x", demirbasno);
	return sorgu.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Zimmet> zimmetPersonelGetir(Long memurid) {

	// List<Zimmet> zimmetPersonelGetir = new ArrayList<Zimmet>();
	// Class.forName("org.postgresql.Driver");
	// Connection conn = DriverManager.getConnection(
	// "jdbc:postgresql://localhost:5432/demirbasdb", "postgres",
	// "1234");
	// String sorgu = "select  * from Zimmet where id=:x ";
	// PreparedStatement ps = conn.prepareStatement(sorgu);
	// ps.setString(1, "x");
	// ResultSet rs0 = ps.executeQuery();
	// while (rs0.next()){
	//
	// Zimmet zimmet = new Zimmet();
	//
	// zimmet.setAlttip(rs0.getsa ("alttipid"));
	//
	//
	// };

	Session session = sessionFactory.getCurrentSession();
	Query sorgu = session.createQuery("from Zimmet where id = :x ");
	sorgu.setParameter("x", memurid); // sorgu.setParameter("x", "%" + id + "%");
	return sorgu.list();

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Zimmet> personelIdGetir(Long memurid) {
	Session session = sessionFactory.getCurrentSession();
	Query sorgu = session
		.createQuery("select distinct memurid FROM Zimmet order by memurid asc "); // ("SELECT distinct memurid.id FROM Zimmet")
	return sorgu.list();

    }

//    @Override
//    public Zimmet resultsetIdGetir(Long id, HttpServletRequest request,
//	    HttpServletResponse response) throws SQLException, ClassNotFoundException {
//
//	ArrayList<Zimmet> zimmetListesi = new ArrayList<Zimmet>();
//
//	Class.forName("org.postgresql.Driver");
//	Connection conn = DriverManager.getConnection(
//		"jdbc:postgresql://localhost:5432/demirbasdb", "postgres",
//		"1234");
//
//	Statement statement = conn.createStatement();
//
//	// String sorgu =
//	// "select  z.id,s1.isim ,s2.isim ,s3.isim,z.demirbasno,z.baslamatarihi,z.bitistarihi,z.eklemezamani, k.isim ,z.memurid,z.kazaid ,z.unvan,u.isim from zimmet z full join unvanlar u on z.unvan=u.id inner join sabittips s1 on z.alttipid = s1.id inner join sabittips s2 on z.markaid = s2.id  inner join sabittips s3 on z.modelid = s3.id inner join kullanici k on z.kaydeden = k.id where z.id= "+
//	// rs0."order by eklemezamani desc ";
//	String memurid = request.getParameter("memurid");
//	ResultSet rs0 = statement
//		.executeQuery("select  z.id,s1.isim ,s2.isim ,s3.isim,z.demirbasno,z.baslamatarihi,z.bitistarihi,z.eklemezamani, k.isim ,z.memurid,z.kazaid ,z.unvan,u.isim,z.serino from zimmet z full join unvanlar u on z.unvan=u.id inner join sabittips s1 on z.alttipid = s1.id inner join sabittips s2 on z.markaid = s2.id  inner join sabittips s3 on z.modelid = s3.id inner join kullanici k on z.kaydeden = k.id where z.memurid= "
//			+ memurid + "order by eklemezamani desc ");
//
//	// ResultSet rs2 = statement.executeQuery(sorgu);
//	String zimmetMemurid = request.getParameter("memurid");
//	int zimmetKazaid;
//	zimmetListesi.clear();
//	int siraNo = 1;
//	while (rs0.next()) {
//	    // zimmetMemurid = rs0.getInt("memurid");
//	    zimmetKazaid = rs0.getInt("kazaid");
//
//	    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//	    String url = "jdbc:sqlserver://10.1.0.10:1433;databaseName=MemurSQL";
//	    // String url =
//	    // "jdbc:sqlserver://localhost:1433;databaseName=java_db";
//	    String kullaniciad = "sa";
//	    String sifre = "saricam%tarim+2010";
//	    // String sifre = "1234";
//	    Connection con = null;
//	    Statement st = null;
//	    ResultSet rs2 = null;
//	    con = DriverManager.getConnection(url, kullaniciad, sifre);
//	    st = con.createStatement();
//
//	    String vericek = "select *  from Memurlar where MemurID =   "
//		    + " ' " + zimmetMemurid + " ' " + "order by Isim ASC";
//
//	    rs2 = st.executeQuery(vericek);
//
//	    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//	    String url2 = "jdbc:sqlserver://10.1.0.10:1433;databaseName=MemurSQL";
//	    // String url2 =
//	    // "jdbc:sqlserver://localhost:1433;databaseName=java_db";
//	    String kullaniciad2 = "sa";
//	    String sifre2 = "saricam%tarim+2010";
//	    // String sifre2 = "1234";
//	    Connection con2 = null;
//	    Statement st2 = null;
//	    ResultSet rs3 = null;
//	    con2 = DriverManager.getConnection(url2, kullaniciad2, sifre2);
//	    st2 = con2.createStatement();
//
//	    String vericek2 = "select *  from Sabitler where KazaID =   "
//		    + " ' " + zimmetKazaid + " ' ";
//
//	    rs3 = st2.executeQuery(vericek2);
//
//	}
//	return null;
//    }

     @Override
     public Zimmet resultsetIdGetir(Long memurid) throws SQLException {
    
     Connection conn = null;
     ResultSet rs = null;
     conn = DriverManager.getConnection(
     "jdbc:postgresql://localhost:5432/demirbasdb", "postgres",
     "1234");
    
     // Class.forName("org.postgresql.Driver");
    
     String sorgu = "select * from zimmet where memurid= ?";
     PreparedStatement ps = conn.prepareStatement(sorgu);
     ps.setLong(1, memurid);
     rs = ps.executeQuery();
     Zimmet zimmet = new Zimmet();
    
     try {
     if (rs.next()) {
    
     zimmet.setId(rs.getLong("id"));
     zimmet.setDemirbasno(rs.getString("demirbasno"));
     zimmet.setKazaid(rs.getLong("kazaid"));
     zimmet.setMemurid(rs.getLong("memurid"));
     zimmet.setUnvan(rs.getLong("unvan"));
     zimmet.setBaslamatarihi(rs.getDate("baslamatarihi"));
     }
     } catch (Exception ex) {
     System.err.println("RS:" + ex.getMessage());
     }
    
     return zimmet;
    
     }

}
