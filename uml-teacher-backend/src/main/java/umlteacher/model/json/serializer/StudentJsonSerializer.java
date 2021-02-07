package umlteacher.model.json.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import umlteacher.model.dao.Student;

public class StudentJsonSerializer extends JsonSerializer<Student> {
	
	@Override
	public void serialize(Student value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeStartObject();
		gen.writeNumberField("studentId", value.getId());
		gen.writeObjectField("user", value.getUser());
		gen.writeObjectField("edu", value.getEdu());
		gen.writeStringField("userContacts", value.getUserContacts());
		gen.writeEndObject();
	}

}
