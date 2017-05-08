package service;

import java.sql.SQLException;
import java.util.List;

import forms.Zimmet;

public interface ZimmetService {
	public List<Zimmet> zimmetListesi(String zm);	

	public void zimmetEkle(Zimmet zimmet);

	public Zimmet zimmetGetir(Long id);

	public void zimmetSil(Long id);

	public List<Zimmet> zimmetDemirbasNoGetir(String demirbasno);
	
	public List<Zimmet> personelIdGetir(Long memurid);
	
	public List<Zimmet> zimmetPersonelGetir(Long id);
	
	public Zimmet resultsetIdGetir(Long id) throws ClassNotFoundException, SQLException;
	
}
