package se.sundsvall.template;

import static org.springframework.boot.SpringApplication.run;

import se.sundsvall.dept44.ServiceApplication;

//TODO Ta bort då det redan finns en Application-klass
@ServiceApplication
public class Application {
	public static void main(final String... args) {
		run(Application.class, args);
	}
}
