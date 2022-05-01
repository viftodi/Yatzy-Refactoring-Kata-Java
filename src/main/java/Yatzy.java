import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Yatzy {

    private int[] diceRolls;

    private Yatzy(int[] diceRolls) {
        Objects.requireNonNull(diceRolls);
        if (diceRolls.length != 5) {
            throw new IllegalArgumentException("Required a dice roll of size 5"); // TODO use Guava preconditions?
        }
        for (int r : diceRolls) {
            if (r <= 0 || r > 6) {
                throw new IllegalArgumentException("Dice rolls must be between 1 and 6");
            }
        }
        this.diceRolls = diceRolls;
    }

    public static Yatzy of(int... diceRolls) {
        return new Yatzy(diceRolls);
    }

    public int chance() {
        return Arrays.stream(this.diceRolls).sum();
    }

    public int yatzy() {
        if (Arrays.stream(this.diceRolls).distinct().count() == 1) {
            return 50;
        } else {
            return 0;
        }
    }

    private int filterEqualToAndSum(int n) {
        return Arrays.stream(this.diceRolls).filter(i -> i == n).sum();
    }

    public int ones() {
        return filterEqualToAndSum(1);
    }

    public int twos() {
        return filterEqualToAndSum(2);
    }

    public int threes() {
        return filterEqualToAndSum(3);
    }

    public int fours() {
        return filterEqualToAndSum(4);
    }

    public int fives() {
        return filterEqualToAndSum(5);
    }

    public int sixes() {
        return filterEqualToAndSum(6);
    }

    public Map<Integer, List<Integer>> getReverseSortedGroupedItems() {
        return Arrays.stream(this.diceRolls).boxed()
                .collect(Collectors.groupingBy(Function.identity(),
                        () -> new TreeMap<Integer, List<Integer>>(Comparator.reverseOrder()),
                        Collectors.toList()));

    }

    public List<Integer> getGroupsSumLimitedBySize(Map<Integer, List<Integer>> groupedItems, int wantedSize) {
        return groupedItems.values().stream()
                .filter(items -> items.size() >= wantedSize)
                .limit(2)
                .map(items -> items.stream().limit(wantedSize).mapToInt(Integer::intValue).sum())
                .toList();
    }

    public int pair() {
        Map<Integer, List<Integer>> groupedItems = this.getReverseSortedGroupedItems();

        return getGroupsSumLimitedBySize(groupedItems, 2)
                .stream()
                .limit(1)
                .findFirst()
                .orElse(0);
    }

    public int twoPairs() {
        Map<Integer, List<Integer>> groupedItems = this.getReverseSortedGroupedItems();

        List<Integer> twoPairsSum = getGroupsSumLimitedBySize(groupedItems, 2);

        if (twoPairsSum.size() == 2) {
            return twoPairsSum.stream().mapToInt(Integer::intValue).sum();
        } else {
            return 0;
        }
    }

    public int threeOfAKind() {
        Map<Integer, List<Integer>> groupedItems = this.getReverseSortedGroupedItems();

        List<Integer> threeOfAKindSum = getGroupsSumLimitedBySize(groupedItems, 3);

        return threeOfAKindSum.stream().findFirst().orElse(0);
    }

    public int fourOfAKind() {
        Map<Integer, List<Integer>> groupedItems = this.getReverseSortedGroupedItems();

        List<Integer> threeOfAKindSum = getGroupsSumLimitedBySize(groupedItems, 4);

        return threeOfAKindSum.stream().findFirst().orElse(0);
    }

    private int getSumIfStrictlyOrderedUntilLimit(int limit) {
        // TODO use guava for sorted check
        boolean isSorted = true;
        for (int i = 1; i < limit; i++) {
            if (this.diceRolls[i] <= this.diceRolls[i - 1]) {
                isSorted = false;
                break;
            }
        }
        if (isSorted) {
            return Arrays.stream(this.diceRolls).sum();
        } else {
            return 0;
        }
    }

    // TODO verify what small/large straight are actually supposed to do
    // Compared to previous code; explanation in article and official Yahtzee rules?
    public int smallStraight() {
        return getSumIfStrictlyOrderedUntilLimit(4);
    }

    public int largeStraight() {
        return getSumIfStrictlyOrderedUntilLimit(5);
    }

    public int fullHouse() {
        Map<Integer, List<Integer>> groupedItems = this.getReverseSortedGroupedItems();
        List<List<Integer>> valuesAsList = groupedItems.values().stream().toList();
        if (valuesAsList.size() == 2 // Sum of size 5 means either group of 2 3 or group of 3 2
                && ((valuesAsList.get(0).size() + valuesAsList.get(1).size()) == 5)) {
            return groupedItems.values().stream().flatMap(List::stream).mapToInt(Integer::intValue).sum();
        } else {
            return 0;
        }
    }
}
