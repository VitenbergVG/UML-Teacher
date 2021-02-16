package umlteacher.model.json.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;
import umlteacher.exceptions.BadFiledValueException;
import umlteacher.exceptions.MissingFieldException;
import umlteacher.model.dao.Student;
import umlteacher.service.dao.EduService;
import umlteacher.service.dao.UserServiceImpl;

import java.io.IOException;
import java.util.Objects;

@JsonComponent
public class StudentJsonDeserializer extends JsonDeserializer<Student> {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private EduService eduService;

    @Override
    public Student deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        Student student = new Student();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(p);

        String field = "userId";
        JsonNode value = root.get(field);
        if (Objects.nonNull(value)) {
            if (value.toString().equals("null"))
                throw new BadFiledValueException("field " + field + " cannot be null");
            student.setUser(userService.findUserById(value.asLong()));
        } else
            throw new MissingFieldException(field, "Student");

        field = "eduId";
        value = root.get(field);
		if (Objects.nonNull(value)) {
			if (value.toString().equals("null"))
				throw new BadFiledValueException("field " + field + " cannot be null");
			student.setEdu(eduService.findById(value.asInt()));
		} else
			throw new MissingFieldException(field, "Student");

		field = "userContacts";
		value = root.get(field);
		if (Objects.nonNull(value)) {
			if (value.toString().equals("null"))
				throw new BadFiledValueException("field " + field + " cannot be null");
			student.setUserContacts(value.asText());
		} else
			throw new MissingFieldException(field, "Student");
		return student;
	}

}
