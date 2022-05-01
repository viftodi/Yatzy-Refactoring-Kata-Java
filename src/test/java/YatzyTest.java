import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class YatzyTest {

    @Test
    public void test_of_rejects_null_argument() {
        assertThatThrownBy(() -> Yatzy.of(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void test_of_requires_5_rolls() {
        assertThatThrownBy(() -> Yatzy.of(1, 2, 3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Required a dice roll of size 5");
    }

    @Test
    public void test_of_handles_invalid_rolls() {
        assertThatThrownBy(() -> Yatzy.of(1, 8, -1, 3, 4))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Dice rolls must be between 1 and 6");
    }

    @Test
    public void chance_scores_sum_of_all_dice() {
        var game1 = Yatzy.of(2, 3, 4, 5, 1);
        assertThat(game1.chance()).isEqualTo(15);
        var game2 = Yatzy.of(3, 3, 4, 5, 1);
        assertThat(game2.chance()).isEqualTo(16);
        var game3 = Yatzy.of(1, 1, 3, 3, 6);
        assertThat(game3.chance()).isEqualTo(14);
        var game4 = Yatzy.of(4, 5, 5, 6, 1);
        assertThat(game4.chance()).isEqualTo(21);
    }

    @Test
    public void yatzy_scores_50_when_all_numbers_the_same() {
        var game1 = Yatzy.of(6, 6, 6, 6, 6);
        assertThat(game1.yatzy()).isEqualTo(50);
        var game2 = Yatzy.of(6, 6, 6, 6, 3);
        assertThat(game2.yatzy()).isEqualTo(0);
    }

    @Test
    public void ones_counts_number_of_1s() {
        var game1 = Yatzy.of(1, 2, 3, 4, 5);
        assertThat(game1.ones()).isEqualTo(1);
        var game2 = Yatzy.of(1, 2, 1, 4, 5);
        assertThat(game2.ones()).isEqualTo(2);
        var game3 = Yatzy.of(6, 2, 2, 4, 5);
        assertThat(game3.ones()).isEqualTo(0);
        var game4 = Yatzy.of(3, 3, 3, 4, 5);
        assertThat(game4.ones()).isEqualTo(0);
        var game5 = Yatzy.of(1, 2, 1, 1, 1);
        assertThat(game5.ones()).isEqualTo(4);
    }

    @Test
    public void twos_counts_number_of_2s() {
        var game1 = Yatzy.of(1, 2, 3, 2, 6);
        assertThat(game1.twos()).isEqualTo(4);
        var game2 = Yatzy.of(2, 2, 2, 2, 2);
        assertThat(game2.twos()).isEqualTo(10);
        var game3 = Yatzy.of(2, 3, 2, 5, 1);
        assertThat(game3.twos()).isEqualTo(4);
    }

    @Test
    public void threes_counts_number_of_3s() {
        var game1 = Yatzy.of(1, 2, 3, 2, 3);
        assertThat(game1.threes()).isEqualTo(6);
        var game2 = Yatzy.of(2, 3, 3, 3, 3);
        assertThat(game2.threes()).isEqualTo(12);
    }

    @Test
    public void fours_counts_number_of_4s() {
        var game1 = Yatzy.of(4, 4, 4, 5, 5);
        assertThat(game1.fours()).isEqualTo(12);
        var game2 = Yatzy.of(4, 4, 5, 5, 5);
        assertThat(game2.fours()).isEqualTo(8);
        var game3 = Yatzy.of(4, 5, 5, 5, 5);
        assertThat(game3.fours()).isEqualTo(4);
    }

    @Test
    public void fives_counts_number_of_5s() {
        var game1 = Yatzy.of(4, 4, 4, 5, 5);
        assertThat(game1.fives()).isEqualTo(10);
        var game2 = Yatzy.of(4, 4, 5, 5, 5);
        assertThat(game2.fives()).isEqualTo(15);
        var game3 = Yatzy.of(4, 5, 5, 5, 5);
        assertThat(game3.fives()).isEqualTo(20);
    }

    @Test
    public void sixes_counts_number_of_6s() {
        var game1 = Yatzy.of(4, 4, 4, 5, 5);
        assertThat(game1.sixes()).isEqualTo(0);
        var game2 = Yatzy.of(4, 4, 6, 5, 5);
        assertThat(game2.sixes()).isEqualTo(6);
        var game3 = Yatzy.of(6, 5, 6, 6, 5);
        assertThat(game3.sixes()).isEqualTo(18);
    }

    @Test
    public void pair_counts_highest_pair() {
        var game1 = Yatzy.of(1, 2, 3, 4, 5);
        assertThat(game1.pair()).isEqualTo(0);
        var game2 = Yatzy.of(3, 4, 3, 5, 6);
        assertThat(game2.pair()).isEqualTo(6);
        var game3 = Yatzy.of(5, 3, 3, 3, 5);
        assertThat(game3.pair()).isEqualTo(10);
        var game4 = Yatzy.of(5, 3, 6, 6, 5);
        assertThat(game4.pair()).isEqualTo(12);
        var game5 = Yatzy.of(3, 3, 3, 3, 1);
        assertThat(game5.pair()).isEqualTo(6);
    }

    @Test
    public void twoPairs_counts_highest_two_pairs() {
        var game1 = Yatzy.of(1, 1, 2, 3, 3);
        assertThat(game1.twoPairs()).isEqualTo(8);
        var game2 = Yatzy.of(1, 1, 2, 3, 4);
        assertThat(game2.twoPairs()).isEqualTo(0);
        var game3 = Yatzy.of(1, 1, 2, 2, 2);
        assertThat(game3.twoPairs()).isEqualTo(6);
        var game4 = Yatzy.of(3, 3, 3, 3, 1);
        assertThat(game4.twoPairs()).isEqualTo(0);
        var game5 = Yatzy.of(3, 3, 5, 4, 5);
        assertThat(game5.twoPairs()).isEqualTo(16);
        var game6 = Yatzy.of(3, 3, 5, 5, 5);
        assertThat(game6.twoPairs()).isEqualTo(16);
    }

    @Test
    public void threeOfAKind_counts_number_repeated_3_times() {
        var game1 = Yatzy.of(3, 3, 3, 4, 5);
        assertThat(game1.threeOfAKind()).isEqualTo(9);
        var game2 = Yatzy.of(3, 3, 4, 5, 6);
        assertThat(game2.threeOfAKind()).isEqualTo(0);
        var game3 = Yatzy.of(3, 3, 3, 3, 1);
        assertThat(game3.threeOfAKind()).isEqualTo(9);
        var game4 = Yatzy.of(3, 3, 3, 4, 5);
        assertThat(game4.threeOfAKind()).isEqualTo(9);
        var game5 = Yatzy.of(5, 3, 5, 4, 5);
        assertThat(game5.threeOfAKind()).isEqualTo(15);
        var game6 = Yatzy.of(3, 3, 3, 3, 5);
        assertThat(game6.threeOfAKind()).isEqualTo(9);
    }

    @Test
    public void fourOfAKind_counts_number_repeated_4_times() {
        var game1 = Yatzy.of(2, 2, 2, 2, 5);
        assertThat(game1.fourOfAKind()).isEqualTo(8);
        var game2 = Yatzy.of(2, 2, 2, 5, 5);
        assertThat(game2.fourOfAKind()).isEqualTo(0);
        var game3 = Yatzy.of(2, 2, 2, 2, 2);
        assertThat(game3.fourOfAKind()).isEqualTo(8);
        var game4 = Yatzy.of(3, 3, 3, 3, 5);
        assertThat(game4.fourOfAKind()).isEqualTo(12);
        var game5 = Yatzy.of(5, 5, 5, 4, 5);
        assertThat(game5.fourOfAKind()).isEqualTo(20);
    }

    @Test
    public void smallStraight_verifies_that_numbers_are_sucessors_and_returns__the_sum() {
        var game1 = Yatzy.of(1, 2, 3, 4, 5);
        assertThat(game1.smallStraight()).isEqualTo(15);
        var game2 = Yatzy.of(2, 3, 4, 5, 1);
        assertThat(game2.smallStraight()).isEqualTo(15);
        var game3 = Yatzy.of(1, 2, 2, 4, 5);
        assertThat(game3.smallStraight()).isEqualTo(0);
    }

    @Test
    public void largeStraight_verifies_the_straight_condition_and_returns__the_sum() {
        // TODO verify if this is actually wanted behavior
        // var game1 = Yatzy.of(6,2,3,4,5);
        // assertThat(game1.largeStraight()).isEqualTo(20);
        var game2 = Yatzy.of(2, 3, 4, 5, 6);
        assertThat(game2.largeStraight()).isEqualTo(20);
        var game3 = Yatzy.of(1, 2, 2, 4, 5);
        assertThat(game3.largeStraight()).isEqualTo(0);
    }

    @Test
    public void fullHouse_verifies_full_house_condition_and_returns_the_sum() {
        var game1 = Yatzy.of(1, 1, 2, 2, 2);
        assertThat(game1.fullHouse()).isEqualTo(8);
        var game2 = Yatzy.of(2, 2, 3, 3, 4);
        assertThat(game2.fullHouse()).isEqualTo(0);
        var game3 = Yatzy.of(4, 4, 4, 4, 4);
        assertThat(game3.fullHouse()).isEqualTo(0);
        var game4 = Yatzy.of(6, 2, 2, 2, 6);
        assertThat(game4.fullHouse()).isEqualTo(18);
        var game5 = Yatzy.of(2, 3, 4, 5, 6);
        assertThat(game5.fullHouse()).isEqualTo(0);
    }

}
