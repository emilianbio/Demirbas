package service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.KullaniciDAO;
import forms.Kullanici;
import forms.Memurlar;

@Service
public class KullaniciServiceImpl implements KullaniciService {
	@Autowired
	KullaniciDAO kullaniciDAO;

	@Override
	@Transactional
	public Kullanici kullaniciGetir(Kullanici kullanici) {
		return kullaniciDAO.kullaniciGetir(kullanici);

	}

	@Override
	public Memurlar kullaniciGiris(Memurlar kullanici,
		HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {
	    
	    return kullaniciDAO.kullaniciGiris(kullanici, request, response);
	}

	

}