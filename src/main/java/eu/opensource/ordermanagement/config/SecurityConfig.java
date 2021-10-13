package eu.opensource.ordermanagement.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@EnableWebSecurity(debug = false)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    private final UserDetailsService userDetailsService;

    @Override
    public void configure(final AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder)
        ;
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        http.authorizeRequests()
//            .antMatchers("/**").permitAll()
            .antMatchers("/resources/**").permitAll()
            .antMatchers("/*").permitAll()
            .antMatchers("/login/*").permitAll()
            .antMatchers("/logout/*").permitAll()
            .antMatchers("/errors").permitAll()
            .antMatchers("/orders/**").hasAnyRole("USER", "ADMIN")
            .antMatchers("/cart/**").hasAnyRole("USER", "ADMIN")
            .antMatchers("/customers/*").hasAnyRole("USER", "ADMIN")
            .antMatchers("/admin/**").hasRole("ADMIN")

            .and()
            .formLogin()
            .loginPage("/login/form")
//            .loginProcessingUrl("/login")
            .loginProcessingUrl("/authenticate")
            .failureUrl("/login?error")
            .defaultSuccessUrl("/default", true)
            .permitAll()
            .usernameParameter("username")
            .passwordParameter("password")

            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login?logout")

            .and()
            .httpBasic()

            // CSRF is enabled by default, with Java Config
            .and()
            .csrf()
            .disable()

            // Enable <frameset> in order to use H2 web console
            .headers()
            .frameOptions()
            .disable()
        ;

    }
}
