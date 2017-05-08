package dao;

import java.util.List;

import forms.Sabittips;

public interface SabitTipsDAO {
	public List<Sabittips> tipGetir();

	public List<Sabittips> altTipGetir(Long Id, Boolean durum);

	public void tipsil(Long id);

	public void ekle(Sabittips sabittips);

	public Sabittips tipsGetir(Long id);

	public void kaydet(Object nesne);

	public Integer calistir(String sorgu);

	public Object getir(Long id, @SuppressWarnings("rawtypes") Class sinif);

	// public Boolean katidVarMi(Long katId);
	public Boolean isimVarMi(String isim);
}
