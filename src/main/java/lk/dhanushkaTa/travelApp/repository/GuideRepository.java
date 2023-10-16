package lk.dhanushkaTa.travelApp.repository;

import lk.dhanushkaTa.travelApp.entity.Guide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuideRepository extends JpaRepository<Guide,String> {
}
