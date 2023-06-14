import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Solver {
    private List<Tile> tiles;
    private int longestChain = 0;

    public Solver(List<Tile> tiles) {

        this.tiles = tiles;
    }


    public List<Tile> solve() {
        longestChain = 0;
        return (solutionForFirst(List.of(), tiles));
    }

    private List<Tile> solutionFor(List<Tile> existingList, List<Tile> potentialNext) {
        if (potentialNext.isEmpty()) return existingList;
        if (existingList.size() > longestChain) longestChain ++;
        if (existingList.size() > 12){
            System.out.println("===========");
            for (Tile t : existingList) {
                System.out.println(t);
            }
        }
        for (Tile candidate : potentialNext) {
            List<Tile> result = null;
            if (existingList.size() == 0 || canonicalForm(existingList, candidate) != null) {
                var canonicalForm = canonicalForm(existingList, candidate);
                result = solutionFor(of(existingList, canonicalForm), potentialNext.stream().filter(x -> x != candidate).collect(Collectors.toList()));
            }
            if (null != result) {
                return result;
            }
        }
        return null;
    }

    private List<Tile> solutionForFirst(List<Tile> existingList, List<Tile> potentialNext) {
        if (potentialNext.isEmpty()) return existingList;
        if (existingList.size() > 12){
            System.out.println("===========");
            for (Tile t : existingList) {
                System.out.println(t);
            }
        }
        for (Tile candidate : potentialNext) {
            var ways = List.of(
                    candidate, candidate.rotate(), candidate.rotate().rotate(), candidate.rotate().rotate().rotate(),
                    candidate.flip(), candidate.flip().rotate(), candidate.flip().rotate().rotate(), candidate.flip().rotate().rotate().rotate()
            );
            List<Tile> result = null;
            for(Tile way: ways){
                if (existingList.size() == 0 || canonicalForm(existingList, way) != null) {
                    var canonicalForm = canonicalForm(existingList, way);
                    result = solutionFor(of(existingList, canonicalForm), potentialNext.stream().filter(x -> x != candidate).collect(Collectors.toList()));
                }
                if (null != result) {
                    return result;
                }}
        }
        return null;
    }

    private static Tile canonicalForm(List<Tile> existingList, Tile candidate) {
        if (existingList.size() == 0) return candidate.flip();
        int tilesAlreadyPlaced = existingList.size();

        if (tilesAlreadyPlaced == 1 && Tile.slotsInto(existingList.get(0), candidate) != null) {
            return Tile.slotsInto(existingList.get(0), candidate).getRight();
        }

        // First Row
        if (tilesAlreadyPlaced < 7) {
            Tile tile = Tile.slotsIntoFixed(existingList.get(tilesAlreadyPlaced - 1).flip(), candidate);
             if (tile == null) return null;
            return tile.flip();
        }
//        //First Vertical Row
        if (tilesAlreadyPlaced == 7)
            // Dont flip #3 ?
            return Tile.slotsIntoFixed(existingList.get(tilesAlreadyPlaced - 7).rotate(), candidate);
        if (tilesAlreadyPlaced == 18 || tilesAlreadyPlaced == 29)
            return Tile.slotsIntoFixed(existingList.get(tilesAlreadyPlaced - 11).rotate(), candidate);
        if (tilesAlreadyPlaced == 8 || tilesAlreadyPlaced == 19 || tilesAlreadyPlaced == 30)
            return Tile.slotsIntoFixed(existingList.get(tilesAlreadyPlaced - 6).rotate(), candidate);
        if (tilesAlreadyPlaced == 9 || tilesAlreadyPlaced == 20 || tilesAlreadyPlaced == 31)
            return Tile.slotsIntoFixed(existingList.get(tilesAlreadyPlaced - 5).rotate(), candidate);
        if (tilesAlreadyPlaced == 10 || tilesAlreadyPlaced == 21 || tilesAlreadyPlaced == 32)
            return Tile.slotsIntoFixed(existingList.get(tilesAlreadyPlaced - 4).rotate(), candidate);
//
//
//        // First knotted row
        if (tilesAlreadyPlaced == 11 || tilesAlreadyPlaced == 22 || tilesAlreadyPlaced == 33)
            return Tile.slotsIntoFixed(existingList.get(tilesAlreadyPlaced - 4).flip(), candidate);

        if (tilesAlreadyPlaced == 12 || tilesAlreadyPlaced == 23 || tilesAlreadyPlaced == 34)
            return Tile.slotsIntoFixed(existingList.get(tilesAlreadyPlaced - 1).rotate(), candidate);


        if (tilesAlreadyPlaced == 13 || tilesAlreadyPlaced == 24 || tilesAlreadyPlaced == 35) {
            Tile tileThatSlotsIntoTileInSameRow = Tile.slotsIntoFixed(existingList.get(tilesAlreadyPlaced - 1), candidate);
            if (tileThatSlotsIntoTileInSameRow == null) return null;

            // rotate-rotate-rotate?
            Intersection tileThatSlotsIntoTileInEarlierRowRow = Tile.slotsIntoBothFixed(existingList.get(tilesAlreadyPlaced - 5), tileThatSlotsIntoTileInSameRow.rotate());
            if (tileThatSlotsIntoTileInEarlierRowRow != null) {
                return tileThatSlotsIntoTileInSameRow;
            } else {
                return null;
            }
        }
        if (tilesAlreadyPlaced == 14 || tilesAlreadyPlaced == 25 || tilesAlreadyPlaced == 36)
            return Tile.slotsIntoFixed(existingList.get(tilesAlreadyPlaced - 1).rotate(), candidate);


        if (tilesAlreadyPlaced == 15 || tilesAlreadyPlaced == 26 || tilesAlreadyPlaced == 37) {
            Tile tileThatSlotsIntoTileInSameRow = Tile.slotsIntoFixed(existingList.get(tilesAlreadyPlaced - 1), candidate);
            if (tileThatSlotsIntoTileInSameRow == null) return null;

            Intersection tileThatSlotsIntoTileInEarlierRowRow = Tile.slotsIntoBothFixed(existingList.get(tilesAlreadyPlaced - 6), tileThatSlotsIntoTileInSameRow.rotate());
            if (tileThatSlotsIntoTileInEarlierRowRow != null) {
                return tileThatSlotsIntoTileInSameRow;
            } else {
                return null;
            }

        }

        if (tilesAlreadyPlaced == 16 || tilesAlreadyPlaced == 27 || tilesAlreadyPlaced == 38)
            return Tile.slotsIntoFixed(existingList.get(tilesAlreadyPlaced - 1).rotate(), candidate);

        if (tilesAlreadyPlaced == 17 || tilesAlreadyPlaced == 28 || tilesAlreadyPlaced == 39) {
            Tile tileThatSlotsIntoTileInSameRow = Tile.slotsIntoFixed(existingList.get(tilesAlreadyPlaced - 1), candidate);
            if (tileThatSlotsIntoTileInSameRow == null) return null;

            Intersection tileThatSlotsIntoTileInEarlierRowRow = Tile.slotsIntoBothFixed(existingList.get(tilesAlreadyPlaced - 7), tileThatSlotsIntoTileInSameRow.rotate());
            if (tileThatSlotsIntoTileInEarlierRowRow != null) {
                return tileThatSlotsIntoTileInSameRow;
            } else {
                return null;
            }

        }

        return null;
    }


    private static boolean candidateIsValidToAddToList(List<Tile> existingList, Tile candidate) {
        int tilesAlreadyPlaced = existingList.size();

        if (tilesAlreadyPlaced == 1) {
            return null != Tile.slotsInto(existingList.get(0), candidate);
        }

        // First Row
        if (tilesAlreadyPlaced < 7)
            return null != Tile.slotsIntoFixed(existingList.get(tilesAlreadyPlaced - 1), candidate);
        //First Vertical Row
        if (tilesAlreadyPlaced == 7 || tilesAlreadyPlaced == 18 || tilesAlreadyPlaced == 29)
            return null != Tile.slotsIntoFixed(existingList.get(tilesAlreadyPlaced - 7).rotate(), candidate);
        if (tilesAlreadyPlaced == 8 || tilesAlreadyPlaced == 19 || tilesAlreadyPlaced == 30)
            return null != Tile.slotsIntoFixed(existingList.get(tilesAlreadyPlaced - 6).rotate(), candidate);
        if (tilesAlreadyPlaced == 9 || tilesAlreadyPlaced == 20 || tilesAlreadyPlaced == 31)
            return null != Tile.slotsIntoFixed(existingList.get(tilesAlreadyPlaced - 5).rotate(), candidate);
        if (tilesAlreadyPlaced == 10 || tilesAlreadyPlaced == 21 || tilesAlreadyPlaced == 32)
            return null != Tile.slotsIntoFixed(existingList.get(tilesAlreadyPlaced - 4).rotate(), candidate);


        // First knotted row
        if (tilesAlreadyPlaced == 11 || tilesAlreadyPlaced == 22 || tilesAlreadyPlaced == 33)
            return null != Tile.slotsIntoFixed(existingList.get(tilesAlreadyPlaced - 4), candidate);

        if (tilesAlreadyPlaced == 12 || tilesAlreadyPlaced == 23 || tilesAlreadyPlaced == 34)
            return null != Tile.slotsIntoFixed(existingList.get(tilesAlreadyPlaced - 1).rotate(), candidate);

        if (tilesAlreadyPlaced == 13 || tilesAlreadyPlaced == 24 || tilesAlreadyPlaced == 35) {
            Tile tileThatSlotsIntoTileInSameRow = Tile.slotsIntoFixed(existingList.get(tilesAlreadyPlaced - 1), candidate);
            if (tileThatSlotsIntoTileInSameRow == null) return false;

            Intersection tileThatSlotsIntoTileInEarlierRowRow = Tile.slotsIntoBothFixed(existingList.get(tilesAlreadyPlaced - 5), tileThatSlotsIntoTileInSameRow.rotate());
            return tileThatSlotsIntoTileInEarlierRowRow != null;
        }
        if (tilesAlreadyPlaced == 14 || tilesAlreadyPlaced == 25 || tilesAlreadyPlaced == 36)
            return null != Tile.slotsIntoFixed(existingList.get(tilesAlreadyPlaced - 1).rotate(), candidate);


        if (tilesAlreadyPlaced == 15 || tilesAlreadyPlaced == 26 || tilesAlreadyPlaced == 37) {
            Tile tileThatSlotsIntoTileInSameRow = Tile.slotsIntoFixed(existingList.get(tilesAlreadyPlaced - 1), candidate);
            if (tileThatSlotsIntoTileInSameRow == null) return false;

            Intersection tileThatSlotsIntoTileInEarlierRowRow = Tile.slotsIntoBothFixed(existingList.get(tilesAlreadyPlaced - 6), tileThatSlotsIntoTileInSameRow.rotate());
            return tileThatSlotsIntoTileInEarlierRowRow != null;
        }

        if (tilesAlreadyPlaced == 16 || tilesAlreadyPlaced == 27 || tilesAlreadyPlaced == 38)
            return null != Tile.slotsIntoFixed(existingList.get(tilesAlreadyPlaced - 1).rotate(), candidate);

        if (tilesAlreadyPlaced == 17 || tilesAlreadyPlaced == 28 || tilesAlreadyPlaced == 39) {
            Tile tileThatSlotsIntoTileInSameRow = Tile.slotsIntoFixed(existingList.get(tilesAlreadyPlaced - 1), candidate);
            if (tileThatSlotsIntoTileInSameRow == null) return false;

            Intersection tileThatSlotsIntoTileInEarlierRowRow = Tile.slotsIntoBothFixed(existingList.get(tilesAlreadyPlaced - 7), tileThatSlotsIntoTileInSameRow.rotate());
            return tileThatSlotsIntoTileInEarlierRowRow != null;
        }

        return false;
    }

    private List<Tile> of(List<Tile> existingList, Tile candidate) {
        var temp = new ArrayList<Tile>();
        temp.addAll(existingList);
        temp.add(candidate);
        return List.copyOf(temp);


    }


    public static void main(String[] args) {
        List<Tile> tiles = List.of(
                new Tile("orange-yellow-purple-blue", "green-red-orange-blue"),
                new Tile("white-green-blue-green", "blue-orange-black-red"),
                new Tile("white-blue-yellow-green", "red-white-orange-yellow"),
                new Tile("red-blue-yellow-purple", "yellow-orange-green-orange"),
                new Tile("white-black-red-yellow", "black-yellow-blue-purple"),
                new Tile("black-green-red-blue", "black-yellow-white-red"),
                new Tile("white-orange-red-yellow", "purple-orange-blue-yellow"),
                new Tile("blue-white-orange-white", "purple-white-yellow-green"),
                new Tile("red-green-black-purple", "red-orange-yellow-purple"),
                new Tile("yellow-green-white-purple", "black-red-black-purple"),
                new Tile("purple-yellow-black-white", "black-green-purple-blue"),
                new Tile("orange-red-white-green", "purple-green-black-white"),
                new Tile("green-blue-yellow-blue", "purple-orange-green-red"),
                new Tile("green-purple-black-blue", "orange-green-yellow-black"),
                new Tile("white-blue-black-yellow", "red-yellow-green-yellow"),
                new Tile("blue-red-orange-black", "purple-red-yellow-red"),
                new Tile("purple-orange-black-red", "orange-white-red-yellow"),
                new Tile("blue-yellow-black-white", "black-purple-orange-purple"),
                new Tile("green-yellow-white-red", "purple-blue-green-yellow"),
                new Tile("blue-white-yellow-orange", "black-green-blue-white"),
                new Tile("green-black-blue-purple", "blue-orange-green-red"),
                new Tile("yellow-blue-black-green", "yellow-red-black-green"),
                new Tile("white-purple-white-red", "green-yellow-black-red"),
                new Tile("yellow-purple-black-white", "blue-purple-yellow-orange"),
                new Tile("red-white-black-green", "blue-orange-red-green"),
                new Tile("blue-purple-red-black", "blue-purple-red-yellow"),
                new Tile("red-orange-white-yellow", "white-black-red-green"),
                new Tile("blue-black-purple-green", "red-blue-white-purple"),
                new Tile("yellow-red-orange-blue", "black-green-blue-red"),
                new Tile("blue-red-orange-yellow", "blue-purple-yellow-black"),
                new Tile("orange-white-green-black", "orange-purple-green-black"),
                new Tile("red-white-black-orange", "black-orange-blue-purple"),
                new Tile("orange-purple-blue-white", "blue-white-green-purple"),
                new Tile("purple-orange-blue-white", "yellow-orange-yellow-black"),
                new Tile("white-red-blue-red", "orange-green-white-black"),
                new Tile("black-white-red-green", "yellow-white-black-purple"),
                new Tile("yellow-orange-purple-white", "orange-green-purple-blue"),
                new Tile("green-orange-red-purple", "black-orange-white-orange"),
                new Tile("red-green-white-orange", "green-orange-purple-blue"),
                new Tile("purple-orange-white-yellow", "black-purple-white-green")
        );

        List<Tile> solution = new Solver(tiles).solve();
        for (Tile t : solution) {
            System.out.println(t);
        }
    }

    public int getLongestChain() {
        return longestChain;
    }
}