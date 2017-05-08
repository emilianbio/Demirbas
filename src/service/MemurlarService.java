package service;

import java.sql.SQLException;
import java.util.List;

import forms.Memurlar;

public interface MemurlarService {

   public List<Memurlar> personelListesi() throws ClassNotFoundException, SQLException;
   public List<Memurlar> üzerindeZimmetOlanPersonelGetir(Long MemurID) throws ClassNotFoundException, SQLException;
   public List<Memurlar> memurlarListesi();
}
