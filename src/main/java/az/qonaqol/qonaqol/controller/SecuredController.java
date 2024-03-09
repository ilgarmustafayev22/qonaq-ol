package az.qonaqol.qonaqol.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecuredController {

    @GetMapping("/api/v1/secured")
    public String securedEndpoint() {
        return "This is a secured endpoint.";
    }

    @GetMapping("/api/v1/non-secured")
    public String nonSecuredEndpoint() {
        return "This is a nonSecured endpoint.";
    }

}
