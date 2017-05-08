package service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import forms.Kullanici;
import forms.Memurlar;

public interface KullaniciService {
	public Kullanici kullaniciGetir(Kullanici kullanici);
	public Memurlar kullaniciGiris(Memurlar kullanici,
		HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException;
	
}