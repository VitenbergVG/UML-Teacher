package umlteacher.model.json.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import umlteacher.model.dao.Group;

public class GroupJsonSerializer extends JsonSerializer<Group> {
	
	@Override
	public void serialize(Group value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeStartObject();
		gen.writeStringField("group_id", value.getId() + "");
		gen.writeStringField("group_name", value.getName());
		gen.writeObjectField("current_course", value.getCurrentCourse());
		gen.writeObjectField("first_teacher", value.getFirstTeacher());
		gen.writeObjectField("second_teacher", value.getSecondTeacher());
		gen.writeEndObject();
	}

}
