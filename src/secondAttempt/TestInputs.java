package secondAttempt;

import java.util.List;

public interface TestInputs {
    List<UnfixedTile> REAL = List.of(
            new UnfixedTile("blue-white-yellow-orange", "black-green-blue-white"),
            new UnfixedTile("red-orange-white-yellow", "white-black-red-green"),

            new UnfixedTile("orange-yellow-purple-blue", "green-red-orange-blue"),
            new UnfixedTile("white-green-blue-green", "blue-orange-black-red"),
            new UnfixedTile("white-blue-yellow-green", "red-white-orange-yellow"),
            new UnfixedTile("red-blue-yellow-purple", "yellow-orange-green-orange"),
            new UnfixedTile("white-black-red-yellow", "black-yellow-blue-purple"),
            new UnfixedTile("black-green-red-blue", "black-yellow-white-red"),
            new UnfixedTile("white-orange-red-yellow", "purple-orange-blue-yellow"),
            new UnfixedTile("blue-white-orange-white", "purple-white-yellow-green"),
            new UnfixedTile("red-green-black-purple", "red-orange-yellow-purple"),
            new UnfixedTile("yellow-green-white-purple", "black-red-black-purple"),
            new UnfixedTile("purple-yellow-black-white", "black-green-purple-blue"),
            new UnfixedTile("orange-red-white-green", "purple-green-black-white"),
            new UnfixedTile("green-blue-yellow-blue", "purple-orange-green-red"),
            new UnfixedTile("green-purple-black-blue", "orange-green-yellow-black"),
            new UnfixedTile("white-blue-black-yellow", "red-yellow-green-yellow"),
            new UnfixedTile("blue-red-orange-black", "purple-red-yellow-red"),
            new UnfixedTile("purple-orange-black-red", "orange-white-red-yellow"),
            new UnfixedTile("blue-yellow-black-white", "black-purple-orange-purple"),
            new UnfixedTile("green-yellow-white-red", "purple-blue-green-yellow"),

            new UnfixedTile("green-black-blue-purple", "blue-orange-green-red"),
            new UnfixedTile("yellow-blue-black-green", "yellow-red-black-green"),
            new UnfixedTile("white-purple-white-red", "green-yellow-black-red"),
            new UnfixedTile("yellow-purple-black-white", "blue-purple-yellow-orange"),
            new UnfixedTile("red-white-black-green", "blue-orange-red-green"),
            new UnfixedTile("blue-purple-red-black", "blue-purple-red-yellow"),
            new UnfixedTile("blue-black-purple-green", "red-blue-white-purple"),
            new UnfixedTile("yellow-red-orange-blue", "black-green-blue-red"),
            new UnfixedTile("blue-red-orange-yellow", "blue-purple-yellow-black"),
            new UnfixedTile("orange-white-green-black", "orange-purple-green-black"),
            new UnfixedTile("red-white-black-orange", "black-orange-blue-purple"),
            new UnfixedTile("orange-purple-blue-white", "blue-white-green-purple"),
            new UnfixedTile("purple-orange-blue-white", "yellow-orange-yellow-black"),
            new UnfixedTile("white-red-blue-red", "orange-green-white-black"),
            new UnfixedTile("black-white-red-green", "yellow-white-black-purple"),
            new UnfixedTile("yellow-orange-purple-white", "orange-green-purple-blue"),
            new UnfixedTile("green-orange-red-purple", "black-orange-white-orange"),
            new UnfixedTile("red-green-white-orange", "green-orange-purple-blue"),
            new UnfixedTile("purple-orange-white-yellow", "black-purple-white-green")
    );

    List<UnfixedTile> DOCTORED = List.of(
            new UnfixedTile("black-yellow-blue-purple", "white-black-red-yellow"),
            new UnfixedTile("white-red-white-purple", "black-red-green-yellow"),
            new UnfixedTile("green-blue-orange-red", "white-black-green-red"),
            new UnfixedTile("green-purple-black-blue", "orange-green-yellow-black"),
            new UnfixedTile("yellow-white-black-purple", "black-white-red-green"),
            new UnfixedTile("red-blue-red-white", "black-orange-green-white"),
            new UnfixedTile("green-orange-purple-blue", "red-green-white-orange")
    );

}
