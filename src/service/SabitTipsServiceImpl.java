package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.SabitTipsDAO;
import forms.Sabittips;

@Service
public class SabitTipsServiceImpl implements SabitTipsService {
	@Autowired
	SabitTipsDAO sabitTipsDAO;

	@Override
	@Transactional
	public List<Sabittips> tipGetir() {
		// TODO Auto-generated method stub
		return sabitTipsDAO.tipGetir();
	}

	@Override
	@Transactional
	public List<Sabittips> altTipGetir(Long Id, Boolean durum) {
		// TODO Auto-generated method stub
		return sabitTipsDAO.altTipGetir(Id, durum);
	}

	@Override
	@Transactional
	public void ekle(Sabittips sabittips) {
		sabitTipsDAO.ekle(sabittips);
	}

	@Override
	@Transactional
	public Sabittips tipsGetir(Long id) {
		// TODO Auto-generated method stub
		return sabitTipsDAO.tipsGetir(id);
	}

	@Override
	@Transactional
	public void tipsil(Long id) {
		sabitTipsDAO.tipsil(id);
	}

	@Override
	@Transactional
	public void kaydet(Object nesne) {
		sabitTipsDAO.kaydet(nesne);
	}

	@Override
	@Transactional
	public Integer calistir(String sorgu) {
		return sabitTipsDAO.calistir(sorgu);
	}

	@Override
	@Transactional
	public Object getir(Long id, @SuppressWarnings("rawtypes") Class sinif) {
		return sabitTipsDAO.getir(id, sinif);
	}

	// @Transactional
	// public boolean katidVarMi(Long katId) {
	// return sabitTipsDAO.katidVarMi( katId);
	// }

	@Override
	@Transactional
	public boolean isimVarMi(String isim) {
		return sabitTipsDAO.isimVarMi(isim);
	}

}
