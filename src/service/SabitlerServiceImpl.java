package service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.SabitlerDAO;
import forms.Sabitler;

@Service
public class SabitlerServiceImpl implements SabitlerService {
    
    @Autowired
    SabitlerDAO sabitlerDAO;

    @Override
    public List<Sabitler> sabitlerListesi() throws ClassNotFoundException,
	    SQLException {

	return sabitlerDAO.sabitlerListesi();
    }

}
