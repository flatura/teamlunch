package code.flatura.teamlunch.repository;

import code.flatura.teamlunch.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * JavaBean used for communicate with datasource.
 * Has methods to manipulate Vote entities
 *
 * @author Dmitry Morozov for TeamLunch Graduation Project
 */
@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {
    @Transactional(readOnly = true)
    @Query("SELECT v FROM Vote v WHERE v.user.id=:user_id AND v.date=:date")
    Optional<Vote> getByUserAndDate(@Param("user_id") Integer userId, @Param("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);

    @Transactional(readOnly = true)
    @Query("SELECT v from Vote v WHERE v.user.id=:user_id AND v.date BETWEEN :startDate AND :endDate ORDER BY v.date DESC")
    List<Vote> getVotesBetween(@Param("user_id") int userId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Override
    @Transactional
    Vote save(Vote vote);
}
