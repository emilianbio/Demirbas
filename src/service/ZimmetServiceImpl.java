package service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.ZimmetDAO;
import forms.Zimmet;

@Service
public class ZimmetServiceImpl implements ZimmetService {
    @Autowired
    ZimmetDAO zimmetDAO;

    @Override
    @Transactional
    public void zimmetEkle(Zimmet zimmet) {
	zimmetDAO.zimmetEkle(zimmet);
    }

    @Transactional
    @Override
    public List<Zimmet> zimmetListesi(String zm) {
	return zimmetDAO.zimmetListesi(zm);
    }

    @Transactional
    @Override
    public Zimmet zimmetGetir(Long id) {

	return zimmetDAO.zimmetGetir(id);
    }

    @Transactional
    @Override
    public void zimmetSil(Long id) {
	zimmetDAO.zimmetSil(id);
    }

    @Transactional
    @Override
    public List<Zimmet> zimmetDemirbasNoGetir(String demirbasno) {

	return zimmetDAO.zimmetDemirbasNoGetir(demirbasno);
    }
    @Transactional
    @Override
    public List<Zimmet> zimmetPersonelGetir(Long memurid) {
	
	return zimmetDAO.zimmetPersonelGetir(memurid);
    }
    
    @Transactional
    @Override
    public List<Zimmet> personelIdGetir(Long memurid) {
	
	return zimmetDAO.personelIdGetir(memurid);
    }

    @Transactional
    @Override
    public Zimmet resultsetIdGetir(Long id) throws ClassNotFoundException, SQLException {
	// TODO Auto-generated method stub
	return zimmetDAO.resultsetIdGetir(id);
    }

   
}
