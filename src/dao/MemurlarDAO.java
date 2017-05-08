package dao;

import java.sql.SQLException;
import java.util.List;

import forms.Memurlar;

public interface MemurlarDAO {
    
    public List<Memurlar>  personelListesi() throws ClassNotFoundException, SQLException;
    public List<Memurlar> üzerindeZimmetOlanPersonelGetir(Long MemurID) throws ClassNotFoundException, SQLException;
    
    public List<Memurlar>  memurlarListesi();
  
}
