package bootcamp.everis.gateway.hystrix;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class HystrixController {

  @GetMapping("/message")
  public String test() {
    return "Called in Fallback Service";
  }

}
