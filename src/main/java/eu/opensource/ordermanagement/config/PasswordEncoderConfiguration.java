package eu.opensource.ordermanagement.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {

    String idForEncode = "noop";
    Map<String,PasswordEncoder> encoders = new HashMap<>();
    
    encoders.put(idForEncode,   new BCryptPasswordEncoder());
    encoders.put("noop",        NoOpPasswordEncoder.getInstance());
//    encoders.put("pbkdf2",      new Pbkdf2PasswordEncoder());
//    encoders.put("scrypt",      new SCryptPasswordEncoder());
//    encoders.put("sha256",      new StandardPasswordEncoder());
   
    PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(idForEncode, encoders);

    return passwordEncoder;
    }
}
