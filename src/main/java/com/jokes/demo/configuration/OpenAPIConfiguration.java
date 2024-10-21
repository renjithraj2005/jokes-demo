package com.jokes.demo.configuration;

import java.util.List;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Demo",
                version = "0.001.alpha",
                contact = @Contact(
                        name = "Demo", email = "demo@example.com", url = "https://www.example.com"
                ),
                license = @License(
                        name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0"
                ),
                description = "Demo."
        )
)
public class OpenAPIConfiguration {



}
