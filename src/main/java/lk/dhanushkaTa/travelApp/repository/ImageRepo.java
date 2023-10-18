package lk.dhanushkaTa.travelApp.repository;

import lk.dhanushkaTa.travelApp.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepo extends JpaRepository<Image,String> {

    @Query(value = "SELECT * FROM Image ORDER BY id DESC 1",nativeQuery = true)
    Image getLastImage();


    Image getFirstByOrderByIdDesc();
}
