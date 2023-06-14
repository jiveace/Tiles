package secondAttempt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SideVerticalTileTest {

    @Test
    public void horizontal_tile_can_slot_into_vertical_tile_in_south_slot() {
        SideVerticalTile vertical = new SideVerticalTile(new UnfixedTile("green-red-white-black", "orange-red-green-blue"));
        HorizontalTile horizontal = new HorizontalTile(new UnfixedTile("red-green-yellow-black", "purple-white-red-white"));
        assertNotNull(vertical.slotsIntoSouth(horizontal));
    }

}