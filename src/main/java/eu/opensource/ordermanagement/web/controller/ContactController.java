package eu.opensource.ordermanagement.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping("/contact")
@Controller
public class ContactController {

    @GetMapping
    public String contact() {

        return "contact/contacts";
    }
}
