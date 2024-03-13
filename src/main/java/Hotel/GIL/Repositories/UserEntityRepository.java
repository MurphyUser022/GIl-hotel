package Hotel.GIL.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import Hotel.GIL.models.UserEntity;


public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {

    public UserEntity findByUserName(String name);
}
