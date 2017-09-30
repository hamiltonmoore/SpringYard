package com.example.customer;

import com.example.customer.model.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CustomerController {
    Customer customer;

    @RequestMapping("/")
    public String home() {
        return "home";
    }


    @RequestMapping("/add_customer")
    public String addcustomer() {
        return "add_customer";
    }
    @RequestMapping(path="/add_customer", method = RequestMethod.POST)
    public String createCustomer(@RequestParam(value="id") String id,
                                @RequestParam(value="firstName") String firstName,
                                @RequestParam(value="lastName") String lastName,
                                @RequestParam(value="phone") String phone,
                                @RequestParam(value="email") String email)
    {
        Customer customer = new Customer();

        customer.setId(id);
        customer.setFirstName(firstName);
        customer.setFirstName(lastName);
        customer.setPhone(phone);
        customer.setEmail(email);
        this.customer = customer;

        return "redirect:/customers.html";
    }

    @RequestMapping("customers")
    public String customers(Model model) {
        model.addAttribute("customers", customer);
        return "customers";
    }

    @RequestMapping("view_customer")
    public String viewCustomer(Model model) {
        model.addAttribute("customer", customer);
        return "view_customer";
    }

}
