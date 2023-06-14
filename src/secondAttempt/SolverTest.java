package secondAttempt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static secondAttempt.TestInputs.DOCTORED;
import static secondAttempt.TestInputs.REAL;

class SolverTest {

    private Solver s;
    private List<Tile> result;

    @BeforeEach
    void setUp() {
        s = new Solver(REAL);
        result = s.solve();
    }

    @Test
    public void first_tile_placed_is_horizontal() {
        assertTrue(result.get(0) instanceof HorizontalTile);
    }

    @Test
    public void second_tile_placed_is_vertical_and_facing() {
        assertTrue(result.get(1) instanceof FacingVerticalTile);
    }

    @Test
    public void first_two_tiles_slot_together_with_doctored_data() {
        s = new Solver(DOCTORED);
        result = s.solve();

        assertNotNull(result);
        HorizontalTile horizontal = (HorizontalTile) result.get(0);
        FacingVerticalTile vertical = (FacingVerticalTile) result.get(1);
        assertNotNull(horizontal.slotsIntoEast(vertical));
    }

    @Test
    public void first_two_tiles_slot_together_with_real_data() {
        assertNotNull(result);
        HorizontalTile horizontal = (HorizontalTile) result.get(0);
        FacingVerticalTile vertical = (FacingVerticalTile) result.get(1);
        assertNotNull(horizontal.slotsIntoEast(vertical));
    }

    @Test
    public void first_two_tiles_slot_together_if_rotation_is_required() {
        List<UnfixedTile> needsRotation = List.of(
                new UnfixedTile("white-red-white-purple", "black-red-green-yellow"),
                new UnfixedTile("black-green-red-white", "red-green-blue-orange")
        );
        s = new Solver(needsRotation);
        result = s.solve();

        assertNotNull(result);
        HorizontalTile horizontal = (HorizontalTile) result.get(0);
        FacingVerticalTile vertical = (FacingVerticalTile) result.get(1);
        assertNotNull(horizontal.slotsIntoEast(vertical));
    }

    @Test
    public void first_two_tiles_slot_together_if_rotation_and_flip_is_required() {
        List<UnfixedTile> needsRotation = List.of(
                new UnfixedTile("black-white-yellow-purple", "yellow-orange-blue-purple"),
                new UnfixedTile("blue-yellow-green-white", "yellow-red-white-orange")
        );
        s = new Solver(needsRotation);
        result = s.solve();

        assertNotNull(result);
        HorizontalTile horizontal = (HorizontalTile) result.get(0);
        FacingVerticalTile vertical = (FacingVerticalTile) result.get(1);
        for (Tile t : result) System.out.println(t);
        assertNotNull(horizontal.slotsIntoEast(vertical));
    }

    @Test
    public void third_tile_placed_is_horizontal() {
        assertTrue(result.get(2) instanceof HorizontalTile);
    }

    @Test
    public void second_two_tiles_slot_together_with_real_data() {
        assertNotNull(result);
        FacingVerticalTile vertical = (FacingVerticalTile) result.get(1);
        HorizontalTile horizontal = (HorizontalTile) result.get(2);
        assertNotNull(vertical.slotsIntoEast(horizontal));
    }

    @Test
    public void second_two_tiles_slot_together_with_doctored_data() {
        s = new Solver(DOCTORED);
        result = s.solve();

        assertNotNull(result);
        FacingVerticalTile vertical = (FacingVerticalTile) result.get(1);
        HorizontalTile horizontal = (HorizontalTile) result.get(2);
        assertNotNull(vertical.slotsIntoEast(horizontal));
    }


    @Test
    public void third_two_tiles_slot_together_with_real_data() {
        assertNotNull(result);
        HorizontalTile horizontal = (HorizontalTile) result.get(2);
        FacingVerticalTile vertical = (FacingVerticalTile) result.get(3);
        assertNotNull(horizontal.slotsIntoEast(vertical));
    }

    @Test
    public void fourth_two_tiles_slot_together_with_real_data() {
        assertNotNull(result);
        FacingVerticalTile vertical = (FacingVerticalTile) result.get(3);
        HorizontalTile horizontal = (HorizontalTile) result.get(4);
        assertNotNull(vertical.slotsIntoEast(horizontal));
    }

    @Test
    public void fifth_two_tiles_slot_together_with_real_data() {
        assertNotNull(result);
        HorizontalTile horizontal = (HorizontalTile) result.get(4);
        FacingVerticalTile vertical = (FacingVerticalTile) result.get(5);
        assertNotNull(horizontal.slotsIntoEast(vertical));
    }

    @Test
    public void fifth_two_tiles_slot_together_with_doctored_data() {
        s = new Solver(DOCTORED);
        result = s.solve();

        assertNotNull(result);
        HorizontalTile horizontal = (HorizontalTile) result.get(4);
        FacingVerticalTile vertical = (FacingVerticalTile) result.get(5);
        assertNotNull(horizontal.slotsIntoEast(vertical));
    }

