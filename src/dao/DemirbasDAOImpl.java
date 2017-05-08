package dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import forms.Demirbas;

@Repository
public class DemirbasDAOImpl implements DemirbasDAO {
    @Autowired
    SessionFactory sessionFactory;

    String url = "jdbc:sqlserver://localhost:1433;databaseName=java_db";
    String kullaniciad = "sa";
    // String sifre = "saricam%tarim+2010";
    String sifre = "1234";

    @Override
    public void demirbasEkle(Demirbas demirbas) {
	sessionFactory.getCurrentSession().saveOrUpdate(demirbas);
    }

    @Override
    public List<Demirbas> demirbasListesi(Integer sayfano) {
	Criteria criteriaDemirbas = sessionFactory.getCurrentSession()
		.createCriteria(Demirbas.class);
	criteriaDemirbas.setFirstResult((sayfano - 1)
		* araclar.Genel.getKayitSayisi());
	criteriaDemirbas.setMaxResults(araclar.Genel.getKayitSayisi());
	criteriaDemirbas.addOrder(Order.desc("eklemezamani"));
	@SuppressWarnings("unchecked")
	List<Demirbas> sonuc = criteriaDemirbas.list();
	return sonuc;
    }

    @Override
    public Demirbas demirbasGetir(Long id) {
	@SuppressWarnings("unused")
	org.hibernate.Session session = sessionFactory.openSession();
	Demirbas demirbass = (Demirbas) sessionFactory.getCurrentSession()
		.load(Demirbas.class, id);

	demirbass.getDemirbasID();
	return demirbass;
    }

    @Override
    public void demirbassil(Long id) {
	sessionFactory.getCurrentSession().delete(demirbasGetir(id));
    }

    @Override
    public Boolean demirbasnoVarMi(String demirbasno) {
	Criteria criteriademirbasnoVarMi = sessionFactory.getCurrentSession()
		.createCriteria(Demirbas.class);
	criteriademirbasnoVarMi.add(Restrictions.eq("demirbasno", demirbasno));
	@SuppressWarnings("rawtypes")
	List sonucList = criteriademirbasnoVarMi.list();
	return (sonucList != null && sonucList.size() > 0);
    }

    @Override
    public Long kayitSayisi() {
	Criteria criteriaDemirbas = sessionFactory.getCurrentSession()
		.createCriteria(Demirbas.class);
	criteriaDemirbas.setProjection(Projections.rowCount());
	@SuppressWarnings("rawtypes")
	List kayitlar = criteriaDemirbas.list();
	Long kayitsayisi = 0L;
	if (kayitlar != null) {
	    kayitsayisi = (Long) kayitlar.get(0);
	}
	return kayitsayisi;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Demirbas> demirbasNoListesi(Long id) {

	Session session = sessionFactory.getCurrentSession();
	return session.createCriteria(Demirbas.class).addOrder(Order.asc("id"))
		.add(Restrictions.eq("demirbasno", id))
		.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();

	// Criteria criteriaDemirbas = sessionFactory.getCurrentSession()
	// .createCriteria(Demirbas.class);
	// criteriaDemirbas.addOrder(Order.asc("id"));
	// List<Demirbas> sonuc = criteriaDemirbas.list();
	// return sonuc;
    }

    @Override
    public Long updateDemirbas(Demirbas demirbas) {
	Session session = sessionFactory.openSession();
	Transaction tx = session.beginTransaction();
	session.saveOrUpdate(demirbas);
	tx.commit();
	Serializable id = session.getIdentifier(demirbas);
	session.close();
	return (Long) id;
    }

    
}