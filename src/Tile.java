import java.util.Objects;

public class Tile {
    private final String name;

    public Tile(String name, String mainSideColours, String flipSideColours) {
        this.name = name;
        this.mainSideColours = mainSideColours;
        this.flipSideColours = flipSideColours;
    }

    //    public static String slotsIntoFixed(Tile left, Tile right) {
//        String result = tryCurrentAllignment(left, right, true);
//        if (result == null) result = tryCurrentAllignment(left, right.flip(), true);
//        return result;
//    }
    public static Tile slotsIntoFixed(Tile left, Tile right) {
        Intersection result = tryCurrentAllignment(left, right, true, false);
        if (result != null) return result.getRight();
        result = tryCurrentAllignment(left, right.flip(), true, false);
        if (result != null) return result.getRight();

        return null;
    }

    public static Intersection slotsIntoBothFixed(Tile left, Tile right) {
        return tryCurrentAllignment(left, right, true, true);
    }

    @Override
    public String toString() {
        return "Main: " + mainSideColours + "  Flipside: " + flipSideColours;
    }

    private final String mainSideColours;
    private final String flipSideColours;


    public Tile(String mainSideColours, String flipSideColours) {
        this.name = "";
        this.mainSideColours = mainSideColours;
        this.flipSideColours = flipSideColours;
    }

    public static Intersection slotsInto(Tile left, Tile right) {
        Intersection result = tryCurrentAllignment(left, right, false, false);
        if (result == null) result = tryCurrentAllignment(left, right.flip(), false, false);
        if (result == null) result = tryCurrentAllignment(left.flip(), right, false, false);
        return result;
    }

    private static Intersection tryCurrentAllignment(Tile left, Tile right, boolean leftFixed, boolean rightFixed) {
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                if (intersectionIsValid(left, right)) {
                    return new Intersection(left, right);
                }
                if (!rightFixed)
                    right = right.rotate();
            }
            if (!leftFixed)
                left = left.rotate();

        }
        return null;
    }

    protected Tile flip() {
        return new Tile(flipSideColours, mainSideColours);
    }

    protected Tile rotate() {
        String[] mainColours = getMainSideColours().split("-");
        String[] flipColours = getFlipSideColours().split("-");

        String rotatedMainColours = mainColours[3] + "-" + mainColours[0] + "-" + mainColours[1] + "-" + mainColours[2];
        String rotatedFlipColours = flipColours[1] + "-" + flipColours[2] + "-" + flipColours[3] + "-" + flipColours[0];

        return new Tile(rotatedMainColours, rotatedFlipColours);
    }

    public static boolean intersectionIsValid(Tile left, Tile right) {
        String[] rightMainColours = right.getMainSideColours().split("-");
        String[] rightFlipColours = right.getFlipSideColours().split("-");
        String[] leftMainColours = left.getMainSideColours().split("-");
        String[] leftFlipColours = left.getFlipSideColours().split("-");

        return quadrantOneMatches(leftMainColours, rightMainColours) &&
                quadrantTwoMatches(leftFlipColours, rightMainColours) &&
                quadrantThreeMatches(leftFlipColours, rightFlipColours) &&
                quadrantFourMatches(leftMainColours, rightFlipColours);
    }

    private static boolean quadrantFourMatches(String[] leftMainColours, String[] rightFlipColours) {
        return Objects.equals(leftMainColours[2], rightFlipColours[0]);
    }

    private static boolean quadrantThreeMatches(String[] leftFlipColours, String[] rightFlipColours) {
        return Objects.equals(leftFlipColours[1], rightFlipColours[3]);
    }

    private static boolean quadrantTwoMatches(String[] leftFlipColours, String[] rightMainColours) {
        return Objects.equals(leftFlipColours[2], rightMainColours[0]);
    }

    private static boolean quadrantOneMatches(String[] leftMainColours, String[] rightMainColours) {
        return Objects.equals(leftMainColours[1], rightMainColours[3]);
    }

    public String getMainSideColours() {
        return mainSideColours;
    }

    public String getFlipSideColours() {
        return flipSideColours;
    }
}
