package nymil.chess.logic.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class toStringSerializer extends JsonSerializer<Object> {
    @Override
    public void serialize(Object object, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(object.toString());
    }
}
