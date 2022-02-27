package io.johnsell620.jMessage;

// import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class JMessageApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		// SpringApplication.run(JMessageApplication.class, args);
		new JMessageApplication().configure(new SpringApplicationBuilder(JMessageApplication.class)).run(args);
	}
	
}
