package forms;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "unvan", schema = "public")
public class Unvanlar {

    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "isim")
    private String isim;
    @Column(name = "katid")
    private Long katid;

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

    public Long getKatid() {
        return katid;
    }

    public void setKatid(Long katid) {
        this.katid = katid;
    }
    
    

}