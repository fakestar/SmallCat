package creare.sc.models.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;


@JsonTypeName("Talk")
public class Talk implements Command {

	public final String id;
	public String text;

	@JsonCreator
	public Talk(@JsonProperty("id") String id, @JsonProperty("text") String text) {
		this.id = id;
		this.text = text;
	}

	@Override
	public void exec() {
		try {
			String token = Translate.getToken("19ee8fe9-2b9a-410a-a7df-c9bd5e5b7d4f", "BI1PDjlDdXhD4ETsKLfLkkdrj8trc5jlq3m9MuTez14=");
			System.out.println(token);

			Translate.setClientId("19ee8fe9-2b9a-410a-a7df-c9bd5e5b7d4f");
			Translate.setClientSecret("BI1PDjlDdXhD4ETsKLfLkkdrj8trc5jlq3m9MuTez14=");
			text = Translate.execute(text, Language.JAPANESE, Language.ENGLISH);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}
}
