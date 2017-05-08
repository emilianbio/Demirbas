package dao;

import java.sql.SQLException;
import java.util.List;

import forms.Sabitler;

public interface SabitlerDAO {
    public List<Sabitler> sabitlerListesi() throws ClassNotFoundException, SQLException;
    
}
