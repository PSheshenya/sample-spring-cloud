package my.sheshenya.services.foofluxservice;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.*;
import java.time.Duration;
import java.time.LocalTime;

@RestController
@RequestMapping(value = "/foo")
public class FooController {

    @GetMapping(value = "/hello")
    public Mono<String> hello() {return Mono.just("Hi from Foo service");}


    @GetMapping(value = "/events" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> eventsStream(){
        return Flux.interval(Duration.ofSeconds(2))
                .map(tick-> LocalTime.now() + "{\"status\":\"OKay...\"}" + tick);
    }


}
