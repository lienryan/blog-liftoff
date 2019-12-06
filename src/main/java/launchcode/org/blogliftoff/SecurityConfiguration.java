package launchcode.org.blogliftoff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select email, password, true from user where email=?")
                .authoritiesByUsernameQuery("select user_email, role_name as role from user_roles where user_email=?")
                .passwordEncoder(passwordEncoder()).rolePrefix("ROLE_");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/user/register","/user/login").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/posts/detail/**").permitAll()
                .antMatchers("/comments/create/**").permitAll()
                .antMatchers("users/profile").hasAnyRole("USER")
                .antMatchers("posts/create").hasAnyRole("USER")
                .and()
                    .formLogin()
                    .loginPage("/user/login")
                    .usernameParameter("email")
                    .permitAll()
                    .defaultSuccessUrl("/user/profile", true)
                .and()
                    .logout()
                    .logoutSuccessUrl("/user/login");

    }

}
