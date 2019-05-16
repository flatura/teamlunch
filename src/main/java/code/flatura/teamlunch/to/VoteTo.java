package code.flatura.teamlunch.to;

import code.flatura.teamlunch.model.Vote;

import java.io.Serializable;

/**
 * Transfer Object of class Vote.
 * Contains original Vote object and "new/rewritten" flag
 *
 * @author Dmitry Morozov for TeamLunch Graduation Project
 */
public class VoteTo implements Serializable {
    private final Vote vote;
    private final boolean fresh;

    public VoteTo(Vote vote, boolean fresh) {
        this.vote = vote;
        this.fresh = fresh;
    }

    public Vote getVote() {
        return vote;
    }

    public boolean justCreated() {
        return fresh;
    }

    @Override
    public String toString() {
        return "VoteTo{" +
                "vote=" + vote +
                ", fresh=" + fresh +
                '}';
    }
}
