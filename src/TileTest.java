import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {

    @Test
    public void tiles_with_colour_match_only_in_quadrant_one_slot_together() {
        Tile left = new Tile("a-Q1-c-d", "e-f-g-h");
        Tile right = new Tile("i-j-k-Q1", "m-n-o-p");
        assertNull(Tile.slotsInto(left, right));
    }

    @Test
    public void tiles_with_colour_match_in_quadrant_one_and_two_slot_together() {
        Tile left = new Tile("a-Q1-c-d", "e-f-Q2-h");
        Tile right = new Tile("Q2-j-k-Q1", "m-n-o-p");
        assertNull(Tile.slotsInto(left, right));
    }

    @Test
    public void tiles_with_colour_match_in_quadrant_one_and_two_and_three_slot_together() {
        Tile left = new Tile("a-Q1-c-d", "e-Q3-Q2-h");
        Tile right = new Tile("Q2-j-k-Q1", "m-n-o-Q3");
        assertNull(Tile.slotsInto(left, right));
    }

    @Test
    public void tiles_with_colour_match_in_quadrant_one_and_two_and_three_and_four_slot_together() {
        Tile left = new Tile("a-Q1-Q4-d", "e-Q3-Q2-h");
        Tile right = new Tile("Q2-j-k-Q1", "Q4-n-o-Q3");
        assertNotNull(Tile.slotsInto(left, right));
    }

    @Test
    public void tiles_with_match_after_one_rotation_of_the_right_slot_together() {
        Tile left = new Tile("a-Q1-Q4-d", "e-Q3-Q2-h");
        Tile right = new Tile("j-k-Q1-Q2", "Q3-Q4-n-o");
        assertNotNull(Tile.slotsInto(left, right));
    }

    @Test
    public void tiles_with_match_after_two_rotations_of_the_right_slot_together() {
        Tile left = new Tile("a-Q1-Q4-d", "e-Q3-Q2-h");
        Tile right = new Tile("k-Q1-Q2-j", "o-Q3-Q4-n");
        assertNotNull(Tile.slotsInto(left, right));
    }

    @Test
    public void tiles_with_match_after_three_rotations_of_the_right_slot_together() {
        Tile left = new Tile("a-Q1-Q4-d", "e-Q3-Q2-h");
        Tile right = new Tile("Q1-Q2-j-k", "n-o-Q3-Q4");
        assertNotNull(Tile.slotsInto(left, right));
    }


    @Test
    public void tiles_with_match_after_one_rotation_of_the_left_slot_together() {
        Tile left = new Tile("Q1-Q4-d-a", "h-e-Q3-Q2");
        Tile right = new Tile("j-k-Q1-Q2", "Q3-Q4-n-o");
        assertNotNull(Tile.slotsInto(left, right));
    }

    @Test
    public void tiles_with_match_after_two_rotations_of_the_left_slot_together() {
        Tile left = new Tile("Q4-d-a-Q1", "Q2-h-e-Q3");
        Tile right = new Tile("j-k-Q1-Q2", "Q3-Q4-n-o");
        assertNotNull(Tile.slotsInto(left, right));
    }

    @Test
    public void tiles_with_match_after_three_rotations_of_the_left_slot_together() {
        Tile left = new Tile("d-a-Q1-Q4", "Q3-Q2-h-e");
        Tile right = new Tile("j-k-Q1-Q2", "Q3-Q4-n-o");
        assertNotNull(Tile.slotsInto(left, right));
    }

    @Test
    public void tiles_with_match_after_all_possible_rotations_slot_together() {
        Tile left = new Tile("a-Q1-Q4-d", "e-Q3-Q2-h");
        Tile right = new Tile("Q2-j-k-Q1", "Q4-n-o-Q3");
        assertNotNull(Tile.slotsInto(left, right));
    }

    @Test
    public void tiles_with_match_after_three_rotations_and_a_right_flip_slot_together() {
        Tile left = new Tile("a-Q1-Q4-d", "e-Q3-Q2-h");
        Tile right = new Tile("Q4-n-o-Q3", "Q2-j-k-Q1");
        assertNotNull(Tile.slotsInto(left, right));
    }

    @Test
    public void tiles_with_match_after_three_rotations_a_right_flip_and_another_rotation_slot_together() {
        Tile left = new Tile("a-Q1-Q4-d", "e-Q3-Q2-h");
        Tile right = new Tile("n-o-Q3-Q4", "Q1-Q2-j-k");
        assertNotNull(Tile.slotsInto(left, right));
    }

    @Test
    public void tiles_with_match_after_three_rotations_a_right_flip_and_another_two_rotations_slot_together() {
        Tile left = new Tile("a-Q1-Q4-d", "e-Q3-Q2-h");
        Tile right = new Tile("o-Q3-Q4-n", "k-Q1-Q2-j");
        assertNotNull(Tile.slotsInto(left, right));
    }

    @Test
    public void tiles_with_match_after_three_rotations_a_right_flip_and_another_three_rotations_slot_together() {
        Tile left = new Tile("a-Q1-Q4-d", "e-Q3-Q2-h");
        Tile right = new Tile("Q3-Q4-n-o", "j-k-Q1-Q2");
        assertNotNull(Tile.slotsInto(left, right));
    }

    @Test
    public void tiles_wwhich_require_one_rotation_of_the_left_do_not_slot_together_if_left_is_fixed() {
        Tile left = new Tile("Q1-Q4-d-a", "h-e-Q3-Q2");
        Tile right = new Tile("j-k-Q1-Q2", "Q3-Q4-n-o");
        assertNull(Tile.slotsIntoFixed(left, right));
    }

}