package at.archkb.server.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Rainer on 17.05.2016.
 */
@Configuration
public class DtoConfiguration {

    // TODO: Using this ModelMapper for converting Domain Objects to DTOs

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();

        return new ModelMapper();
    }

}
