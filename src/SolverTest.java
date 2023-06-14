import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SolverTest {

    public static final Tile one = new Tile("a-b-c-d", "e-f-g-h");
    private Tile two;
    private Tile ten;
    private Tile nine;
    private Tile eight;
    private Tile seven;
    private Tile six;
    private Tile five;
    private Tile four;
    private Tile three;
    private Tile eleven;
    private Tile twelve;
    private Tile thirteen;
    private Tile fourteen;
    private Tile fifteen;
    private Tile sixteen;
    private Tile seventeen;
    private Tile eighteen;
    private Tile nineteen;
    private Tile twenty;
    private Tile twentyone;
    private Tile twentytwo;
    private Tile twentythree;
    private Tile twentyfour;
    private Tile twentyfive;
    private Tile twentysix;
    private Tile twentyseven;
    private Tile twentyeight;
    private Tile twentynine;


    @BeforeEach
    void setUp() {
        Tile one = new Tile("one", "a-b-c-d", "e-f-g-h");
        two = tileThatSlotsInto(one);
        three = tileThatSlotsInto(two);
        four = tileThatSlotsInto(three);
        five = tileThatSlotsInto(four);
        six = tileThatSlotsInto(five);
        seven = tileThatSlotsInto(six);
        eight = tileThatSlotsInto(one.rotate());
        nine = tileThatSlotsInto(three.rotate(), "orange");
        ten = tileThatSlotsInto(five.rotate(), "purple");
        eleven = tileThatSlotsInto(seven.rotate(), "magenta");
        twelve = tileThatSlotsInto(eight);
        thirteen = tileThatSlotsInto(twelve.rotate(), "orange");
        fourteen = tileThatSlotsInto(thirteen, nine);
        fifteen = tileThatSlotsInto(fourteen.rotate(), "purple");
        sixteen = tileThatSlotsInto(fifteen, ten);
        seventeen = tileThatSlotsInto(sixteen.rotate(), "magenta");
        eighteen = tileThatSlotsInto(seventeen, eleven);
        nineteen = tileThatSlotsInto(twelve.rotate());
        twenty = tileThatSlotsInto(fourteen.rotate(), "mauve");
        twentyone = tileThatSlotsInto(sixteen.rotate(), "red");
        twentytwo = tileThatSlotsInto(eighteen.rotate(), "crimson");
        twentythree = tileThatSlotsInto(nineteen);
        twentyfour = tileThatSlotsInto(twentythree.rotate(), "mauve");
        twentyfive = tileThatSlotsInto(twentyfour, twenty);
        twentysix = tileThatSlotsInto(twentyfive.rotate(), "red");
        twentyseven = tileThatSlotsInto(twentysix, twentyone);
        twentyeight = tileThatSlotsInto(twentyseven.rotate(), "crimson");
        twentynine = tileThatSlotsInto(twentyeight, twentytwo);
    }

    @Test
    public void two_tile_chain_cant_be_identified() {
        Tile one = new Tile("a-b-c-d", "e-f-g-h");
        Tile two = new Tile("e-f-g-h", "i-j-k-l");
        assertNull(new Solver(List.of(one, two)).solve());
    }

    @Test
    public void two_tile_chain_can_be_identified() {
        assertNotNull(Tile.slotsInto(one, two));
        assertEquals(List.of(one, two), new Solver(List.of(one, two)).solve());
    }

    @Test
    public void three_tile_chain_can_be_identified() {
        assertNotNull(Tile.slotsInto(one, two));
        assertNotNull(Tile.slotsInto(two, three));
        assertEquals(List.of(one, two, three), new Solver(List.of(one, two, three)).solve());
    }

    @Test
    public void three_tile_chain_can_be_identified_when_input_order_not_result_order() {
        assertNotNull(Tile.slotsInto(one, two));
        assertNotNull(Tile.slotsInto(two, three));
        assertEquals(List.of(one, two, three), new Solver(List.of(two, one, three)).solve());
    }

    @Test
    public void seven_tile_chain_can_be_identified_when_input_order_not_result_order() {
        List<Tile> expectedOutput = List.of(one, two, three, four, five, six, seven);
        List<Tile> input = reverseAndRoll(expectedOutput);
        assertNotNull(new Solver(input).solve());
    }

    @Test
    public void eight_tile_chain_across_two_rows_can_be_identified_when_input_order_not_result_order() {
        List<Tile> expectedOutput = List.of(one, two, three, four, five, six, seven, eight);
        List<Tile> input = reverseAndRoll(expectedOutput);
        assertEquals(expectedOutput, new Solver(input).solve());
    }

    @Test
    public void nine_tile_chain_across_two_rows_can_be_identified_when_input_order_not_result_order() {
        List<Tile> expectedOutput = List.of(one, two, three, four, five, six, seven, eight, nine);
        List<Tile> input = reverseAndRoll(expectedOutput);
        assertEquals(expectedOutput, new Solver(input).solve());
    }

    @Test
    public void ten_tile_chain_across_two_rows_can_be_identified_when_input_order_not_result_order() {
        List<Tile> expectedOutput = List.of(one, two, three, four, five, six, seven, eight, nine, ten);
        List<Tile> input = reverseAndRoll(expectedOutput);
        assertEquals(expectedOutput, new Solver(input).solve());
    }

    @Test
    public void eleven_tile_chain_across_two_rows_can_be_identified_when_input_order_not_result_order() {
        List<Tile> expectedOutput = List.of(one, two, three, four, five, six, seven, eight, nine, ten, eleven);
        List<Tile> input = reverseAndRoll(expectedOutput);
        assertEquals(expectedOutput, new Solver(input).solve());
    }

    @Test
    public void twelve_tile_chain_across_three_rows_can_be_identified_when_input_order_not_result_order() {
        List<Tile> expectedOutput = List.of(one, two, three, four, five, six, seven, eight, nine, ten, eleven, twelve);
        List<Tile> input = reverseAndRoll(expectedOutput);
        assertEquals(expectedOutput, new Solver(input).solve());
    }

    @Test
    public void thirteen_tile_chain_across_three_rows_can_be_identified_when_input_order_not_result_order() {
        List<Tile> expectedOutput = chainOfSize(13);
        List<Tile> input = reverseAndRoll(expectedOutput);
        assertEquals(expectedOutput, new Solver(input).solve());
    }

    @Test
    public void fourteen_tile_chain_across_three_rows_and_two_comparisons_cannot_be_identified_when_one_comparison_fails() {
        var expectedOutput = List.of(
                one, two, three, four, five, six, seven, eight, nine, ten, eleven, twelve, thirteen, tileThatSlotsInto(thirteen, "green"));
        List<Tile> input = reverseAndRoll(expectedOutput);
        Object foo = new Solver(input).solve();
        assertNull(foo);
    }

    @Test
    public void fourteen_tile_chain_across_three_rows_and_two_comparisons_can_be_identified_when_input_order_not_result_order() {
        List<Tile> expectedOutput = chainOfSize(14);
        List<Tile> input = reverseAndRoll(expectedOutput);
        assertEquals(expectedOutput, new Solver(input).solve());
    }


    @Test
    public void fifteen_tile_chain_across_three_rows_and_two_comparisons_can_be_identified_when_input_order_not_result_order() {
        List<Tile> expectedOutput = chainOfSize(15);
        List<Tile> input = reverseAndRoll(expectedOutput);
        assertEquals(expectedOutput, new Solver(input).solve());
    }

    @Test
    public void seventeen_tile_chain_across_three_rows_and_two_comparisons_can_be_identified_when_input_order_not_result_order() {
        List<Tile> expectedOutput = chainOfSize(17);
        List<Tile> input = reverseAndRoll(expectedOutput);
        assertEquals(expectedOutput, new Solver(input).solve());
    }

    @Test
    public void eighteen_tile_chain_across_three_rows_and_two_comparisons_can_be_identified_when_input_order_not_result_order() {
        List<Tile> expectedOutput = chainOfSize(18);
        List<Tile> input = reverseAndRoll(expectedOutput);
        assertEquals(expectedOutput, new Solver(input).solve());
    }

    @Test
    public void nineteen_tile_chain_across_three_rows_and_two_comparisons_can_be_identified_when_input_order_not_result_order() {
        List<Tile> expectedOutput = chainOfSize(19);
        List<Tile> input = reverseAndRoll(expectedOutput);
        assertEquals(expectedOutput, new Solver(input).solve());
    }

    @Test
    public void twenty_two_tile_chain_across_three_rows_and_two_comparisons_can_be_identified_when_input_order_not_result_order() {
        List<Tile> expectedOutput = chainOfSize(21);
        List<Tile> input = reverseAndRoll(expectedOutput);
        assertEquals(expectedOutput, new Solver(input).solve());
    }

    @Test
    public void twenty_nine_tile_chain_across_three_rows_and_two_comparisons_can_be_identified_when_input_order_not_result_order() {
        List<Tile> expectedOutput = chainOfSize(29);
        List<Tile> input = reverseAndRoll(expectedOutput);
        assertEquals(expectedOutput, new Solver(input).solve());
    }

    private List<Tile> chainOfSize(int i) {
        var master = List.of(
                one, two, three, four, five, six, seven, eight, nine, ten, eleven, twelve, thirteen, fourteen, fifteen, sixteen, seventeen, eighteen, nineteen,
                twenty, twentyone, twentytwo, twentythree, twentyfour, twentyfive, twentysix, twentyseven, twentyeight, twentynine);
        return master.subList(0, i);
    }


    private List<Tile> reverseAndRoll(List<Tile> expectedOutput) {
        var workingCopy = new ArrayList<Tile>();
        workingCopy.add(expectedOutput.get(expectedOutput.size() - 1));
        for (int i = expectedOutput.size() - 2; i >= 0; i--) {
            workingCopy.add(expectedOutput.get(i));

        }
        return workingCopy;
    }

    private Tile tileThatSlotsInto(Tile left, String controlColour) {
        String[] leftMainColours = left.getMainSideColours().split("-");
        String[] leftFlipColours = left.getFlipSideColours().split("-");
        return new Tile(leftFlipColours[2] + "-" + controlColour + "-" + controlColour + "-" + leftMainColours[1], leftMainColours[2] + "-" + controlColour + "-" + controlColour + "-" + leftFlipColours[1]);

    }

    private Tile tileThatSlotsInto(Tile left) {
        String[] leftMainColours = left.getMainSideColours().split("-");
        String[] leftFlipColours = left.getFlipSideColours().split("-");
        return new Tile(leftFlipColours[2] + "-" + random() + "-" + random() + "-" + leftMainColours[1], leftMainColours[2] + "-" + random() + "-" + random() + "-" + leftFlipColours[1]);
    }

    private Tile tileThatSlotsInto(Tile left, Tile right) {
        String[] leftMainColours = left.getMainSideColours().split("-");
        String[] leftFlipColours = left.getFlipSideColours().split("-");

        String[] rightMainColours = right.getMainSideColours().split("-");
        String[] rightFlipColours = right.getFlipSideColours().split("-");

        if (!leftMainColours[2].equals(rightFlipColours[1])) {
            throw new IllegalStateException("Corner doesn't match");
        }

        if (!leftMainColours[1].equals(rightFlipColours[2])) {
            throw new IllegalStateException("Corner doesn't match");
        }

        return new Tile(
                leftFlipColours[2] + "-" + random() + "-" + rightMainColours[1] + "-" + rightFlipColours[2],
                leftMainColours[2] + "-" + rightMainColours[2] + "-" + random() + "-" + leftFlipColours[1]);

    }

    private String random() {
        return RandomStringUtils.random(4, true, false);
    }
}