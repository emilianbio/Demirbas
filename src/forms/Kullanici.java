package forms;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "kullanici", schema = "public")
// Veri tabanındaki tablo ismi sabitler mi evet sequence ismi on
public class Kullanici implements java.io.Serializable {
	private static final long serialVersionUID = 4418029727139184238L;
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "tabloSequnce", sequenceName = "kullanici_id_seq")
	@GeneratedValue(generator = "tabloSequnce")
	private Long id;

	@Column(name = "isim")
	private String isim;

	@Column(name = "sifre")
	private String sifre;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsim() {
		return isim;
	}

	public void setIsim(String isim) {
		this.isim = isim;
	}

	public String getSifre() {
		return sifre;
	}

	public void setSifre(String sifre) {
		this.sifre = sifre;
	}

	@OneToOne
	@PrimaryKeyJoinColumn
	private Kullanici memurs;

	public Kullanici getMemurs() {
		return memurs;
	}

	public void setMemurs(Kullanici memurs) {
		this.memurs = memurs;
	}
}