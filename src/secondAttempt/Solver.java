package secondAttempt;

import java.util.*;
import java.util.stream.Collectors;

public class Solver {

    private final List<UnfixedTile> unfixedTiles;

    public Solver(List<UnfixedTile> unfixedTiles) {
        this.unfixedTiles = unfixedTiles;
    }

    private static List<Tile> allPermutationsOf(HorizontalTile startingTile) {
        return List.of(
                startingTile, startingTile.rotate(), startingTile.rotate().rotate(), startingTile.rotate().rotate().rotate(),
                startingTile.flip(), startingTile.flip().rotate(), startingTile.flip().rotate().rotate(), startingTile.flip().rotate().rotate().rotate()
        );
    }


    public List<Tile> solve() {
        List<List<Tile>> lists = solutionForFirst(unfixedTiles);
        if(lists.isEmpty()) return null;
        System.out.println(("" + lists.get(0)).replace("\n, ", "\n"));
        return lists.get(0);
    }

    private List<List<Tile>> solutionForFirst(List<UnfixedTile> unplacedTiles) {
        List<List<Tile>> fullSolutions = new ArrayList<>();
        for (UnfixedTile unfixedTile : unplacedTiles) {
            HorizontalTile startingTile = new HorizontalTile(unfixedTile);
            List<Tile> startingPermutations = allPermutationsOf(startingTile);
            for (Tile tile : startingPermutations) {
                List<Tile> result = solutionFor(List.of(tile), inputsWithout(unfixedTile, unplacedTiles));
//                List<Tile> result = solutionFor(List.of(tile), inputsWithout(tile, unplacedTiles.subList(1, unplacedTiles.size())));
                if (result != null)
                    fullSolutions.add(result);
            }
        }
        return fullSolutions;
    }

    private List<UnfixedTile> inputsWithout(Tile tile, List<UnfixedTile> unplacedTiles) {
        return unplacedTiles.stream().filter(x -> !x.equals(tile)).collect(Collectors.toList());
    }


    private List<Tile> solutionFor(List<Tile> solutionSoFar, List<UnfixedTile> unplacedTiles) {

//        if (solutionSoFar.size() == 29) {
//            System.out.println(solutionSoFar);
//        }
        if (unplacedTiles.isEmpty()
//                || solutionSoFar.size() == 34
        ) {
//            System.out.println(solutionSoFar);
            return solutionSoFar;
        }

        for (UnfixedTile unfixedTile : unplacedTiles) {
            Tile matchingTile = findMatchingTile(solutionSoFar, unfixedTile);
            if (matchingTile != null) {
                return solutionFor(append(solutionSoFar, matchingTile), unplacedTilesWithout(unplacedTiles, unfixedTile));
            }
        }

        return null;
    }

    private static Tile findMatchingTile(List<Tile> solutionSoFar, UnfixedTile unfixedTile) {
        int solutionSize = solutionSoFar.size();

        if (solutionSize < 7) {
            if (sizeIsOdd(solutionSoFar)) {
                return findMatchingVerticalTile(solutionSoFar, unfixedTile);
            }

            if (sizeIsEven(solutionSoFar)) {
                return findMatchingHorizontalTile(solutionSoFar, unfixedTile);
            }
        }

        if (solutionSize >= 7 && solutionSize < 11) {
            return findMatchingVerticalTileToTheSouth(solutionSoFar, unfixedTile);
        }

        if (solutionSize >= 11 && solutionSize < 18) {
            if (solutionSize == 12 || solutionSize == 14 | solutionSize == 16) {
                return findMatchingVerticalTile(solutionSoFar, unfixedTile);
            }
            if (solutionSize == 13 || solutionSize == 15 || solutionSize == 17) {
                return findDualMatchingHorizontalTile(solutionSoFar, unfixedTile);
            }
            return findMatchingHorizontalTileToTheSouth(solutionSoFar, unfixedTile);
        }

        if (solutionSize >= 18 && solutionSize < 22) {
            return findMatchingVerticalTileToTheSouth(solutionSoFar, unfixedTile);
        }
        if (solutionSize >= 22 && solutionSize < 29) {
            if (solutionSize == 23 || solutionSize == 25 | solutionSize == 27) {
                return findMatchingVerticalTile(solutionSoFar, unfixedTile);
            }
            if (solutionSize == 24 || solutionSize == 26 || solutionSize == 28) {
                return findDualMatchingHorizontalTile(solutionSoFar, unfixedTile);
            }
            return findMatchingHorizontalTileToTheSouth(solutionSoFar, unfixedTile);
        }
        if (solutionSize >= 29 && solutionSize < 33) {
            return findMatchingVerticalTileToTheSouth(solutionSoFar, unfixedTile);
        }
        if (solutionSize >= 33 && solutionSize <= 40) {
            if (solutionSize == 34 || solutionSize == 36 | solutionSize == 38) {
                return findMatchingVerticalTile(solutionSoFar, unfixedTile);
            }
            if (solutionSize == 35 || solutionSize == 37 || solutionSize == 39) {
                return findDualMatchingHorizontalTile(solutionSoFar, unfixedTile);
            }
            return findMatchingHorizontalTileToTheSouth(solutionSoFar, unfixedTile);
        }

        return null;
    }


