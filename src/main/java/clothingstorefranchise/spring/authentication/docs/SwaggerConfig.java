package clothingstorefranchise.spring.authentication.docs;

import org.springframework.context.annotation.Configuration;

import clothingstorefranchise.spring.common.swagger.BaseSwaggerConfig;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig{

	public SwaggerConfig() {
		super("clothingstorefranchise.spring.authentication.controller");
	}
}
