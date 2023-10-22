package lk.dhanushkaTa.travelApp.dto;

import lk.dhanushkaTa.travelApp.embeded.Contact;
import lk.dhanushkaTa.travelApp.embeded.RoomOption;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class HotelDTO {
    private String hotelId;
    private String hotelName;
    private String hotelCategory;
    private String hotelLocation;
    private String hotelGmapLocation;
    private String hotelEmail;
    private Contact hotelContact;
    private String hotelPetAllow;
    private String hotelCancellationCriteria;
    private RoomOption hotelRoomOpt;
    private byte[] hotelImage;

}
