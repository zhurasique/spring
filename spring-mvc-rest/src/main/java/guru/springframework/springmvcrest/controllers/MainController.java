package guru.springframework.springmvcrest.controllers;

import guru.springframework.springmvcrest.domain.Customer;
import guru.springframework.springmvcrest.respositories.CustomerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    private final CustomerRepository customerRepository;

    public MainController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public String index(Map<String, List<Customer>> model) {
        model.put("customers", customerRepository.findAll());
        return "index";
    }
}
