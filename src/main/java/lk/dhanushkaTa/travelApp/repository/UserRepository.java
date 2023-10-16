package lk.dhanushkaTa.travelApp.repository;

import lk.dhanushkaTa.travelApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    User findUserByUserIdOrUserNic(String userId,String userNic);

    List<User> findUserByUserIdLikeOrUserNicLike(String userId, String Nic);
}