    @Test
    public void last_two_tiles_in_first_row_slot_together_with_real_data() {
        assertNotNull(result);
        FacingVerticalTile vertical = (FacingVerticalTile) result.get(5);
        HorizontalTile horizontal = (HorizontalTile) result.get(6);
        assertNotNull(vertical.slotsIntoEast(horizontal));
    }

    @Test
    public void first_tile_in_second_row_slots_together_with_first_tile_in_first_row() {
        assertNotNull(result);
        HorizontalTile horizontal = (HorizontalTile) result.get(0);
        SideVerticalTile vertical = (SideVerticalTile) result.get(7);
        assertNotNull(horizontal.slotsIntoSouth(vertical));
    }

    @Test
    public void second_tile_in_second_row_slots_together_with_third_tile_in_first_row() {
        assertNotNull(result);
        HorizontalTile horizontal = (HorizontalTile) result.get(2);
        SideVerticalTile vertical = (SideVerticalTile) result.get(8);
        assertNotNull(horizontal.slotsIntoSouth(vertical));
    }

    @Test
    public void third_tile_in_second_row_slots_together_with_fifth_tile_in_first_row() {
        assertNotNull(result);
        HorizontalTile horizontal = (HorizontalTile) result.get(4);
        SideVerticalTile vertical = (SideVerticalTile) result.get(9);
        assertNotNull(horizontal.slotsIntoSouth(vertical));
    }
    @Test
    public void last_tile_in_second_row_slots_together_with_last_tile_in_first_row() {
        assertNotNull(result);
        HorizontalTile horizontal = (HorizontalTile) result.get(6);
        SideVerticalTile vertical = (SideVerticalTile) result.get(10);
        assertNotNull(horizontal.slotsIntoSouth(vertical));
    }

    @Test
    public void first_tile_in_third_row_slots_together_with_first_tile_in_second_row() {
        assertNotNull(result);
        SideVerticalTile vertical = (SideVerticalTile) result.get(7);
        HorizontalTile horizontal = (HorizontalTile) result.get(11);
        assertNotNull(vertical.slotsIntoSouth(horizontal));
    }

    @Test
    public void second_tile_in_third_row_slots_together_with_first_tile_in_third_row() {
        assertNotNull(result);
        HorizontalTile horizontal = (HorizontalTile) result.get(11);
        FacingVerticalTile vertical = (FacingVerticalTile) result.get(12);
        assertNotNull(horizontal.slotsIntoEast(vertical));
    }

    @Test
    public void third_tile_in_third_row_slots_together_with_second_tile_in_third_row_and_second_tile_int_second_row() {
        assertNotNull(result);
        FacingVerticalTile facingVertical = (FacingVerticalTile) result.get(12);
        SideVerticalTile sideVertical = (SideVerticalTile) result.get(8);
        HorizontalTile horizontal = (HorizontalTile) result.get(13);

        assertNotNull(facingVertical.slotsIntoEast(horizontal));
        assertNotNull(sideVertical.slotsIntoSouth(horizontal));
    }
    @Test
    public void fourth_tile_in_third_row_slots_together_with_third_tile_in_third_row() {
        assertNotNull(result);
        HorizontalTile horizontal = (HorizontalTile) result.get(13);
        FacingVerticalTile vertical = (FacingVerticalTile) result.get(14);
        assertNotNull(horizontal.slotsIntoEast(vertical));
    }

    @Test
    public void fifth_tile_in_third_row_slots_together_with_fourth_tile_in_third_row_and_third_tile_int_second_row() {
        assertNotNull(result);
        FacingVerticalTile facingVertical = (FacingVerticalTile) result.get(14);
        SideVerticalTile sideVertical = (SideVerticalTile) result.get(9);
        HorizontalTile horizontal = (HorizontalTile) result.get(15);

        assertNotNull(facingVertical.slotsIntoEast(horizontal));
        assertNotNull(sideVertical.slotsIntoSouth(horizontal));
    }
    @Test
    public void sixth_tile_in_third_row_slots_together_with_fifth_tile_in_third_row() {
        assertNotNull(result);
        HorizontalTile horizontal = (HorizontalTile) result.get(15);
        FacingVerticalTile vertical = (FacingVerticalTile) result.get(16);
        assertNotNull(horizontal.slotsIntoEast(vertical));
    }

