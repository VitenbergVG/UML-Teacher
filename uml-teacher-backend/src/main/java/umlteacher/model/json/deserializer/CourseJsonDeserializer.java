package umlteacher.model.json.deserializer;

import java.io.IOException;
import java.sql.Date;
import java.time.Duration;
import java.util.Objects;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import umlteacher.exceptions.BadFiledValueException;
import umlteacher.exceptions.MissingFieldException;
import umlteacher.model.dao.Course;

@JsonComponent
public class CourseJsonDeserializer extends JsonDeserializer<Course> {

	@Override
	public Course deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		Course course = new Course();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(p);
		
		String field = "courseId";
		JsonNode value = root.get(field);
		if (Objects.nonNull(value)) {
			if (value.toString().equals("null"))
				throw new BadFiledValueException("field " + field + " cannot be null");
			course.setId(value.asInt());
		} else
			throw new MissingFieldException(field, "Course");

		field = "courseName";
		value = root.get(field);
		if (Objects.nonNull(value)) {
			if (value.toString().equals("null"))
				throw new BadFiledValueException("field " + field + " cannot be null");
			course.setName(value.asText());
		} else
			throw new MissingFieldException(field, "Course");
		
		field = "createdDate";
		value = root.get(field);
		if (Objects.nonNull(value)) {
			if (value.toString().equals("null"))
				throw new BadFiledValueException("field " + field + " cannot be null");
			course.setDate(Date.valueOf(value.asText()));
		} else
			throw new MissingFieldException(field, "Course");
		
		field = "rating";
		value = root.get(field);
		if (Objects.nonNull(value)) {
			if (!value.toString().equals("null"))
				course.setName(value.asText());
		}
		
		field = "timeToComplete";
		value = root.get(field);
		if (Objects.nonNull(value)) {
			if (value.toString().equals("null"))
				throw new BadFiledValueException("field " + field + " cannot be null");
			course.setTime(Duration.parse(value.asText()));
		} else
			throw new MissingFieldException(field, "Course");
		
		return course;
	}
}
