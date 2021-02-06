package umlteacher.model.json.serializer;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import umlteacher.model.dao.Course;

@JsonComponent
public class CourseJsonSerializer extends JsonSerializer<Course> {
	@Override
	public void serialize(Course value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeStartObject();
		gen.writeNumberField("courseId", value.getId());
		gen.writeStringField("courseName", value.getName());
		gen.writeStringField("createdDate", value.getDate().toString());
		gen.writeNumberField("rating", value.getRating() == null ? null : value.getRating());
		gen.writeStringField("timeToComplete", value.getTime().toString());
		gen.writeEndObject();
	}
}
