package Rest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.ServerVariable;

@OpenAPIDefinition(
        info = @Info(
                title = "API CSU",
                version = "1.0.0",
                description = "Swagger para la API de CSU",
                contact = @Contact(
                        name = "Francisco Estrada y Ludmila Dosil",
                        email = "ludmidosil@gmail.com",
                        url = "https://mi-sitio.com"
                )
        )
        , servers = {
                @Server(
                        url = "http://localhost:8080/csu/api",
                        description = "Servidor de desarrollo",
                        variables = {
                                @ServerVariable(name = "port", defaultValue = "8080")
                        }
                ),
                @Server(
                        url = "https://mi-sitio.com/api",
                        description = "Servidor de producción"
                )
        }
)
public class SwaggerConfig  {

}