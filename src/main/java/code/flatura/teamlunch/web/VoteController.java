package code.flatura.teamlunch.web;

import code.flatura.teamlunch.model.Restaurant;
import code.flatura.teamlunch.model.Vote;
import code.flatura.teamlunch.service.VoteService;
import code.flatura.teamlunch.to.VoteTo;
import code.flatura.teamlunch.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * JavaBean used for save the user vote.
 * Has a date, user and restaurant properties and also delegated id.
 *
 * @author Dmitry Morozov for TeamLunch Graduation Project
 */
@RestController
@RequestMapping(value = "/api/votes")
public class VoteController {
    private static final LocalTime VOTING_CLOSING_TIME = LocalTime.of(11, 0);

    private VoteService voteService;

    @Autowired
    public void setVoteService(VoteService voteService) {
        this.voteService = voteService;
    }

    /**
     * Show current vote if exists
     *
     * @return ResponseEntity with current vote
     */
    @RequestMapping(method = GET)
    public ResponseEntity<Restaurant> getVoteByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return voteService.getByUserAndDate(SecurityUtil.authUserId(), date)
                .map(v -> new ResponseEntity<>(v.getRestaurant(), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Accept a user vote for given restaurant ID if it's made before vote closing time.
     * Perform a request consistency check
     *
     * @return ResponseEntity with current vote with HTTP 200, 201
     */
    @RequestMapping(value = "/{id}", method = POST)
    public ResponseEntity<Restaurant> vote(@PathVariable("id") Integer restaurantId) {
        boolean acceptVote = LocalTime.now().isBefore(VOTING_CLOSING_TIME);
        Integer userId = SecurityUtil.authUserId();
        LocalDate localDate = LocalDate.now();
        VoteTo voteTo = acceptVote ? voteService.save(localDate, userId, restaurantId)
                : voteService.saveIfAbsent(userId, restaurantId);
        return new ResponseEntity<>(voteTo.getVote().getRestaurant(),
                voteTo.justCreated() ? HttpStatus.CREATED : (acceptVote ? HttpStatus.OK : HttpStatus.NOT_ACCEPTABLE));
    }

    @RequestMapping(value = "/history", method = GET)
    public List<Vote> getVotesBetween(
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        int userId = SecurityUtil.authUserId();
        return voteService.getVotesBetween(userId, startDate, endDate);
    }
}