    private static Tile findDualMatchingHorizontalTile(List<Tile> solutionSoFar, UnfixedTile unfixedTile) {
        var rightMap = new HashMap<Integer, Integer>();
        rightMap.put(13, 8);
        rightMap.put(15, 9);
        rightMap.put(17, 10);
        rightMap.put(24, 19);
        rightMap.put(26, 20);
        rightMap.put(28, 21);
        rightMap.put(35, 30);
        rightMap.put(37, 31);
        rightMap.put(39, 32);
        FacingVerticalTile tileOnLeft = (FacingVerticalTile) solutionSoFar.get(solutionSoFar.size() - 1);
        SideVerticalTile tileBehind = (SideVerticalTile) solutionSoFar.get(rightMap.get(solutionSoFar.size()));
        for (Tile t : allPermutationsOf(new HorizontalTile(unfixedTile))) {
            boolean matchesOneOnLeft = tileOnLeft.slotsIntoEastWithCurrentRotation((HorizontalTile) t) != null;
            boolean matchesOneBehind = tileBehind.slotsIntoSouthWithCurrentRotation((HorizontalTile) t) != null;
            if (matchesOneOnLeft && matchesOneBehind) {
                return t;
            }
        }
        return null;
    }

    private static SideVerticalTile findMatchingVerticalTileToTheSouth(List<Tile> solutionSoFar, UnfixedTile unfixedTile) {
        var map = new HashMap<Integer, Integer>();
        map.put(7, 0);
        map.put(8, 2);
        map.put(9, 4);
        map.put(10, 6);

        map.put(18, 11);
        map.put(19, 13);
        map.put(20, 15);
        map.put(21, 17);

        map.put(29, 22);
        map.put(30, 24);
        map.put(31, 26);
        map.put(32, 28);

        HorizontalTile firstTile = (HorizontalTile) solutionSoFar.get(map.get(solutionSoFar.size()));
        return firstTile.slotsIntoSouth(new SideVerticalTile(unfixedTile));
    }

    private static HorizontalTile findMatchingHorizontalTileToTheSouth(List<Tile> solutionSoFar, UnfixedTile unfixedTile) {
        var map = new HashMap<Integer, Integer>();
        map.put(11, 7);
        map.put(22, 18);
        map.put(33, 29);

        SideVerticalTile firstTile = (SideVerticalTile) solutionSoFar.get(map.get(solutionSoFar.size()));
        return firstTile.slotsIntoSouth(new HorizontalTile(unfixedTile));
    }

    private static boolean sizeIsEven(List<Tile> solutionSoFar) {
        return solutionSoFar.size() % 2 == 0;
    }

    private static boolean sizeIsOdd(List<Tile> solutionSoFar) {
        return solutionSoFar.size() % 2 == 1;
    }

    private static HorizontalTile findMatchingHorizontalTile(List<Tile> solutionSoFar, UnfixedTile unfixedTile) {
        FacingVerticalTile secondTile = (FacingVerticalTile) solutionSoFar.get(solutionSoFar.size() - 1);
        return secondTile.slotsIntoEast(new HorizontalTile(unfixedTile));
    }

    private static FacingVerticalTile findMatchingVerticalTile(List<Tile> solutionSoFar, UnfixedTile unfixedTile) {
        HorizontalTile firstTile = (HorizontalTile) solutionSoFar.get(solutionSoFar.size() - 1);
        return firstTile.slotsIntoEast(new FacingVerticalTile(unfixedTile));
    }

    private static List<UnfixedTile> unplacedTilesWithout(List<UnfixedTile> unplacedTiles, UnfixedTile unfixedTile) {
        return unplacedTiles.stream().filter(x -> x != unfixedTile).collect(Collectors.toList());
    }

    private List<Tile> append(List<Tile> solutionSoFar, Tile matchingVerticalTile) {
        List<Tile> temp = new LinkedList<>(solutionSoFar);
        temp.add(matchingVerticalTile);
        return List.copyOf(temp);
    }

}

