package dao;

import java.util.List;

import forms.Demirbas;

public interface DemirbasDAO {

	public void demirbasEkle(Demirbas demirbas);

	public List<Demirbas> demirbasListesi(Integer sayfaNo);

	public Long kayitSayisi();

	public Demirbas demirbasGetir(Long id);

	public void demirbassil(Long id);

	public Boolean demirbasnoVarMi(String demirbasno);

	public List<Demirbas> demirbasNoListesi(Long id);

	public Long updateDemirbas(Demirbas demirbas);



	

}