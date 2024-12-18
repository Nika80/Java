import java.io.IOException;
import java.text.DecimalFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

public class JacksonJsonDoubleSerializer extends JsonSerializer<Double> implements ContextualSerializer {

    private int precision = 4;

    public JacksonJsonDoubleSerializer ( ) {

    }

    public JacksonJsonDoubleSerializer ( int precision ) {
        this.precision = precision;
    }

    private DecimalFormat format = null;

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider provider, BeanProperty property ) {
        return new JacksonJsonDoubleSerializer(precision);
    }

    private DecimalFormat getFormat () {
        if ( this.format == null ) {
            StringBuilder b = new StringBuilder("0.");
            b.append("0".repeat(Math.max(0, this.precision)));
            this.format = new DecimalFormat(b.toString());
        }
        return this.format;
    }

    @Override
    public void serialize(Double value, JsonGenerator jgen, SerializerProvider provider ) throws IOException {
        if ( (value == null) || value.isNaN() ) {
            jgen.writeNull();
        }
        else {
            DecimalFormat format = getFormat();
            jgen.writeRawValue(format.format(value));
        }
    }
}