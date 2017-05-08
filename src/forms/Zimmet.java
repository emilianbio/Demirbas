package forms;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "zimmet", schema = "public")
public class Zimmet implements Serializable {
    private static final long serialVersionUID = 4418029727139184238L;
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "tabloSequnce", sequenceName = "zimmet_id_seq")
    @GeneratedValue(generator = "tabloSequnce")
    private long id;

    @ManyToOne
    @JoinColumn(name = "alttipid")
    private Sabittips alttip;

    @ManyToOne
    @JoinColumn(name = "markaid")
    private Sabittips marka;

    @ManyToOne
    @JoinColumn(name = "modelid")
    private Sabittips model;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "baslamatarihi")
    private Date baslamatarihi;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "bitistarihi")
    private Date bitistarihi;

    @Column(name = "demirbasno")
    private String demirbasno;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "eklemezamani")
    private Date eklemezamani;

    @Column(name = "kazaid")
    private long kazaid;

    @Column(name = "memurid")
    private long memurid;

    @OneToOne
    @JoinColumn(name = "kaydeden")
    private Kullanici memurs;

    @Column(name = "unvan")
    private Long unvan;
    
    @Column(name = "serino")
    private String serino;
    
    @Column(name = "aded")
    private Long aded;
    
    @Column(name = "demirbasid")
    private Long demirbasID;

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public Sabittips getAlttip() {
	return alttip;
    }

    public void setAlttip(Sabittips alttip) {
	this.alttip = alttip;
    }

    public Sabittips getMarka() {
	return marka;
    }

    public void setMarka(Sabittips marka) {
	this.marka = marka;
    }

    public Sabittips getModel() {
	return model;
    }

    public void setModel(Sabittips model) {
	this.model = model;
    }

    public Date getBaslamatarihi() {
	return baslamatarihi;
    }

    public void setBaslamatarihi(Date baslamatarihi) {
	this.baslamatarihi = baslamatarihi;
    }

    public Date getBitistarihi() {
	return bitistarihi;
    }

    public void setBitistarihi(Date bitistarihi) {
	this.bitistarihi = bitistarihi;
    }

    public String getDemirbasno() {
	return demirbasno;
    }

    public void setDemirbasno(String demirbasno) {
	this.demirbasno = demirbasno;
    }

    public long getKazaid() {
	return kazaid;
    }

    public void setKazaid(long kazaid) {
	this.kazaid = kazaid;
    }

    public Kullanici getMemurs() {
	return memurs;
    }

    public void setMemurs(Kullanici memurs) {
	this.memurs = memurs;
    }

    public Date getEklemezamani() {
	return eklemezamani;
    }

    public void setEklemezamani(Date eklemezamani) {
	this.eklemezamani = eklemezamani;
    }

    public Long getMemurid() {
	return memurid;
    }

    public void setMemurid(long memurid) {
	this.memurid = memurid;
    }

    public Long getUnvan() {
	return unvan;
    }

    public void setUnvan(Long unvan) {
	this.unvan = unvan;
	
	
    }

    public String getSerino() {
        return serino;
    }

    public void setSerino(String serino) {
        this.serino = serino;
    }

    public Long getAded() {
        return aded;
    }

    public void setAded(Long aded) {
        this.aded = aded;
    }

    public Long getDemirbasID() {
        return demirbasID;
    }

    public void setDemirbasID(Long demirbasID) {
        this.demirbasID = demirbasID;
    }
    
    

}