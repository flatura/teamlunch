package code.flatura.teamlunch.service;

import code.flatura.teamlunch.model.Restaurant;
import code.flatura.teamlunch.model.User;
import code.flatura.teamlunch.model.Vote;
import code.flatura.teamlunch.repository.RestaurantRepository;
import code.flatura.teamlunch.repository.UserRepository;
import code.flatura.teamlunch.repository.VoteRepository;
import code.flatura.teamlunch.to.VoteTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Service implements business logic of voting process.
 *
 * @author Dmitry Morozov for TeamLunch Graduation Project
 */
@Service
public class VoteService {
    private VoteRepository voteRepository;
    private UserRepository userRepository;
    private RestaurantRepository restaurantRepository;

    @Autowired
    public VoteService(VoteRepository voteRepository, UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    /**
     * Get user's vote on the given date
     *
     * @param userId
     * @param date
     * @return
     */
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public Optional<Vote> getByUserAndDate(Integer userId, LocalDate date) {
        return voteRepository.getByUserAndDate(userId, date);
    }

    /**
     * Save a new custom date vote for given restaurant or update if exists
     *
     * @param date         custom LocalDate
     * @param userId
     * @param restaurantId
     * @return Vote TO
     */
    @Transactional
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public VoteTo save(LocalDate date, Integer userId, Integer restaurantId) {
        Restaurant restaurant = restaurantRepository.getOne(restaurantId);
        User user = userRepository.getOne(userId);

        VoteTo voteTo = voteRepository.getByUserAndDate(userId, date)
                .map(v -> {
                    v.setRestaurant(restaurant);
                    return new VoteTo(v, false);
                }).orElseGet(() -> new VoteTo(
                        new Vote(date, user, restaurant), true)
                );
        voteRepository.save(voteTo.getVote());
        return voteTo;
    }

    /**
     * Save a new today vote for given restaurant only if it doesn't exist
     *
     * @param userId
     * @param restaurantId
     * @return Vote TO
     */
    @Transactional
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public VoteTo saveIfAbsent(Integer userId, Integer restaurantId) {
        LocalDate localDate = LocalDate.now();
        return voteRepository.getByUserAndDate(userId, localDate)
                .map(v -> new VoteTo(v, false))
                .orElseGet(() -> new VoteTo(
                        voteRepository.save(
                                new Vote(
                                        localDate,
                                        userRepository.getOne(userId),
                                        restaurantRepository.getOne(restaurantId)))
                        , true)
                );
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public List<Vote> getVotesBetween(int userId, LocalDate startDate, LocalDate endDate) {
        return voteRepository.getVotesBetween(userId,
                startDate != null ? startDate : LocalDate.of(2000, 1, 1),
                endDate != null ? endDate : LocalDate.of(2100, 1, 1));
    }
}