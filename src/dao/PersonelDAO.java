package dao;

import java.util.List;

import forms.Personel;

public interface PersonelDAO {
	public List<Personel> personelListesi();
	public List<Personel> personelGetir(Long katid);
}
