package umlteacher.model.json.serializer;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import umlteacher.model.dao.Task;

@JsonComponent
public class TaskJsonSerializer extends JsonSerializer<Task> {
	
	@Override
	public void serialize(Task value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeStartObject();
		gen.writeNumberField("taskId", value.getId());
		gen.writeStringField("path", value.getPath());
		gen.writeEndObject();
	}

}
