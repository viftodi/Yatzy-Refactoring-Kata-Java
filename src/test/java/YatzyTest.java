import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class YatzyTest {

    @Test
    public void chance_scores_sum_of_all_dice() {
        int expected = 15;
        int actual = Yatzy.chance(2,3,4,5,1);
        assertEquals(expected, actual);
        assertEquals(16, Yatzy.chance(3,3,4,5,1));
    }

    @Test public void yatzy_scores_50() {
        int expected = 50;
        int actual = Yatzy.yatzy(4,4,4,4,4);
        assertEquals(expected, actual);
        assertEquals(50, Yatzy.yatzy(6,6,6,6,6));
        assertEquals(0, Yatzy.yatzy(6,6,6,6,3));
    }

    @Test public void ones_counts_number_of_1s() {
        assertTrue(Yatzy.ones(1,2,3,4,5) == 1);
        assertEquals(2, Yatzy.ones(1,2,1,4,5));
        assertEquals(0, Yatzy.ones(6,2,2,4,5));
        assertEquals(4, Yatzy.ones(1,2,1,1,1));
    }

    @Test
    public void twos_counts_number_of_2s() {
        assertEquals(4, Yatzy.twos(1,2,3,2,6));
        assertEquals(10, Yatzy.twos(2,2,2,2,2));
    }

    @Test
    public void threes_counts_number_of_3s() {
        assertEquals(6, Yatzy.threes(1,2,3,2,3));
        assertEquals(12, Yatzy.threes(2,3,3,3,3));
    }

    @Test
    public void fours_counts_number_of_4s() 
    {
        assertEquals(12, new Yatzy(4,4,4,5,5).fours());
        assertEquals(8, new Yatzy(4,4,5,5,5).fours());
        assertEquals(4, new Yatzy(4,5,5,5,5).fours());
    }

    @Test
    public void fives_counts_number_of_5s() {
        assertEquals(10, new Yatzy(4,4,4,5,5).fives());
        assertEquals(15, new Yatzy(4,4,5,5,5).fives());
        assertEquals(20, new Yatzy(4,5,5,5,5).fives());
    }

    @Test
    public void sixes_counts_number_of_6s() {
        assertEquals(0, new Yatzy(4,4,4,5,5).sixes());
        assertEquals(6, new Yatzy(4,4,6,5,5).sixes());
        assertEquals(18, new Yatzy(6,5,6,6,5).sixes());
    }

    @Test
    public void pair_counts_highest_pair() {
        assertEquals(6, Yatzy.pair(3,4,3,5,6));
        assertEquals(10, Yatzy.pair(5,3,3,3,5));
        assertEquals(12, Yatzy.pair(5,3,6,6,5));
    }

    @Test
    public void twoPairs_counts_highest_two_pairs() {
        assertEquals(16, Yatzy.twoPairs(3,3,5,4,5));
        assertEquals(16, Yatzy.twoPairs(3,3,5,5,5));
    }

    @Test
    public void threeOfAKind_counts_number_repeated_3_times() 
    {
        assertEquals(9, Yatzy.threeOfAKind(3,3,3,4,5));
        assertEquals(15, Yatzy.threeOfAKind(5,3,5,4,5));
        assertEquals(9, Yatzy.threeOfAKind(3,3,3,3,5));
    }

    @Test
    public void fourOfAKind_counts_number_repeated_4_times() {
        assertEquals(12, Yatzy.fourOfAKind(3,3,3,3,5));
        assertEquals(20, Yatzy.fourOfAKind(5,5,5,4,5));
        assertEquals(9, Yatzy.threeOfAKind(3,3,3,3,3));
    }

    @Test
    public void smallStraight_verifies_that_numbers_are_sucessors_and_returns__the_sum() {
        assertEquals(15, Yatzy.smallStraight(1,2,3,4,5));
        assertEquals(15, Yatzy.smallStraight(2,3,4,5,1));
        assertEquals(0, Yatzy.smallStraight(1,2,2,4,5));
    }

    @Test
    public void largeStraight_verifies_the_straight_condition_and_returns__the_sum() {
        assertEquals(20, Yatzy.largeStraight(6,2,3,4,5));
        assertEquals(20, Yatzy.largeStraight(2,3,4,5,6));
        assertEquals(0, Yatzy.largeStraight(1,2,2,4,5));
    }

    @Test
    public void fullHouse_verifies_full_house_condition_and_returns_the_sum() {
        assertEquals(18, Yatzy.fullHouse(6,2,2,2,6));
        assertEquals(0, Yatzy.fullHouse(2,3,4,5,6));
    }
}
