package umlteacher.model.json.serializer;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import umlteacher.model.dao.User;

@JsonComponent
public class UserJsonSerializer extends JsonSerializer<User> {
	
	@Override
	public void serialize(User value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeStartObject();
		gen.writeStringField("user_id", value.getId().toString());
		gen.writeStringField("fullname", value.getFullname());
		gen.writeStringField("role", value.getRole().getName());
		gen.writeEndObject();
	}

}
