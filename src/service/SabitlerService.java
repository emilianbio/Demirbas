package service;

import java.sql.SQLException;
import java.util.List;

import forms.Sabitler;

public interface SabitlerService {

   public List<Sabitler> sabitlerListesi() throws ClassNotFoundException, SQLException;

}
