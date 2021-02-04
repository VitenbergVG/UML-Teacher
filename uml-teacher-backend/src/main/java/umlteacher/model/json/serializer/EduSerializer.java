package umlteacher.model.json.serializer;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import umlteacher.model.dao.Edu;

@JsonComponent
public class EduSerializer extends JsonSerializer<Edu> {
	
	@Override
	public void serialize(Edu value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeStartObject();
		gen.writeStringField("edu_id", value.getId() + "");
		gen.writeStringField("edu_name", value.getName());
		gen.writeEndObject();
	}

}
