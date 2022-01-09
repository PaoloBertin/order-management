package eu.opensource.ordermanagement.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/booking")
@Controller
public class BookingController {

    @GetMapping
    public String booking() {

        return "booking/booking";
    }
}
