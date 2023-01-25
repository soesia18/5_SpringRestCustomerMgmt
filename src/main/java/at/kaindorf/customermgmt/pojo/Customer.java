package at.kaindorf.customermgmt.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h3>Created by IntelliJ IDEA.</h3><br>
 * <b>Project:</b> SpringRestCustomerMgmt<br>
 * <b>User:</b> Simon Schoeggler<br>
 * <b>Date:</b> 18. Jänner 2023<br>
 * <b>Time:</b> 09:23<br>
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
    @Id
    private Long id;
    private String name;
    private String address;
    private Float sales;
}
