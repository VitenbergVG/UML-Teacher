package umlteacher.model.json.deserializer;

import java.io.IOException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import umlteacher.exceptions.BadFiledValueException;
import umlteacher.exceptions.MissingFieldException;
import umlteacher.model.dao.Group;
import umlteacher.service.dao.CourseService;
import umlteacher.service.dao.EmployeeService;

@JsonComponent
public class GroupJsonDeserializer extends JsonDeserializer<Group> {
	@Autowired
	private CourseService courseService;
	@Autowired
	private EmployeeService employeeService;

	@Override
	public Group deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		Group group = new Group();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(p);

		String field = "groupId";
		JsonNode value = root.get(field);
		if (Objects.nonNull(value)) {
			if (value.toString().equals("null"))
				throw new BadFiledValueException("field " + field + " cannot be null");
			group.setId(value.asInt());
		} else
			throw new MissingFieldException(field, "Group");

		field = "groupName";
		value = root.get(field);
		if (Objects.nonNull(value)) {
			if (value.toString().equals("null"))
				throw new BadFiledValueException("field " + field + " cannot be null");
			group.setName(value.asText());
		} else
			throw new MissingFieldException(field, "Group");

		field = "currentCourseId";
		value = root.get(field);
		if (Objects.nonNull(value)) {
			if (value.toString().equals("null"))
				throw new BadFiledValueException("field " + field + " cannot be null");
			group.setCurrentCourse(courseService.findCourseById(value.asInt()));
		} else
			throw new MissingFieldException(field, "Group");

		field = "firstTeacherId";
		value = root.get(field);
		if (Objects.nonNull(value)) {
			if (value.toString().equals("null"))
				throw new BadFiledValueException("field " + field + " cannot be null");
			group.setFirstTeacher(employeeService.findById(value.asInt()));
		} else
			throw new MissingFieldException(field, "Group");

		field = "secondTeacherId";
		value = root.get(field);
		if (Objects.nonNull(value)) {
			if (!value.toString().equals("null"))
				group.setFirstTeacher(employeeService.findById(value.asInt()));
		}
		return group;
	}

}
