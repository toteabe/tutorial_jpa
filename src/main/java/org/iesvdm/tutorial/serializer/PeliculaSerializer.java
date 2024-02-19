package org.iesvdm.tutorial.serializer;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.iesvdm.tutorial.domain.Pelicula;

public class PeliculaSerializer  extends StdSerializer<Pelicula> {

    public PeliculaSerializer() {
        this(null);
    }

    public PeliculaSerializer(Class < Pelicula > t) {
        super(t);
    }

    @Override
    public void serialize(Pelicula pelicula, JsonGenerator jgen,
                          SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeStartObject(); // equivale en JSON a abrir {
        jgen.writeNumberField("id", pelicula.getId()); // "id": 1
        jgen.writeStringField("titulo", pelicula.getTitulo()); // "pelicula": "peli"
        jgen.writeFieldName("idioma"); // "idioma":

        if (pelicula.getIdioma() != null) {
            jgen.writeStartObject(); // equivale en JSON a abrir {
            jgen.writeNumberField("id", pelicula.getIdioma().getId());
            jgen.writeStringField("nombre", pelicula.getIdioma().getNombre());
            jgen.writeEndObject(); // equivale en JSON a cerrar }
        } else {
            jgen.writeRawValue("null"); //como no existe el idioma a null
        }

        jgen.writeEndObject(); //equivale en JSON a cerrar }

    }
}