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
		gen.writeNumberField("groupId", value.getId());
		gen.writeStringField("groupName", value.getName());
		gen.writeObjectField("currentCourse", value.getCurrentCourse());
		gen.writeObjectField("firstTeacher", value.getFirstTeacher());
		gen.writeObjectField("secondTeacher", value.getSecondTeacher());
		gen.writeEndObject();
	}

}
