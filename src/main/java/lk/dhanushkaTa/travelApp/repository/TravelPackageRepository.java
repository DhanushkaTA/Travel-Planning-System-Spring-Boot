package lk.dhanushkaTa.travelApp.repository;

import lk.dhanushkaTa.travelApp.entity.TravelPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelPackageRepository extends JpaRepository<TravelPackage,String> {

}
