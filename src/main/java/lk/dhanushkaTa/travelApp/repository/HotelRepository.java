package lk.dhanushkaTa.travelApp.repository;

import lk.dhanushkaTa.travelApp.entity.Hotel;
import lk.dhanushkaTa.travelApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,String> {

    List<Hotel> findByHotelNameIsLikeIgnoreCaseOrderByHotelCategoryAsc(String hotelName);
    List<Hotel> findByHotelNameStartingWithOrderByHotelCategoryAsc(String hotelName);
    Hotel findByHotelName(String hotelName);
}
