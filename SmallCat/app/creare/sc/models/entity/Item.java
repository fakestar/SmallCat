package creare.sc.models.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import play.data.format.Formats;
import play.db.ebean.Model;

import com.avaje.ebean.annotation.CreatedTimestamp;

@Entity

@Table(name = "m_items")
public class Item extends Model{

	@Id
	public Long itemId;

	@NotNull
	public String itemName;

	@CreatedTimestamp
	@Formats.DateTime(pattern="yyyy/MM/dd")
	public Date createDate;

	@Version
	@Formats.DateTime(pattern="yyyy/MM/dd")
	public Date updateDate;

	public String toString() {
		return "Parent [id=" + itemId + ", name=" + itemName + ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}

}
