package lk.dhanushkaTa.travelApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class UserDTO {
    private String userId;
    private String userFullName;
    private String username;
    private String userPassword;
    private String userNic;
    private String userAddress;
}
