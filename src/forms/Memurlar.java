package forms;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Memurlar", schema = "dbo")
public class Memurlar {
    
    @Id
    @Column(name = "MemurID")
    public long MemurID;
    
    @Column(name = "TCNo")
    public String TCNo;
    
    @Column(name = "Isim")
    public String Isim;
    
    @Column(name = "kaza_id")
    public int kaza_id;
    
    @Column(name = "Vazifesi")
    public String Vazifesi;
    
    @Column(name = "sifre")
    public String sifre;
    
    @Column(name = "unvan")
    public String unvan;

    public String getTCNo() {
	return TCNo;
    }

    public void setTCNo(String tCNo) {
	TCNo = tCNo;
    }

    public String getIsim() {
	return Isim;
    }

    public void setIsim(String isim) {
	this.Isim = isim;
    }

    public int getKaza_id() {
	return kaza_id;
    }

    public void setKaza_id(int kaza_id) {
	this.kaza_id = kaza_id;
    }

    public long getMemurID() {
	return MemurID;
    }

    public void setMemurID(long memurID) {
	MemurID = memurID;
    }

    public String getVazifesi() {
	return Vazifesi;
    }

    public void setVazifesi(String vazifesi) {
	Vazifesi = vazifesi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getUnvan() {
        return unvan;
    }

    public void setUnvan(String unvan) {
        this.unvan = unvan;
    }
    
    
    
    

}
