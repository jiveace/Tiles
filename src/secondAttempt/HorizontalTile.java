package secondAttempt;

import java.util.List;

public class HorizontalTile extends Tile {

    public HorizontalTile(List<String> upper, List<String> lower) {
        super(upper, lower);
    }

    @Override
    public String toString() {
        return "Horizontal:      " + super.toString() + "\n";
    }

    public HorizontalTile(UnfixedTile unfixedTile) {
        super(unfixedTile.getUpper(), unfixedTile.getLower());
    }

    public FacingVerticalTile slotsIntoEast(FacingVerticalTile other) {
        FacingVerticalTile result = (FacingVerticalTile) tryAllRotationsForOneSideEast(other);
        if (result != null) return result;
        result = (FacingVerticalTile)  tryAllRotationsForOneSideEast((FacingVerticalTile) other.flip());
        return result;
    }

    private Tile tryAllRotationsForOneSideEast(FacingVerticalTile other) {
        for (int i = 0; i < 4; i++) {
            if (slotsIntoEastWithCurrentRotation(other) != null) {
                return other;
            }
            other = (FacingVerticalTile) other.rotate();
        }
        return null;
    }

    private Tile slotsIntoEastWithCurrentRotation(FacingVerticalTile other) {
        String nearUpper = getUpper().get(2);
        String farUpper = getUpper().get(1);
        String nearLower = getLower().get(1);
        String farLower = getLower().get(2);

        String otherNearUpper = other.getUpper().get(0);
        String otherFarUpper = other.getLower().get(3);
        String otherNearLower = other.getUpper().get(3);
        String otherFarLower = other.getLower().get(0);

        if (!nearUpper.equals(otherNearUpper)) return null;
        if (!nearLower.equals(otherNearLower)) return null;
        if (!farUpper.equals(otherFarUpper)) return null;
        if (!farLower.equals(otherFarLower)) return null;
        return other;
    }

    public Tile rotate() {
        String rotatedMainColours = getUpper().get(3) + "-" + getUpper().get(0) + "-" + getUpper().get(1) + "-" + getUpper().get(2);
        String rotatedFlipColours = getLower().get(1) + "-" + getLower().get(2) + "-" + getLower().get(3) + "-" + getLower().get(0);

        return new HorizontalTile(new UnfixedTile(rotatedMainColours, rotatedFlipColours));
    }

    public Tile flip() {
        return new HorizontalTile(getUpper(),getLower());
    }

    public SideVerticalTile slotsIntoSouth(SideVerticalTile other) {
        SideVerticalTile result = (SideVerticalTile) tryAllRotationsForOneSideSouth(other);
        if (result != null) return result;
        result = (SideVerticalTile)  tryAllRotationsForOneSideSouth((SideVerticalTile) other.flip());
        return result;
    }

    private Tile tryAllRotationsForOneSideSouth(SideVerticalTile other) {
        for (int i = 0; i < 4; i++) {
            if (slotsIntoSouthWithCurrentRotation(other) != null) {
                return other;
            }
            other = (SideVerticalTile) other.rotate();
        }
        return null;
    }

    private Tile slotsIntoSouthWithCurrentRotation(SideVerticalTile other) {
        String leftUpper = getUpper().get(3);
        String rightUpper = getUpper().get(2);
        String leftLower = getLower().get(0);
        String rightLower = getLower().get(1);

        String otherLeftUpper = other.getUpper().get(0);
        String otherRightUpper = other.getLower().get(3);
        String otherLeftLower = other.getUpper().get(3);
        String otherRightLower = other.getLower().get(0);

        if (!leftUpper.equals(otherLeftUpper)) return null;
        if (!rightUpper.equals(otherRightUpper)) return null;
        if (!leftLower.equals(otherLeftLower)) return null;
        if (!rightLower.equals(otherRightLower)) return null;
        return other;
    }

}
