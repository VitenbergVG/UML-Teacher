package umlteacher.model.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import umlteacher.model.dao.Course;

import java.io.IOException;

@JsonComponent
public class CourseJsonSerializer extends JsonSerializer<Course> {
    @Override
    public void serialize(Course value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("id", value.getId() + "");
        gen.writeStringField("name", value.getName());
        gen.writeStringField("createdDate", value.getDate().toString());
        gen.writeStringField("rating", value.getRating() == null ? null : value.getRating().toString());
        gen.writeStringField("timeToComplete", value.getTime().toString());
        gen.writeEndObject();
    }
}
