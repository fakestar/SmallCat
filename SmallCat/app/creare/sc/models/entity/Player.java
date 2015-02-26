package creare.sc.models.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import play.data.format.Formats;
import play.db.ebean.Model;

import com.avaje.ebean.annotation.CreatedTimestamp;

@Entity

@Table(name = "t_players")

/**
 * DBデータとしてのEntity設定とレスポンスデータとしてのJSON設定は分けるべきな気がする。
 *
 * @author 0505177
 *
 */
public class Player extends Model {

	@Id
	private Long playerId;

	@NotNull
	private String playerName;

	private Long charId;

	@CreatedTimestamp
	@Formats.DateTime(pattern="yyyy/MM/dd")
	private Date createDate;

	@Version
	@Formats.DateTime(pattern="yyyy/MM/dd")
	private Date updateDate;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
		name="c_player_friends",
		joinColumns=@JoinColumn(name="player_id"),
		inverseJoinColumns=@JoinColumn(name="friend_id")
	)

	public List<Player> friends = new ArrayList<Player>();

	public Player(Long charId, String playerName) {
		this.charId = charId;
		this.playerName = playerName;
	}

	public Player(Long playerId, Long charId, String playerName, Date createDate, Date updateDate) {
		this.playerId = playerId;
		this.charId = charId;
		this.playerName = playerName;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public String toString() {
		return "Parent [id=" + playerId + ", name=" + playerName + ", charId=" + charId + ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}

}
