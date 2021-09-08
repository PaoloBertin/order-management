package eu.opensource.ordermanagement.web.controller;

import eu.opensource.ordermanagement.domain.Customer;
import eu.opensource.ordermanagement.domain.Role;
import eu.opensource.ordermanagement.service.CustomerService;
import eu.opensource.ordermanagement.service.RoleService;
import eu.opensource.ordermanagement.web.util.Message;
import eu.opensource.ordermanagement.web.util.SignupForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Locale;

@Slf4j
@RequiredArgsConstructor
@RequestMapping
@Controller
public class SignUpController {

    private final CustomerService customerService;

    private final RoleService roleService;

    private final MessageSource messageSource;

    @GetMapping("/signup/form")
    public String signupForm(@ModelAttribute SignupForm signupForm) {

        return "login/signupForm";
    }

    @PostMapping(value = "/signup/new")
    public String signup(@Valid SignupForm signupForm, BindingResult result, Model uiModel, RedirectAttributes redirectAttributes,
                         Locale locale) {

        // verifica che i dati del form siano validi
        Message message = null;
        if (result.hasErrors()) {
            message = new Message("error", messageSource.getMessage("message.invalid_signup", new Object[]{}, locale));
            uiModel.addAttribute("message", message);
            return "login/signupForm";
        }

        // verifica che l'username (=email) non sia presente nel db
        message = null;
        String email = signupForm.getUsername();
        if (customerService.getCustomerByUsername(email) != null) {
            message = new Message("error", messageSource.getMessage("message.signup.email", new Object[]{}, locale));
            uiModel.addAttribute("message", message);
            return "login/signupForm";
        }

        // crea nuovo utente
        Customer customer = new Customer();
        customer.setUsername(email);
        customer.setFirstname(signupForm.getFirstname());
        customer.setLastname(signupForm.getLastname());
        customer.setPassword(signupForm.getPassword());

        // crea ruolo
        Role role = roleService.getRoleByName("ROLE_USER");
        // aggiunge ruolo a customer
        customer.getRoles()
                .add(role);
        // aggiunge customer a role
        role.getCustomers()
            .add(customer);

        log.info("Customer: {}", customer);

        // rende persistenti firstname, lastname, email e password del nuovo utente
        customer = customerService.saveCustomer(customer);
        //customer.setId(id);
        customerService.setCurrentCustomer(customer);

        message = new Message("success", messageSource.getMessage("message.registraion.email", new Object[]{}, locale));
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/";
    }
}
