public class Intersection {

    private final Tile left;
    private final Tile right;

    public Intersection(Tile left, Tile right) {
        this.left = left;
        this.right = right;
    }

    public Tile getLeft() {
        return left;
    }

    public Tile getRight() {
        return right;
    }
}
