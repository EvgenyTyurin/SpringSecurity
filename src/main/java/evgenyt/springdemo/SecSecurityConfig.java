package evgenyt.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * Security ON and config
 */

@Configuration
@EnableWebSecurity
public class SecSecurityConfig extends WebSecurityConfigurerAdapter {

    // Needed to provide password
    @Autowired
    PasswordEncoder passwordEncoder;

    // Bean declared in AppConfig.java
    @Autowired
    DataSource dataSource;

    // Add users here
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // user1 hardcoded
        auth.inMemoryAuthentication()
                .withUser("user1")
                .password(passwordEncoder.encode("pass1"))
                .roles("USER");
        // user2 from database tables: users(password must be encrypt) and authorities
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from users where username=?")
                .authoritiesByUsernameQuery("select username, authority from authorities where username=?")
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
