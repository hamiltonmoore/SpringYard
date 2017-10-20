package com.example.customer;

import com.example.customer.model.Customer;
import com.example.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;


@Controller
public class CustomerController {
    Customer customer;

    @Autowired
    CustomerService customerService;

    @PostConstruct
    private void createTables(){
        customerService.createTable();
    }

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping(path="/add_customer", method = RequestMethod.POST)
    public String createCustomer(@RequestParam(value="firstName") String firstName,
                                @RequestParam(value="lastName") String lastName,
                                @RequestParam(value="phone") String phone,
                                @RequestParam(value="email") String email, Model model)
    {
        Customer customer = new Customer();

        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setPhone(phone);
        customer.setEmail(email);

        customerService.add(customer);

        return "redirect:/customers.html";
    }

    @RequestMapping("/customers")
    public String customers(Model model) {
        model.addAttribute("customers", customerService.get());
        return "customers";
    }

    @RequestMapping("/view_customer/{id}")
    public String viewCustomer(Model model, @PathVariable Integer id) {
        model.addAttribute("customer", customerService.getById(id));
        return "view_customer";
    }

    @RequestMapping(path="/customers/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable Integer id){
        customerService.delete(id);
        return "redirect:/customers.html";
    }

    @RequestMapping(path="/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable Integer id, Customer customer) {
        customerService.update(customer, id);   //this bit may need to be by id, right now customerService reflects customer
        return "redirect:/customers.html";
    }

}

