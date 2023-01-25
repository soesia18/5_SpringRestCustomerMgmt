package at.kaindorf.customermgmt.web;

import at.kaindorf.customermgmt.database.CustomerRepository;
import at.kaindorf.customermgmt.pojo.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Customer> getCustomer (@PathVariable("id") Long id) {
        Optional<Customer> customerOpt = customerRepository.findById(id);
        return customerOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

}
