package at.kaindorf.customermgmt.web;

import at.kaindorf.customermgmt.database.CustomerRepository;
import at.kaindorf.customermgmt.pojo.Customer;
import jakarta.servlet.Servlet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.swing.text.html.parser.Entity;
import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * <h3>Created by IntelliJ IDEA.</h3><br>
 * <b>Project:</b> SpringRestCustomerMgmt<br>
 * <b>User:</b> Simon Schoeggler<br>
 * <b>Date:</b> 18. Jänner 2023<br>
 * <b>Time:</b> 09:26<br>
 */

@RestController
@Slf4j
@RequestMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class CustomerController {
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id) {
        /*
        Optional<Customer> customerOpt = customerRepository.findById(id);
        return customerOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        */
        return ResponseEntity.of(customerRepository.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomer() {
        /*
        List<Customer> customers = customerRepository.findAll();
        return !customers.isEmpty() ? ResponseEntity.ok(customers) : ResponseEntity.noContent().build();
        */
        return ResponseEntity.of(Optional.of(customerRepository.findAll()));
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        if (customerRepository.existsById(customer.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        customerRepository.save(customer);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(customer.getId())
                .toUri();

        /*return ResponseEntity.status(HttpStatus.CREATED).body(customer);*/
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id) {
        Optional<Customer> optCustomer = customerRepository.findById(id);

        if (optCustomer.isPresent()) {
            customerRepository.deleteById(id);
            return ResponseEntity.ok(optCustomer.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer
                                                           customer) {
        if (!customerRepository.existsById(customer.getId())) {
            return ResponseEntity.notFound().build();
        }
        try {
            customerRepository.save(customer);
            return ResponseEntity.ok(customer);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


}
