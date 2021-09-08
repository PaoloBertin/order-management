package eu.opensource.ordermanagement.web.controller;

import eu.opensource.ordermanagement.domain.Customer;
import eu.opensource.ordermanagement.service.CustomerService;
import eu.opensource.ordermanagement.web.util.AddressForm;
import eu.opensource.ordermanagement.web.util.CustomerForm;
import eu.opensource.ordermanagement.web.util.Message;
import eu.opensource.ordermanagement.web.util.PasswordForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Locale;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("customers")
@Controller
public class CustomerController {

    private final MessageSource messageSource;

    private final CustomerService customerService;

    @GetMapping
    public String customerForm(@RequestParam String form, Authentication authentication, Model uiModel) {

        Customer customer = (Customer) authentication.getPrincipal();
        CustomerForm customerForm = new CustomerForm();
        customerForm.setId(customer.getId());
        customerForm.setFirstname(customer.getFirstname());
        customerForm.setLastname(customer.getLastname());
        customerForm.setUsername(customer.getUsername());

        uiModel.addAttribute("customerForm", customerForm);

        return "customers/customerView";
    }

    @PutMapping
    public String updateCustomerPersonalData(@Valid CustomerForm customerForm, BindingResult result, Model uiModel,
                                             RedirectAttributes redirectAttributes,
                                             Locale locale) {

        Message message;
        // verifica che i dati del form siano validi
        if (result.hasErrors()) {
            message = new Message("error", messageSource.getMessage("message.incomplete_data", new Object[]{}, locale));
            uiModel.addAttribute("message", message);
            return "customers/customerView";
        }

        // aggiorna informazioni utente
        Optional<Customer> customerTry = customerService.getCustomerById(customerForm.getId());
        Customer customer = new Customer();
        if (customerTry.isPresent()) {
            customer = customerTry.get();
            customer.setFirstname(customerForm.getFirstname());
            customer.setLastname(customerForm.getLastname());
            customer.setUsername(customerForm.getUsername());
        } else {
            message = new Message("error", messageSource.getMessage("message.incomplete_data", new Object[]{}, locale));
            uiModel.addAttribute("message", message);
            return "customers/customerView";
        }

        log.info("Customer: {}", customer);

        // aggiorna dati utente
        customerService.saveCustomer(customer);
        customerService.setCurrentCustomer(customer);

        message = new Message("success", messageSource.getMessage("message.successful_update", new Object[]{}, locale));
        redirectAttributes.addFlashAttribute("message", message);

        return "redirect:/";
    }

    @GetMapping("/changePassword")
    public String changePasswordForm(Model uiModel) {

        uiModel.addAttribute("passwordForm", new PasswordForm());

        return "customers/changePassword";
    }

    @PostMapping("/changePassword")
    public String changePassword(@Valid PasswordForm passwordForm, Authentication authentication, BindingResult result, Model uiModel,
                                 RedirectAttributes redirectAttributes, Locale locale) {

        // le password non sono state inserite
        Message message = null;
        if (result.hasErrors()) {
            message = new Message("error", messageSource.getMessage("message.invalid_signup", new Object[]{}, locale));
            uiModel.addAttribute("message", message);
            return "customers/changePassword";
        }

        // verifica che le password inserite siano uguali
        String password = passwordForm.getPassword();
        String password2 = passwordForm.getPassword2();
        if (!password.equals(password2)) {
            message = new Message("error", messageSource.getMessage("message.invalid_signup", new Object[]{}, locale));
            uiModel.addAttribute("message", message);
            return "login/signupForm";
        }

        // rende persistente la modifica della password
        Customer customer = (Customer) authentication.getPrincipal();
        customer.setPassword(passwordForm.getPassword());

        log.info("Customer: {}", customer);

        customerService.saveCustomer(customer);
        customerService.setCurrentCustomer(customer);

        message = new Message("success", messageSource.getMessage("message.passwordChanged", new Object[]{}, locale));
        redirectAttributes.addFlashAttribute("message", message);

        return "redirect:/";
    }

    @GetMapping("/changeAddresses")
    public String changeAddresses(Authentication authentication, Model uiModel) {

        Customer customer = (Customer) authentication.getPrincipal();
        uiModel.addAttribute("customer", customer);

        return "customers/changeAddresses";
    }


    @GetMapping("/changeAddressHolder")
    public String changeAddressesHolder(Authentication authentication, Model uiModel) {

        Customer customer = (Customer) authentication.getPrincipal();
        AddressForm addressForm = new AddressForm();
        addressForm.setId(customer.getId());
        addressForm.setFirstname(customer.getHeader_addres().getFirstname());
        addressForm.setLastname(customer.getHeader_addres().getLastname());
        addressForm.setAddressId(customer.getHeader_addres().getId());
        addressForm.setHouseNumber(customer.getHeader_addres().getHouseNumber());
        addressForm.setStreet(customer.getHeader_addres().getStreet());
        addressForm.setCity(customer.getHeader_addres().getCity());
        addressForm.setCountry(customer.getHeader_addres().getCountry());
        addressForm.setState(customer.getHeader_addres().getState());

        uiModel.addAttribute("addressForm", addressForm);

        return "customers/editAddressHolder";
    }

