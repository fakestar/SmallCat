package creare.sc.models.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;


@JsonTypeName("Translate")
public class Translate implements Command {

	public final String id;
	public final String text;

	@JsonCreator
	public Translate(@JsonProperty("id") String id, @JsonProperty("text") String text) {
		this.id = id;
		this.text = text;
	}

	@Override
	public void exec() {
		// TODO 自動生成されたメソッド・スタブ

	}
}