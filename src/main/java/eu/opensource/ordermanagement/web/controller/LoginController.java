package eu.opensource.ordermanagement.web.controller;

import eu.opensource.ordermanagement.domain.Customer;
import eu.opensource.ordermanagement.service.CustomerService;
import eu.opensource.ordermanagement.web.util.LoginForm;
import eu.opensource.ordermanagement.web.util.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Locale;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/login")
@Controller
public class LoginController {

    private final MessageSource messageSource;

    private final CustomerService customerService;

    @GetMapping("/form")
    public String login(@ModelAttribute LoginForm loginForm) {

        return "login/loginForm";
    }

    @PostMapping("/authenticate")
    public String autenticate(@Valid LoginForm loginForm, BindingResult result, RedirectAttributes redirectAttributes, Model uiModel,
                              Locale locale) {

        // se i dati del form non sono completi ritorna al form di login
        if (result.hasErrors()) {
            return "redirect:/login?error";
        }

        // verifica che l'utente sia registrato
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();
        Customer customer = customerService.getCustomerByUsernameAndPassword(username, password);
        if (customer != null) {
            customerService.setCurrentCustomer(customer);
        } else {
            return "redirect:/login?error";
        }

        return "redirect:/default";
    }

    @GetMapping
    public String loginError(@RequestParam(value = "error", required = false) String error,
                             @RequestParam(value = "logout", required = false) String logout, Model uiModel, Locale locale) {

        Message message = null;
        if (error != null) {
            message = new Message("error", messageSource.getMessage("message.login.error", new Object[]{}, locale));
            uiModel.addAttribute("message", message);
            uiModel.addAttribute("loginForm", new LoginForm());
            log.info("login errato");

            return "login/loginForm";
        }

        if (logout != null) {
            message = new Message("success", messageSource.getMessage("message.logout.success", new Object[]{}, locale));
            uiModel.addAttribute("message", message);
            log.info("logout riuscito");
        }

        return "welcome";
    }
}
