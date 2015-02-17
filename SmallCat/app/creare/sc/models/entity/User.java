package creare.sc.models.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import play.db.ebean.Model;

import com.avaje.ebean.annotation.CreatedTimestamp;

@Entity
public class User extends Model{

	@Id
	public Long id;

	@NotNull
	public String name;

	@CreatedTimestamp
	public Date createDate;

	@Version
	public Date updateDate;

	public String toString() {
		return "Parent [id=" + id + ", name=" + name + ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}

}
