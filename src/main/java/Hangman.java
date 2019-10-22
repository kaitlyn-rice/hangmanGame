import java.util.*;

// main class
public class Hangman {
    // private static final boolean testingMode = true;

    // you will take care of user interaction under main method
    public static void main(String[] args) {

    }

    
    // this method validate if a string can be parsed to an integer
    // this method takes 1 parameter and returns a boolean:
    //      str - a string
    // when str can be parsed to an integer without any needs of modification
    // return true; otherwise false
    public static boolean isInt(String str) {
        // remove this line
        return true;
    }
    

    // this method validates the positions given by users as a string
    // this method takes 2 parameters and returns a boolean:
    //      positionStr - a string given by a user 
    //                      representing "spaces the user want to check";
    //      spaceAllowed - an integer representing the allowed spaces at the current 
    //                      difficulty level
    // when the given positionStr can be split and parsed to exactly the number of 
    // spaceAllowed integers, return true; otherwise false
    // NOTE: The only allowed delimiter for positionStr is white space
    // e.g.1, positionStr: "0 1 5 7" 
    //        spaceAllowed: 3
    //        return: false
    // e.g.2, positionStr: "0 1 5 a "
    //        spaceAllowed: 4
    //        return: false
    // e.g.3, positionStr: "0 100 4 21 "
    //        spaceAllowed: 4
    //        return: true
    // e.g.4, positionStr: "0, 100, 4, 21 "
    //        spaceAllowed: 4
    //        return: false
    public static boolean validPosition(String positionStr, int spaceAllowed) {
        // remove this line
        return true;
    }
    

    // this method converts the validated positions from a string to an int array
    // this method takes 2 parameters and returns an int array
    //      positionStr - a string representing validated positions provided by a user
    //      spaceAllowed - an integer representing the allowed spaces at the current 
    //                      difficulty level
    // NOTE 1: You should assume the validity of positionStr -
    //      only composed of integers and white spaces
    //      the numbers of integers are the same as spaceAllowed
    // NOTE 2: White spaces serve as delimiters
    // e.g.1, positionStr: "0 100 4 21 "
    //        spaceAllowed: 4
    //        return: {0, 100, 4, 21}
    // e.g.2, positionStr: "0 1          3      4    "
    //        spaceAllowed: 4
    //        return: {0, 1, 3, 4}
    public static int[] getPosition(String positionStr, int spaceAllowed) {
        // remove these two line
        int[] arr = {-1};
        return arr;
    }

    // you are welcome to add more methods if you want
}
