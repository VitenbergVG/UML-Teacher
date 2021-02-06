package umlteacher.model.json.deserializer;

import java.io.IOException;
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
import umlteacher.model.dao.Edu;

@JsonComponent
public class EduJsonDeserializer extends JsonDeserializer<Edu> {
	
	@Override
	public Edu deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		Edu edu = new Edu();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(p);
		
		String field = "eduId";
		JsonNode value = root.get(field);
		if (Objects.nonNull(value)) {
			if (value.toString().equals("null"))
				throw new BadFiledValueException("field " + field + " cannot be null");
			edu.setId(value.asInt());
		} else
			throw new MissingFieldException(field, "Edu");

		field = "eduName";
		value = root.get(field);
		if (Objects.nonNull(value)) {
			if (value.toString().equals("null"))
				throw new BadFiledValueException("field " + field + " cannot be null");
			edu.setName(value.asText());
		} else
			throw new MissingFieldException(field, "Edu");
		return edu;
	}

}
