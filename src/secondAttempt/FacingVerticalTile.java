package secondAttempt;

import java.util.List;

public class FacingVerticalTile extends Tile{

    @Override
    public String toString() {
        return "Facing Vertical: " + super.toString() + "\n";
    }

    public FacingVerticalTile(List<String> near, List<String> far) {
        super(near, far);
    }

    public FacingVerticalTile(UnfixedTile unfixedTile) {
        super(unfixedTile.getUpper(), unfixedTile.getLower());
    }


    public Tile rotate() {
        String rotatedMainColours = getUpper().get(3) + "-" + getUpper().get(0) + "-" + getUpper().get(1) + "-" + getUpper().get(2);
        String rotatedFlipColours = getLower().get(1) + "-" + getLower().get(2) + "-" + getLower().get(3) + "-" + getLower().get(0);

        return new FacingVerticalTile(new UnfixedTile(rotatedMainColours, rotatedFlipColours));
    }
    
    public Tile flip() {
        return new FacingVerticalTile(getUpper(), getLower());
    }


    public HorizontalTile slotsIntoEast(HorizontalTile other) {
        HorizontalTile result = (HorizontalTile) tryAllRotationsForOneSide(other);
        if (result != null) return result;
        result = (HorizontalTile)  tryAllRotationsForOneSide((HorizontalTile) other.flip());
        if (result != null) return result;

        return null;
    }

    private Tile tryAllRotationsForOneSide(HorizontalTile other) {
        for (int i = 0; i < 4; i++) {
            if (slotsIntoEastWithCurrentRotation(other) != null) {
                return other;
            }
            other = (HorizontalTile) other.rotate();
        }
        return null;
    }

    public HorizontalTile slotsIntoEastWithCurrentRotation(HorizontalTile other) {
            String nearUpper = getUpper().get(1);
            String farUpper = getLower().get(2);
            String nearLower = getUpper().get(2);
            String farLower = getLower().get(1);

            String otherNearUpper = other.getUpper().get(3);
            String otherFarUpper = other.getUpper().get(0);
            String otherNearLower = other.getLower().get(0);
            String otherFarLower = other.getLower().get(3);

            if (!nearUpper.equals(otherNearUpper)) return null;
            if (!nearLower.equals(otherNearLower)) return null;
            if (!farUpper.equals(otherFarUpper)) return null;
            if (!farLower.equals(otherFarLower)) return null;
            return other;
    }
}
