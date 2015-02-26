package creare.sc.models.service;

import java.util.List;

import play.db.ebean.Model;
import play.libs.F.None;
import play.libs.F.Option;
import creare.sc.models.entity.Player;
import creare.sc.util.OptionUtil;

public class PlayerService implements ModelService<Player> {


	@Override
	public Option<Player> findById(Long id) {
		Option<Long> idOps = OptionUtil.apply(id);
		if(idOps.isDefined()) {

			Model.Finder<Long, Player> find = new Model.Finder<Long, Player>(Long.class, Player.class);
			return OptionUtil.apply(find.byId(id));
		}

		return new None<Player>();
	}

	@Override
	public Option<List<Player>> findAll(String field, String value) {
		Option<String> idOps = OptionUtil.apply(field);
		if(idOps.isDefined()) {

			Model.Finder<Long, Player> find = new Model.Finder<Long, Player>(Long.class, Player.class);
			System.out.println(find.where().eq("player_name","test2"));
			List<Player> list =  find.where().eq("player_name","test2").findList();
			return OptionUtil.apply(list);
		}

		return new None<List<Player>>();

	}

	@Override
	public Option<Player> create(Player user) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public boolean update(Player user, Long id) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean delete(Long id) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean deleteAll(List<Long> idList) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

}
