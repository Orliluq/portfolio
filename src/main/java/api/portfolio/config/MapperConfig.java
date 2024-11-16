package api.portfolio.config;

import api.portfolio.mapper.PortfolioMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public PortfolioMapper portfolioMapper() {
        return Mappers.getMapper(PortfolioMapper.class);
    }
}
