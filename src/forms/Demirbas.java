package forms;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "demirbas", schema = "public")
public class Demirbas implements java.io.Serializable {
    private static final long serialVersionUID = 4418029727139184238L;

    @Id
    @Column(name = "demirbasid")
    @SequenceGenerator(name = "tabloSequnce", sequenceName = "demirbas_demirbasid_seq")
    @GeneratedValue(generator = "tabloSequnce")
    private long demirbasid;

    @ManyToOne
    @JoinColumn(name = "tipid")
    private Sabittips tip;

    @ManyToOne
    @JoinColumn(name = "alttipid")
    private Sabittips alttip;

    @ManyToOne
    @JoinColumn(name = "markaid")
    private Sabittips marka;

    @ManyToOne
    @JoinColumn(name = "modelid")
    private Sabittips model;

    @Column(name = "aded")
    private long aded;

    // @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "baslamatarihi")
    private Date baslamatarihi;

    @Column(name = "bitistarihi")
    private Date bitistarihi;

    @Column(name = "demirbasno")
    private String demirbasno;

    @Column(name = "serino")
    private String serino;

    @Column(name = "durum")
    private Boolean durum;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "eklemezamani")
    private Date eklemezamani;

    @Column(name = "izahat")
    private String izahat;

    @Column(name = "kazaid")
    private Long kazaid;

    @Column(name = "memurid")
    private Long memurid;

    // @OneToOne
    // @JoinColumn(name = "kaydeden")
    // private Kullanici memurs;

    @Column(name = "kaydeden")
    private Long kaydeden;

    @Column(name = "tutar")
    private BigDecimal tutar;

    @Column(name = "unvan")
    private Long unvan;

    public long getDemirbasID() {
	return demirbasid;
    }

    public void setDemirbasID(long demirbasID) {
	this.demirbasid = demirbasID;
    }

    public Sabittips getTip() {
	return tip;
    }

    public void setTip(Sabittips tip) {
	this.tip = tip;
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

    public long getAded() {
	return aded;
    }

    public void setAded(long aded) {
	this.aded = aded;
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

    public Boolean getDurum() {
	return durum;
    }

    public void setDurum(Boolean durum) {
	this.durum = durum;
    }

    public Date getEklemezamani() {
	return eklemezamani;
    }

    public void setEklemezamani(Date eklemezamani) {
	this.eklemezamani = eklemezamani;
    }

    public String getIzahat() {
	return izahat;
    }

    public void setIzahat(String izahat) {
	this.izahat = izahat;
    }

    public Long getKazaid() {
	return kazaid;
    }

    public void setKazaid(Long kazaid) {
	this.kazaid = kazaid;
    }

    public Long getMemurid() {
	return memurid;
    }

    public void setMemurid(Long memurid) {
	this.memurid = memurid;
    }

    public Long getKaydeden() {
	return kaydeden;
    }

    public void setKaydeden(Long kaydeden) {
	this.kaydeden = kaydeden;
    }

    public BigDecimal getTutar() {
	return tutar;
    }

    public void setTutar(BigDecimal tutar) {
	this.tutar = tutar;
    }

    public String getSerino() {
	return serino;
    }

    public void setSerino(String serino) {
	this.serino = serino;
    }

    public Long getUnvan() {
	return unvan;
    }

    public void setUnvan(Long unvan) {
	this.unvan = unvan;
    }

}