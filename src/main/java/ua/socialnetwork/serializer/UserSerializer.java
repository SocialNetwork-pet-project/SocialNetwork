package ua.test.testtask;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ua.socialnetwork.entity.User;

import java.io.IOException;

public class UserSerializer extends StdSerializer<User> {

    public UserSerializer() {
        this(null);
    }

    public UserSerializer(Class<User> t) {
        super(t);
    }

    //Still COMPLETELY NOT DONE

    @Override
    public void serialize(
            User user, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeStartObject();
        jgen.writeNumberField("id", user.getId());
        jgen.writeStringField("username", user.getUsername());
        jgen.writeStringField("firstName", user.getFirstName());
        jgen.writeStringField("lastName", user.getLastName());
        jgen.writeStringField("email", user.getEmail());
        jgen.writeStringField("bio", user.getBio());
        jgen.writeNumberField("age", user.getAge());
        jgen.writeStringField("country", user.getCountry());
        jgen.writeStringField("gender", user.getGender().toString());
//        jgen.writeStringField("role", user.getRole().toString());
        jgen.writeBooleanField("isEmployed", user.isEmployed());
//        //ToDo add list to serializer
//        jgen.write("friends", user.getFriends());
        jgen.writeEndObject();
    }
}