    @PostMapping("/changeAddressesHolder")
    public String changeAddressesHolder(AddressForm addressForm, BindingResult result, Model uiModel, RedirectAttributes redirectAttributes,
                                        Locale locale) {

        // le password non sono state inserite
        Message message = null;
        if (result.hasErrors()) {
            message = new Message("error", messageSource.getMessage("message.incomplete_data", new Object[]{}, locale));
            uiModel.addAttribute("message", message);
            return "customers/editAddresHolder";
        }

        // aggiorna indirizzo
        Optional<Customer> customerTry = customerService.getCustomerById(addressForm.getId());
        Customer customer = new Customer();
        if (customerTry.isPresent()) {
            customer = customerTry.get();

            customer.getHeader_addres().setFirstname(addressForm.getFirstname());
            customer.getHeader_addres().setLastname(addressForm.getLastname());
            customer.getHeader_addres().setHouseNumber(addressForm.getHouseNumber());
            customer.getHeader_addres().setStreet(addressForm.getStreet());
            customer.getHeader_addres().setCity(addressForm.getCity());
            customer.getHeader_addres().setCountry(addressForm.getCountry());
            customer.getHeader_addres().setState(addressForm.getState());
        } else {
            message = new Message("error", messageSource.getMessage("message.invalid_user", new Object[]{}, locale));
            uiModel.addAttribute("message", message);
            return "welcome";
        }

        customerService.saveCustomer(customer);
        customerService.setCurrentCustomer(customer);
        message = new Message("success", messageSource.getMessage("message.addressChanged", new Object[]{}, locale));
        redirectAttributes.addFlashAttribute("message", message);

        return "redirect:/";
    }

    @GetMapping("/changeAddressDelivery")
    public String changeAddressesDelivery(Authentication authentication, Model uiModel) {

        Customer customer = (Customer) authentication.getPrincipal();
        AddressForm addressForm = new AddressForm();
        addressForm.setId(customer.getId());
        addressForm.setFirstname(customer.getDelivery_addres().getFirstname());
        addressForm.setLastname(customer.getDelivery_addres().getLastname());
        addressForm.setAddressId(customer.getDelivery_addres().getId());
        addressForm.setHouseNumber(customer.getDelivery_addres().getHouseNumber());
        addressForm.setStreet(customer.getDelivery_addres().getStreet());
        addressForm.setCity(customer.getDelivery_addres().getCity());
        addressForm.setCountry(customer.getDelivery_addres().getCountry());
        addressForm.setState(customer.getDelivery_addres().getState());

        uiModel.addAttribute("addressForm", addressForm);

        return "customers/editAddressDelivery";
    }

    @PostMapping("/changeAddressesDelivery")
    public String changeAddressesDelivery(AddressForm addressForm, BindingResult result, Model uiModel, RedirectAttributes redirectAttributes,
                                        Locale locale) {

        // le password non sono state inserite
        Message message = null;
        if (result.hasErrors()) {
            message = new Message("error", messageSource.getMessage("message.incomplete_data", new Object[]{}, locale));
            uiModel.addAttribute("message", message);
            return "customers/editAddresDelivery";
        }

        // aggiorna indirizzo
        Optional<Customer> customerTry = customerService.getCustomerById(addressForm.getId());
        Customer customer = new Customer();
        if (customerTry.isPresent()) {
            customer = customerTry.get();
            customer.getDelivery_addres().setFirstname(addressForm.getFirstname());
            customer.getDelivery_addres().setLastname(addressForm.getLastname());
            customer.getDelivery_addres().setHouseNumber(addressForm.getHouseNumber());
            customer.getDelivery_addres().setStreet(addressForm.getStreet());
            customer.getDelivery_addres().setCity(addressForm.getCity());
            customer.getDelivery_addres().setCountry(addressForm.getCountry());
            customer.getDelivery_addres().setState(addressForm.getState());
        } else {
            message = new Message("error", messageSource.getMessage("message.invalid_user", new Object[]{}, locale));
            uiModel.addAttribute("message", message);
            return "welcome";
        }

        customerService.saveCustomer(customer);
        customerService.setCurrentCustomer(customer);
        message = new Message("success", messageSource.getMessage("message.addressChanged", new Object[]{}, locale));
        redirectAttributes.addFlashAttribute("message", message);

        return "redirect:/";
    }
}
