package service;

import java.util.List;

import forms.Sabittips;

public interface SabitTipsService {
	public List<Sabittips> tipGetir();

	public List<Sabittips> altTipGetir(Long Id, Boolean durum);

	public void tipsil(Long id);

	public void ekle(Sabittips sabittips);

	public Sabittips tipsGetir(Long id);

	public void kaydet(Object nesne);

	public Integer calistir(String sorgu);

	public Object getir(Long id, @SuppressWarnings("rawtypes") Class sinif);

	public boolean isimVarMi(String isim);

}
