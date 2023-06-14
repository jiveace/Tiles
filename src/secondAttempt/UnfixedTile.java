package secondAttempt;

import java.util.List;

public class UnfixedTile extends Tile {
    public UnfixedTile(List<String> lower, List<String> upper) {
        super(lower, upper);
    }


    public UnfixedTile(String oneSide, String otherSide) {
        super(List.of(oneSide.split("-")), List.of(otherSide.split("-")));
    }

    @Override
    public Tile rotate() {
        return this;
    }

    @Override
    public Tile flip() {
        return this;
    }
}
