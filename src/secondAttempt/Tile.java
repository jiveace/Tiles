package secondAttempt;

import java.util.List;
import java.util.Objects;

public abstract class Tile {
    @Override
    public String toString() {
        return "" +
                "Main: " + upper +
                ", secondary=" + lower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Tile tile = (Tile) o;
        return (upper.equals(tile.upper) && lower.equals(tile.lower)) || (lower.equals(tile.upper) && upper.equals(tile.lower));
    }

    @Override
    public int hashCode() {
        return Objects.hash(upper, lower);
    }

    private final List<String> upper;
    private final List<String> lower;

    public Tile(List<String> lower, List<String> upper) {
        this.lower = lower;
        this.upper = upper;
    }

    public List<String> getUpper() {
        return upper;
    }

    public List<String> getLower() {
        return lower;
    }

    public abstract Tile rotate() ;

    public abstract Tile flip() ;
}
