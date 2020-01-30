package guru.springframework.springmvcrest.bootstrap;

import guru.springframework.springmvcrest.domain.Customer;
import guru.springframework.springmvcrest.respositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final CustomerRepository customerRepository;

    public BootStrapData(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("loading");

        Customer c1 = new Customer();
        c1.setFirstname("Ford");
        c1.setLastname("Bosch");
        customerRepository.save(c1);

        Customer c2 = new Customer();
        c2.setFirstname("Kaszm");
        c2.setLastname("Zaslah");
        customerRepository.save(c2);

        Customer c3 = new Customer();
        c3.setFirstname("Ooper");
        c3.setLastname("Axe");
        customerRepository.save(c3);

        System.out.println(customerRepository.count() + " saved");
    }
}
