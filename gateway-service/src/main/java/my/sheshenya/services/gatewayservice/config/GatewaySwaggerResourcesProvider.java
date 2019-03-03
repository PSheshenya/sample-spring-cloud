package my.sheshenya.services.gatewayservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;


@Component
@Primary
@EnableAutoConfiguration
public class GatewaySwaggerResourcesProvider implements SwaggerResourcesProvider {

    //https://yq.aliyun.com/articles/615547
    public static final String API_URI = "/v2/api-docs";

    @Autowired
    private GatewayProperties gatewayProperties;
    @Autowired
    private RouteLocator routeLocator;


    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        List<String> routes = new ArrayList<>();
        routeLocator.getRoutes().subscribe(route -> routes.add(route.getId()));

        //Add the default swagger resource that correspond to the gateway's own swagger doc
        resources.add(swaggerResource("default", API_URI));

        gatewayProperties.getRoutes().stream().filter(routeDefinition -> routes.contains(routeDefinition.getId()))
                .forEach(routeDefinition -> routeDefinition.getPredicates().stream()
                        .forEach(predicateDefinition -> resources.add(swaggerResource(
                                        routeDefinition.getId(),
                                        predicateDefinition.getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0").replace("/**", API_URI)
                                       ))));
//        resources.add(swaggerResource("lb://BSO-SERVICE/swagger"
//                , "/bso/swagger"));
//        resources.add(swaggerResource("lb://BSO-SERVICE"
//                , "lb://BSO-SERVICE/v2/api-docs?group=bsoNumber-api-1.0"));
//        resources.add(swaggerResource("http://localhost:8080"
//                , "http://localhost:8080/v2/api-docs?group=bsoNumber-api-1.0"));
//
        return resources;
    }


    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        // swaggerResource.setLocation("/" + location+ "/v2/api-docs?group=bsoNumber-api-1.0");
        // swaggerResource.setLocation("/" + location + "/v2/api-docs");
        // swaggerResource.setLocation("/" + location + "/swagger-ui.html");

        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        //swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}
