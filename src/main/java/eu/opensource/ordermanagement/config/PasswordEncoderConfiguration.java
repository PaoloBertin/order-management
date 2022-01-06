package eu.opensource.ordermanagement.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

@Configuration
public class PasswordEncoderConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {

//        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        return new BCryptPasswordEncoder(4);
//      return NoOpPasswordEncoder.getInstance();

//    return passwordEncoder;


//    String idForEncode = "bcrypt";
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
