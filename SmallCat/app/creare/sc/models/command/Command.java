package creare.sc.models.command;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(use=Id.NAME)
@JsonSubTypes({
    @Type(value=Talk.class),
    @Type(value=Translate.class)
})

public interface Command {

	public void exec();
}
