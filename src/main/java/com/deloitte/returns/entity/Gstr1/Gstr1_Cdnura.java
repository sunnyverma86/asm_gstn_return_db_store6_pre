package com.deloitte.returns.entity.Gstr1;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonDeserialize(using = Gstr1_Cdnura.Deserializer.class)
@JsonSerialize(using = Gstr1_Cdnura.Serializer.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cdnura", schema = "gstr1")
public class Gstr1_Cdnura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Double doubleValue;
	private Long integerValue;
	private Boolean boolValue;
	@Transient
	private Object[] anythingArrayValue;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cdnura_class_id")
	private Gstr1_CdnuraClass cdnuraClassValue;
	private String stringValue;

	static class Deserializer extends JsonDeserializer<Gstr1_Cdnura> {
		@Override
		public Gstr1_Cdnura deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
				throws IOException, JsonProcessingException {
			Gstr1_Cdnura value = new Gstr1_Cdnura();
			switch (jsonParser.currentToken()) {
			case VALUE_NULL:
				break;
			case VALUE_NUMBER_INT:
				value.integerValue = jsonParser.readValueAs(Long.class);
				break;
			case VALUE_NUMBER_FLOAT:
				value.doubleValue = jsonParser.readValueAs(Double.class);
				break;
			case VALUE_TRUE:
			case VALUE_FALSE:
				value.boolValue = jsonParser.readValueAs(Boolean.class);
				break;
			case VALUE_STRING:
				String string = jsonParser.readValueAs(String.class);
				value.stringValue = string;
				break;
			case START_ARRAY:
				value.anythingArrayValue = jsonParser.readValueAs(Object[].class);
				break;
			case START_OBJECT:
				value.cdnuraClassValue = jsonParser.readValueAs(Gstr1_CdnuraClass.class);
				break;
			default:
				throw new IOException("Cannot deserialize CdnuraElement");
			}
			return value;
		}
	}

	static class Serializer extends JsonSerializer<Gstr1_Cdnura> {
		@Override
		public void serialize(Gstr1_Cdnura obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
				throws IOException {
			if (obj.doubleValue != null) {
				jsonGenerator.writeObject(obj.doubleValue);
				return;
			}
			if (obj.integerValue != null) {
				jsonGenerator.writeObject(obj.integerValue);
				return;
			}
			if (obj.boolValue != null) {
				jsonGenerator.writeObject(obj.boolValue);
				return;
			}
			if (obj.anythingArrayValue != null) {
				jsonGenerator.writeObject(obj.anythingArrayValue);
				return;
			}
			if (obj.cdnuraClassValue != null) {
				jsonGenerator.writeObject(obj.cdnuraClassValue);
				return;
			}
			if (obj.stringValue != null) {
				jsonGenerator.writeObject(obj.stringValue);
				return;
			}
			jsonGenerator.writeNull();
		}
	}
}
