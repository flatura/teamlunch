package code.flatura.teamlunch.repository;

import code.flatura.teamlunch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * JavaBean used for communicate with datasource.
 * Has methods to manipulate User entities
 *
 * @author Dmitry Morozov for TeamLunch Graduation Project
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Transactional(readOnly = true)
    @Query("SELECT u FROM User u WHERE u.email=:email")
    User findByEmail(@Param("email") String email);

    User getByEmail(String toLowerCase);
}
