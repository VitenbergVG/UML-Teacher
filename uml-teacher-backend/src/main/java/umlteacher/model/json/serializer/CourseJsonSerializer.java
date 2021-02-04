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
		gen.writeStringField("course_id", value.getId() + "");
		gen.writeStringField("course_name", value.getName());
		gen.writeStringField("created_date", value.getDate().toString());
		gen.writeStringField("rating", value.getRating() == null ? null : value.getRating().toString());
		gen.writeStringField("time_to_complete", value.getTime().toString());
		gen.writeEndObject();
	}
}
