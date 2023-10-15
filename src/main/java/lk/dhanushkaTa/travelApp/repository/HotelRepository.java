package lk.dhanushkaTa.travelApp.repository;

import lk.dhanushkaTa.travelApp.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel,String> {
}
