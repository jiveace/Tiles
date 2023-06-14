package secondAttempt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HorizontalTileTest {

    @Test
    public void horizontal_tile_can_slot_into_vertical_tile_in_east_slot() {
        HorizontalTile horizontal = new HorizontalTile(new UnfixedTile("white-red-white-purple", "black-red-green-yellow"));
        FacingVerticalTile vertical = new FacingVerticalTile(new UnfixedTile("white-black-green-red", "green-blue-orange-red"));
        assertNotNull(horizontal.slotsIntoEast(vertical));
    }
    @Test
    public void horizontal_tile_can_slot_into_vertical_tile_in_south_slot() {
        HorizontalTile horizontal = new HorizontalTile(new UnfixedTile("purple-white-red-white", "red-green-yellow-black"));
        SideVerticalTile vertical = new SideVerticalTile(new UnfixedTile("white-black-green-red", "green-blue-orange-red"));
        assertNotNull(horizontal.slotsIntoSouth(vertical));
    }
}