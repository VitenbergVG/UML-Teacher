package umlteacher.model.json.deserializer;

import java.io.IOException;
import java.sql.Date;
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
import umlteacher.model.dao.Employee;
import umlteacher.service.dao.UserServiceImpl;

@JsonComponent
public class EmployeeJsonDeserializer extends JsonDeserializer<Employee> {
	@Autowired
	private UserServiceImpl userService;
	
	@Override
	public Employee deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		Employee employee = new Employee();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(p);
		
		String field = "employeeId";
		JsonNode value = root.get(field);
		if (Objects.nonNull(value)) {
			if (value.toString().equals("null"))
				throw new BadFiledValueException("field " + field + " cannot be null");
			employee.setId(value.asInt());
		} else
			throw new MissingFieldException(field, "Employee");

		field = "userId";
		value = root.get(field);
		if (Objects.nonNull(value)) {
			if (value.toString().equals("null"))
				throw new BadFiledValueException("field " + field + " cannot be null");
			employee.setUser(userService.findUserById(value.asLong()));
		} else
			throw new MissingFieldException(field, "Employee");

		field = "hiringDate";
		value = root.get(field);
		if (Objects.nonNull(value)) {
			if (value.toString().equals("null"))
				throw new BadFiledValueException("field " + field + " cannot be null");
			employee.setHiringDate(Date.valueOf(value.asText()));
		} else
			throw new MissingFieldException(field, "Employee");

		field = "phoneNumber";
		value = root.get(field);
		if (Objects.nonNull(value)) {
			if (value.toString().equals("null"))
				throw new BadFiledValueException("field " + field + " cannot be null");
			employee.setPhoneNumber(value.asText());
		} else
			throw new MissingFieldException(field, "Employee");
		
		field = "email";
		value = root.get(field);
		if (Objects.nonNull(value)) {
			if (value.toString().equals("null"))
				throw new BadFiledValueException("field " + field + " cannot be null");
			employee.setEmail(value.asText());
		} else
			throw new MissingFieldException(field, "Employee");
		return employee;
	}
}
