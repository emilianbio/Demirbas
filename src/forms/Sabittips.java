package forms;

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

@Entity
@Table(name = "sabittips", schema = "public")
// Veri tabanındaki tablo ismi sabitler mi evet sequence ismi on
public class Sabittips implements java.io.Serializable {
	private static final long serialVersionUID = 4418029727139184238L;
	@Id
	@Column(name = "id", nullable = false)
	@SequenceGenerator(name = "tabloSequnce", sequenceName = "sabittips_id_seq")
	@GeneratedValue(generator = "tabloSequnce")
	private long id;

	@Column(name = "isim")
	private String isim;

	@Column(name = "durum")
	private Boolean durum;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "eklemezamani")
	private Date eklemezamani;

	@ManyToOne
	@JoinColumn(name = "katid")
	private Sabittips tip;

	public Sabittips getTip() {
		return tip;
	}

	public void setTip(Sabittips tip) {
		this.tip = tip;
	}

	public Sabittips() {

	}

	public Sabittips(long katId) {
		this.id = katId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIsim() {
		return isim;
	}

	public void setIsim(String isim) {
		this.isim = isim;
	}

	public Date getEklemezamani() {
		return eklemezamani;
	}

	public void setEklemezamani(Date eklemezamani) {
		this.eklemezamani = eklemezamani;
	}

	public Boolean getDurum() {
		return durum;
	}

	public void setDurum(Boolean durum) {
		this.durum = durum;
	}

	@OneToOne
	@JoinColumn(name = "kaydeden")
	private Kullanici memurs;

	public Kullanici getMemurs() {
		return memurs;
	}

	public void setMemurs(Kullanici memurs) {
		this.memurs = memurs;
	}

}