    @Test
    public void last_tile_in_third_row_slots_together_with_third_tile_in_third_row_and_last_tile_int_second_row() {
        assertNotNull(result);
        FacingVerticalTile facingVertical = (FacingVerticalTile) result.get(16);
        SideVerticalTile sideVertical = (SideVerticalTile) result.get(10);
        HorizontalTile horizontal = (HorizontalTile) result.get(17);

        assertNotNull(facingVertical.slotsIntoEast(horizontal));
        assertNotNull(sideVertical.slotsIntoSouth(horizontal));
    }

    @Test
    public void first_tile_in_fourth_row_slots_together_with_first_tile_in_third_row() {
        assertNotNull(result);
        HorizontalTile horizontal = (HorizontalTile) result.get(11);
        SideVerticalTile vertical = (SideVerticalTile) result.get(18);
        assertNotNull(horizontal.slotsIntoSouth(vertical));
    }
    @Test
    public void second_tile_in_row_4_slots_together_with_third_tile_in_row_3() {
        assertNotNull(result);
        HorizontalTile horizontal = (HorizontalTile) result.get(13);
        SideVerticalTile vertical = (SideVerticalTile) result.get(19);
        assertNotNull(horizontal.slotsIntoSouth(vertical));
    }

    @Test
    public void third_tile_in_row_4_slots_together_with_fifth_tile_in_row_3() {
        assertNotNull(result);
        HorizontalTile horizontal = (HorizontalTile) result.get(15);
        SideVerticalTile vertical = (SideVerticalTile) result.get(20);
        assertNotNull(horizontal.slotsIntoSouth(vertical));
    }
    @Test
    public void last_tile_in_fourth_row_slots_together_with_last_tile_in_third_row() {
        assertNotNull(result);
        HorizontalTile horizontal = (HorizontalTile) result.get(17);
        SideVerticalTile vertical = (SideVerticalTile) result.get(21);
        assertNotNull(horizontal.slotsIntoSouth(vertical));
    }

    @Test
    public void first_tile_in_fifth_row_slots_together_with_first_tile_in_fourth_row() {
        assertNotNull(result);
        SideVerticalTile vertical = (SideVerticalTile) result.get(18);
        HorizontalTile horizontal = (HorizontalTile) result.get(22);
        assertNotNull(vertical.slotsIntoSouth(horizontal));
    }

    @Test
    public void second_tile_in_fifth_row_slots_together_with_first_tile_in_fifth_row() {
        assertNotNull(result);
        HorizontalTile horizontal = (HorizontalTile) result.get(22);
        FacingVerticalTile vertical = (FacingVerticalTile) result.get(23);
        assertNotNull(horizontal.slotsIntoEast(vertical));
    }

    @Test
    public void third_tile_in_fifth_row_slots_together_with_second_tile_in_fifth_row_and_second_tile_int_fourth_row() {
        assertNotNull(result);
        FacingVerticalTile facingVertical = (FacingVerticalTile) result.get(23);
        SideVerticalTile sideVertical = (SideVerticalTile) result.get(19);
        HorizontalTile horizontal = (HorizontalTile) result.get(24);

        assertNotNull(facingVertical.slotsIntoEast(horizontal));
        assertNotNull(sideVertical.slotsIntoSouth(horizontal));
    }
    @Test
    public void fourth_tile_in_fifth_row_slots_together_with_third_tile_in_fifth_row() {
        assertNotNull(result);
        HorizontalTile horizontal = (HorizontalTile) result.get(24);
        FacingVerticalTile vertical = (FacingVerticalTile) result.get(25);
        assertNotNull(horizontal.slotsIntoEast(vertical));
    }

    @Test
    public void fifth_tile_in_fifth_row_slots_together_with_fourth_tile_in_fifth_row_and_third_tile_int_fourth_row() {
        assertNotNull(result);
        FacingVerticalTile facingVertical = (FacingVerticalTile) result.get(25);
        SideVerticalTile sideVertical = (SideVerticalTile) result.get(20);
        HorizontalTile horizontal = (HorizontalTile) result.get(26);

        assertNotNull(facingVertical.slotsIntoEast(horizontal));
        assertNotNull(sideVertical.slotsIntoSouth(horizontal));
    }
    @Test
    public void sixth_tile_in_fifth_row_slots_together_with_fifth_tile_in_fifth_row() {
        assertNotNull(result);
        HorizontalTile horizontal = (HorizontalTile) result.get(26);
        FacingVerticalTile vertical = (FacingVerticalTile) result.get(27);
        assertNotNull(horizontal.slotsIntoEast(vertical));
    }

    @Test
    public void last_tile_in_fifth_row_slots_together_with_third_tile_in_fifth_row_and_last_tile_int_fourth_row() {
        assertNotNull(result);
        FacingVerticalTile facingVertical = (FacingVerticalTile) result.get(27);
        SideVerticalTile sideVertical = (SideVerticalTile) result.get(21);
        HorizontalTile horizontal = (HorizontalTile) result.get(28);

        assertNotNull(facingVertical.slotsIntoEast(horizontal));
        assertNotNull(sideVertical.slotsIntoSouth(horizontal));
    }


