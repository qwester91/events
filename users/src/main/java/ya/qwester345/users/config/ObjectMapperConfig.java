package ya.qwester345.users.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import ya.qwester345.users.config.utils.LocalDateTimeDeserializer;
import ya.qwester345.users.config.utils.LocalDateTimeSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


@Configuration
public class ObjectMapperConfig {
    @Bean

    public Jackson2ObjectMapperBuilder mapperBuilder()  {
        ObjectMapper mapper = new ObjectMapper();
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        builder.serializerByType(LocalDate.class, new LocalDateSerializer(
                DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("ru"))));
        builder.deserializerByType(LocalDate.class, new LocalDateDeserializer(
                DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("ru"))));
        builder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer());
        builder.deserializerByType(LocalDateTime.class,new LocalDateTimeDeserializer());
        builder.configure(mapper);
        return builder;
    }
}

