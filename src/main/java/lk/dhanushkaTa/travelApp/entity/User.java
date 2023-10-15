package lk.dhanushkaTa.travelApp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "user")
public class User {
    @Id
    private String userId;
    @Column(nullable = false)
    private String userFullName;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String userPassword;
    @Column(nullable = false,unique = true)
    private String userNic;
    @Column(nullable = false)
    private String userAddress;
}