    @Test
    public void first_tile_in_sixth_row_slots_together_with_first_tile_in_fifth_row() {
        assertNotNull(result);
        HorizontalTile horizontal = (HorizontalTile) result.get(22);
        SideVerticalTile vertical = (SideVerticalTile) result.get(29);
        assertNotNull(horizontal.slotsIntoSouth(vertical));
    }
    @Test
    public void second_tile_in_sixth_row_slots_together_with_third_tile_in_fifth_row() {
        assertNotNull(result);
        HorizontalTile horizontal = (HorizontalTile) result.get(24);
        SideVerticalTile vertical = (SideVerticalTile) result.get(30);
        assertNotNull(horizontal.slotsIntoSouth(vertical));
    }

    @Test
    public void third_tile_in_sixth_row_slots_together_with_fifth_tile_in_fifth_row() {
        assertNotNull(result);
        HorizontalTile horizontal = (HorizontalTile) result.get(26);
        SideVerticalTile vertical = (SideVerticalTile) result.get(31);
        assertNotNull(horizontal.slotsIntoSouth(vertical));
    }
    @Test
    public void last_tile_in_sixth_row_slots_together_with_last_tile_in_third_row() {
        assertNotNull(result);
        HorizontalTile horizontal = (HorizontalTile) result.get(28);
        SideVerticalTile vertical = (SideVerticalTile) result.get(32);
        assertNotNull(horizontal.slotsIntoSouth(vertical));
    }

    @Test
    public void first_tile_in_seventh_row_slots_together_with_first_tile_in_sixth_row() {
        assertNotNull(result);
        SideVerticalTile vertical = (SideVerticalTile) result.get(29);
        HorizontalTile horizontal = (HorizontalTile) result.get(33);
        assertNotNull(vertical.slotsIntoSouth(horizontal));
    }

    @Test
    public void second_tile_in_seventh_row_slots_together_with_first_tile_in_seventh_row() {
        assertNotNull(result);
        HorizontalTile horizontal = (HorizontalTile) result.get(33);
        FacingVerticalTile vertical = (FacingVerticalTile) result.get(34);
        assertNotNull(horizontal.slotsIntoEast(vertical));
    }

    @Test
    public void third_tile_in_seventh_row_slots_together_with_second_tile_in_seventh_row_and_second_tile_int_sixth_row() {
        assertNotNull(result);
        FacingVerticalTile facingVertical = (FacingVerticalTile) result.get(34);
        SideVerticalTile sideVertical = (SideVerticalTile) result.get(30);
        HorizontalTile horizontal = (HorizontalTile) result.get(35);

        assertNotNull(facingVertical.slotsIntoEast(horizontal));
        assertNotNull(sideVertical.slotsIntoSouth(horizontal));
    }
    @Test
    public void fourth_tile_in_seventh_row_slots_together_with_third_tile_in_seventh_row() {
        assertNotNull(result);
        HorizontalTile horizontal = (HorizontalTile) result.get(35);
        FacingVerticalTile vertical = (FacingVerticalTile) result.get(36);
        assertNotNull(horizontal.slotsIntoEast(vertical));
    }

    @Test
    public void fifth_tile_in_seventh_row_slots_together_with_fourth_tile_in_seventh_row_and_third_tile_int_sixth_row() {
        assertNotNull(result);
        FacingVerticalTile facingVertical = (FacingVerticalTile) result.get(36);
        SideVerticalTile sideVertical = (SideVerticalTile) result.get(31);
        HorizontalTile horizontal = (HorizontalTile) result.get(37);

        assertNotNull(facingVertical.slotsIntoEast(horizontal));
        assertNotNull(sideVertical.slotsIntoSouth(horizontal));
    }
    @Test
    public void sixth_tile_in_seventh_row_slots_together_with_fifth_tile_in_seventh_row() {
        assertNotNull(result);
        HorizontalTile horizontal = (HorizontalTile) result.get(37);
        FacingVerticalTile vertical = (FacingVerticalTile) result.get(38);
        assertNotNull(horizontal.slotsIntoEast(vertical));
    }

    @Test
    public void last_tile_in_seventh_row_slots_together_with_third_tile_in_seventh_row_and_last_tile_int_sixth_row() {
        assertNotNull(result);
        FacingVerticalTile facingVertical = (FacingVerticalTile) result.get(38);
        SideVerticalTile sideVertical = (SideVerticalTile) result.get(32);
        HorizontalTile horizontal = (HorizontalTile) result.get(39);

        assertNotNull(facingVertical.slotsIntoEast(horizontal));
        assertNotNull(sideVertical.slotsIntoSouth(horizontal));
    }

}