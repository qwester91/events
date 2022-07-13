package ya.qwester345.classifier.countries.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ya.qwester345.classifier.countries.service.api.IMapper;
import ya.qwester345.classifier.countries.service.mapper.CountryMapper;

@Configuration
public class ServiceConfiguration {
    @Bean
    public IMapper mapper(){
        return new CountryMapper();
    }
}
