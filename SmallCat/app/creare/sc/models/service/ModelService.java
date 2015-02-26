package creare.sc.models.service;

import java.util.List;

import play.db.ebean.Model;
import play.libs.F.Option;

public interface ModelService<T extends Model> {

	public Option<T> findById(Long id);

	public Option<List<T>> findAll(String field, String value);

	public Option<T> create(T model);

	public boolean update(T model, Long id);

	public boolean delete(Long id);

	public boolean deleteAll(List<Long> idList);
}
