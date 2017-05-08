package service;

import java.util.List;

import forms.Personel;

public interface PersonelService {
	public List<Personel> personelListesi();
	public List<Personel> personelGetir(Long katid);
}