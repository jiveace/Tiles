package secondAttempt;

import java.util.List;

public class SideVerticalTile extends Tile{
    public SideVerticalTile(List<String> a, List<String> b) {
        super(a, b);
    }
    @Override
    public String toString() {
        return "Side Vertical:   " + super.toString() + "\n";
    }

    public SideVerticalTile(UnfixedTile unfixedTile) {
        super(unfixedTile.getUpper(), unfixedTile.getLower());
    }

    public Tile rotate() {
        String rotatedMainColours = getUpper().get(3) + "-" + getUpper().get(0) + "-" + getUpper().get(1) + "-" + getUpper().get(2);
        String rotatedFlipColours = getLower().get(1) + "-" + getLower().get(2) + "-" + getLower().get(3) + "-" + getLower().get(0);

        return new SideVerticalTile(new UnfixedTile(rotatedMainColours, rotatedFlipColours));
    }

    public Tile flip() {
        return new SideVerticalTile(getUpper(),getLower());
    }


    public HorizontalTile slotsIntoSouth(HorizontalTile other) {
        HorizontalTile result = (HorizontalTile) tryAllRotationsForOneSideSouth(other);
        if (result != null) return result;
        result = (HorizontalTile)  tryAllRotationsForOneSideSouth((HorizontalTile) other.flip());
        return result;
    }

    private Tile tryAllRotationsForOneSideSouth(HorizontalTile other) {
        for (int i = 0; i < 4; i++) {
            if (slotsIntoSouthWithCurrentRotation(other) != null) {
                return other;
            }
            other = (HorizontalTile) other.rotate();
        }
        return null;
    }

    Tile slotsIntoSouthWithCurrentRotation(HorizontalTile other) {
        String leftUpper = getUpper().get(1);
        String rightUpper = getLower().get(2);
        String leftLower = getUpper().get(2);
        String rightLower = getLower().get(1);

        String otherLeftUpper = other.getUpper().get(0);
        String otherRightUpper = other.getUpper().get(1);
        String otherLeftLower = other.getLower().get(3);
        String otherRightLower = other.getLower().get(2);

        if (!leftUpper.equals(otherLeftUpper)) return null;
        if (!rightUpper.equals(otherRightUpper)) return null;
        if (!leftLower.equals(otherLeftLower)) return null;
        if (!rightLower.equals(otherRightLower)) return null;
        return other;
    }
}
