package lk.dhanushkaTa.travelApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class GuideDTO {
    private String guideId;
    private String guideDob;
    private String guideAddress;
    private String guideName;
    private double guideManDay_value;
    private String guideExperience;
    private String guideContact;
    private String guideEmail;
    private String guideGender;
    private String guideStatus;
//    private guideIDImage;
//    private guideNICImage;
//    private guideImage;
}
