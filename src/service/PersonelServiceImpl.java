package service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.PersonelDAO;
import forms.Personel;

@Service
public class PersonelServiceImpl implements PersonelService {
	@Autowired
	PersonelDAO personelDAO;

	@Transactional
	@Override
	public List<Personel> personelListesi() {

		return personelDAO.personelListesi();
	}

	@Override
	public List<Personel> personelGetir(Long katid) {
		// TODO Auto-generated method stub
		return personelDAO.personelGetir(katid);
	}

}
