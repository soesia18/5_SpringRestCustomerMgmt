package at.kaindorf.customermgmt.database;

import at.kaindorf.customermgmt.pojo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <h3>Created by IntelliJ IDEA.</h3><br>
 * <b>Project:</b> SpringRestCustomerMgmt<br>
 * <b>User:</b> Simon Schoeggler<br>
 * <b>Date:</b> 18. Jänner 2023<br>
 * <b>Time:</b> 09:25<br>
 */

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
