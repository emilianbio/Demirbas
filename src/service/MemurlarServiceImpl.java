package service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.MemurlarDAO;
import forms.Memurlar;

@Service
public class MemurlarServiceImpl implements MemurlarService {
    @Autowired
    MemurlarDAO memurlarDAO;

    @Override
    public List<Memurlar> personelListesi() throws ClassNotFoundException,
	    SQLException {

	return memurlarDAO.personelListesi();
    }

    @Override
    public List<Memurlar> üzerindeZimmetOlanPersonelGetir(Long MemurID)
	    throws ClassNotFoundException, SQLException {

	return memurlarDAO.üzerindeZimmetOlanPersonelGetir(MemurID);
    }

    @Override
    @Transactional
    public List<Memurlar> memurlarListesi() {

	return memurlarDAO.memurlarListesi();
    }

}
