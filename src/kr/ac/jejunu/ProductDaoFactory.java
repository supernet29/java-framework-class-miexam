package kr.ac.jejunu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by super on 2017-04-21.
 */
@Configuration
public class ProductDaoFactory {

    @Bean
    public ProductDao productDao() {
         return new ProductDao(ConnectionMaker());
    }

    @Bean
    public JejuConnectionMaker ConnectionMaker() {
        return new JejuConnectionMaker();
    }
}
