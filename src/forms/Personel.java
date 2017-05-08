package forms;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "personel", schema = "public")
public class Personel implements java.io.Serializable {
	private static final long serialVersionUID = 4418029727139184238L;
	@Id
	@Column(name = "id")
	private long id;

	@Column(name = "isim")
	private String isim;
	
	@Column(name = "kazaid")
	private long kazaid;

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

	
	public long getKazaid() {
	    return kazaid;
	}

	
	public void setKazaid(long kazaid) {
	    this.kazaid = kazaid;
	}
	

}
