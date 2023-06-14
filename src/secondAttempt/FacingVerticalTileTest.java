package secondAttempt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FacingVerticalTileTest {

    @Test
    public void vertical_tile_can_slot_into_horizontal_tile_in_east_slot() {
        HorizontalTile horizontal = new HorizontalTile(new UnfixedTile("green-blue-orange-red", "white-black-green-red"));
        FacingVerticalTile vertical = new FacingVerticalTile(new UnfixedTile("white-red-white-purple", "black-red-green-yellow"));
        assertNotNull(vertical.slotsIntoEast(horizontal));
    }
    @Test
    public void vertical_tile_cannot_slot_into_horizontal_tile_in_east_slot_if_colours_do_not_match() {
        HorizontalTile horizontal = new HorizontalTile(new UnfixedTile("blue-black-purple-green", "red-blue-white-purple"));
        FacingVerticalTile vertical = new FacingVerticalTile(new UnfixedTile("yellow-purple-black-white", "blue-purple-yellow-orange"));
        assertNull(vertical.slotsIntoEast(horizontal));
    }

}