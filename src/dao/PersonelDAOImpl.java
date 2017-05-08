package dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import forms.Personel;

@Repository
public class PersonelDAOImpl implements PersonelDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public List<Personel> personelListesi() {
	Criteria criteriaBirimler = sessionFactory.getCurrentSession()
		.createCriteria(Personel.class);
	// criteriaDemirbas.setFirstResult((int)(sayfano-1)*araclar.Genel.getKayitSayisi());
	// criteriaDemirbas.setMaxResults(araclar.Genel.getKayitSayisi());
	criteriaBirimler.addOrder(Order.asc("isim"));
	List<Personel> sonuc = criteriaBirimler.list();
	return sonuc;
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public List<Personel> personelGetir(Long katid) {

	Criteria criteriaBirimler = sessionFactory.getCurrentSession()
		.createCriteria(Personel.class);
	List<Personel> sonuc = criteriaBirimler.list();

	return sonuc;
    }

}