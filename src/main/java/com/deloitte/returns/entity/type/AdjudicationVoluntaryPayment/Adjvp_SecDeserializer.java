package com.deloitte.returns.entity.type.AdjudicationVoluntaryPayment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class Adjvp_SecDeserializer extends StdDeserializer<List<String>> {

    private static final long serialVersionUID = 1L;

    public Adjvp_SecDeserializer() {
        super(List.class);
    }

    @Override
    public List<String> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

        List<String> result = new ArrayList<>();
        JsonNode node = p.getCodec().readTree(p);

        if (node == null || node.isNull()) {
            return result;   // return empty list
        }

        if (node.isArray()) {
            for (JsonNode element : node) {
                result.add(element.asText());
            }
        } else if (node.isTextual()) {
            // Handle: "sec": "73(5)"
            result.add(node.asText());
        }

        return result;
    }
}
