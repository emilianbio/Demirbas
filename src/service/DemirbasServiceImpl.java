package service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.DemirbasDAO;
import forms.Demirbas;

@Service
public class DemirbasServiceImpl implements DemirbasService {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	DemirbasDAO demirBasDAO;

	@Override
	@Transactional
	public void demirbasEkle(Demirbas demirbas) {
		demirBasDAO.demirbasEkle(demirbas);
	}

	@Override
	@Transactional
	public List<Demirbas> demirbasListesi(Integer sayfaNo) {
		// TODO Auto-generated method stub
		return demirBasDAO.demirbasListesi(sayfaNo);
	}

	@Override
	@Transactional
	public Demirbas demirbasGetir(Long id) {
		return demirBasDAO.demirbasGetir(id);
	}

	@Override
	@Transactional
	public void demirbassil(Long id) {
		demirBasDAO.demirbassil(id);
	}

	@Override
	@Transactional
	public Boolean demirbasnoVarMi(String demirbasno) {
		return demirBasDAO.demirbasnoVarMi(demirbasno);
	}

	@Transactional
	@Override
	public Long kayitSayisi() {

		return demirBasDAO.kayitSayisi();
	}

	@Transactional
	@Override
	public List<Demirbas> demirbasNoListesi(Long id) {

		return demirBasDAO.demirbasNoListesi(id);
	}

	@Override
	public Long updateDemirbas(Demirbas demirbas) {
		return demirBasDAO.updateDemirbas(demirbas);
	}

	

}
