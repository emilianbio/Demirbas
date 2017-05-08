package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import forms.Sabittips;

@Repository
public class SabitTipsDAOImpl implements SabitTipsDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Sabittips> tipGetir() {
		Criteria criteriaSabittips = sessionFactory.getCurrentSession()
				.createCriteria(Sabittips.class);
		// //System.out.println("*******************************************1");
		criteriaSabittips.addOrder(Order.asc("id"));
		criteriaSabittips.add(Restrictions.isNull("tip"));
		@SuppressWarnings("unchecked")
		List<Sabittips> sonuc = criteriaSabittips.list();
		return sonuc;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sabittips> altTipGetir(Long Id, Boolean durum) {
		List<Sabittips> sonuc = null;
		if (Id == 0)
			sonuc = tipGetir();
		else {
			Criteria criteriaSabittips = sessionFactory.getCurrentSession()
					.createCriteria(Sabittips.class);
			// //System.out.println("*******************************************1");

			criteriaSabittips.add(Restrictions.eq("tip.id", Id));
			criteriaSabittips.add(Restrictions.eq("durum", durum));
			sonuc = criteriaSabittips.list();
			// System.out.println("*******************************************2");
			// System.out.println("************"+sonuc.toArray().length);
		}
		return sonuc;
	}

	@Override
	public void ekle(Sabittips sabittips) {
		sessionFactory.getCurrentSession().saveOrUpdate(sabittips);
	}

	@Override
	public Sabittips tipsGetir(Long id) {
		// System.out.println("******************************1");
		Sabittips tips = (Sabittips) sessionFactory.getCurrentSession().load(
				Sabittips.class, id);
		// System.out.println("******************************"+
		tips.getId();
		return tips;
	}

	@Override
	public void tipsil(Long id) {
		sessionFactory.getCurrentSession().delete(tipsGetir(id));
	}

	@Override
	public void kaydet(Object nesne) {
		sessionFactory.getCurrentSession().saveOrUpdate(nesne);
	}

	@Override
	public Integer calistir(String sorgu) {
		return sessionFactory.getCurrentSession().createQuery(sorgu)
				.executeUpdate();
	}

	@Override
	public Object getir(Long id, @SuppressWarnings("rawtypes") Class sinif) {
		// load yerine get kullanildi getCurrentSession yeri be
		Object nesne = sessionFactory.getCurrentSession().get(sinif, id);
		return nesne;
	}

	@Override
	public Boolean isimVarMi(String isim) {
		Criteria criteriaSabittips = sessionFactory.getCurrentSession()
				.createCriteria(Sabittips.class);
		criteriaSabittips.add(Restrictions.eq("isim", isim));
		@SuppressWarnings("rawtypes")
		List sonucList = criteriaSabittips.list();
		return (sonucList != null && sonucList.size() > 0);
	}
}
