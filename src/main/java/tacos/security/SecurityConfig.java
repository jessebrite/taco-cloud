package tacos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Resource(name = "userRepositoryUserDetailsService")
	UserDetailsService userDetailsService;

//	use the next method "BCryptPasswordEncoder" instead
//	only using for testing purposes
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new StandardPasswordEncoder("53cr3t");
	}

//	uncomment to use instead of the deprecated StandardPasswordEncoder
//	@Bean
//	public PasswordEncoder encoder() {
//		return new BCryptPasswordEncoder();
//	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder());
//
	}
}
