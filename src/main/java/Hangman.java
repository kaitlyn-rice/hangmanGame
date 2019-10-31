import java.util.*;

// main class
public class Hangman {
    private static final boolean testingMode = true;
    
    // you will take care of user interaction under main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int timesPlayed = 1;
        char userEntry;
        String input;
        //to set the difficulty
        do{    
            int check = 1;
            do{
                System.out.println("Enter your difficulty: Easy(e), Intermediate (i), or Hard (h)");
                input = sc.nextLine().toLowerCase();
                userEntry = input.charAt(0);
                
                //ensures that the case always matches with toLowerCase()
            
                switch(userEntry){
                    case 'e':
                    playGame(15,4);
                    check = 1;
                    break;
                    
                    case 'i':
                    playGame(12,3);
                    check = 1;
                    break;
                
                    case 'h':
                    playGame(10,2);
                    check = 1;
                    break;
                    
                    
                    default:
                    System.out.println("\nInvalid diffifulty. Try again...");
                    check = 0;
                    //in case the user inputs a command wrong
                }
            }while(check!=1);
            System.out.println("\nWould you like to play again? Yes(y) or No(n)");
            input = sc.nextLine().toLowerCase();
            userEntry = input.charAt(0);
            switch(userEntry){
                case 'y':
                System.out.println();
                timesPlayed++;
                if(timesPlayed ==20){
                    System.out.println("Error: played too many times.");
                    System.exit(0);
                }
                break;
                
                case 'n':
                System.out.println();
                break;
                
                default:
                System.out.println("\nInvalid entry. Try again...");
                //in case the user inputs a command wrong
            }
        }while(userEntry!='n');
    }

    
    // this method validate if a string can be parsed to an integer
    // this method takes 1 parameter and returns a boolean:
    //      str - a string
    // when str can be parsed to an integer without any needs of modification
    // return true; otherwise false
    public static boolean isInt(String str) {
        try{
          Integer.parseInt(str);
          return true;
        }
        catch(NumberFormatException e){
          return false;
        }
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
        ArrayList<Integer> arr = new ArrayList<Integer>();
        int i =0;
        StringTokenizer t = new StringTokenizer(positionStr, " ");
        //creates a collection of tokens of the index positions
        do{
           String str =  t.nextToken();
           if(!isInt(str)) return false; //ensures that the input is a number
           arr.add(Integer.parseInt(str));
           i++;
        }while (t.hasMoreTokens() == true);
        
        if(arr.size() == spaceAllowed) return true; //ensures that there is the right number of indices
        return false;
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
        int[] arr = new int[spaceAllowed];
        int i =0;
        StringTokenizer t = new StringTokenizer(positionStr, " ");
        //creates a collection of tokens of the index positions
        do{
           String str =  t.nextToken();
           arr[i] = Integer.parseInt(str);   
           i++;
        }while (t.hasMoreTokens() == true);
        //compiles the tokens into the array
        return arr;
    }

    //checks user character guess
    public static boolean isChar(char c) {
         if(Character.isLetter(c))
            return true;
         return false;
    }
    
    //checks that the indices entered are valid in the length of the word
    public static boolean validIndex(int[] arr, String str){
        for(int i = 0; i<arr.length; i++){
            if(arr[i] > str.length()-1){
                return false;
            }
        }
        return true;
    }
    
    //the method that contains the actual game play
    public static void playGame(int guess, int space){
        String word = RandomWord.newWord().toLowerCase();
        String str = "";
        String solve = "solve";
        int gCheck;
        Scanner sc = new Scanner(System.in);
        for(int i = 0; i<word.length(); i++){
            str= str + "-";
        }
        //creates the string with dashes in it to print as the user guesses
        System.out.println("The word is: "+str);
        do{
            System.out.print("Please enter the letter you want to guess: ");
            String input = sc.nextLine().toLowerCase();
            if(input.equals(solve)) {
               System.out.println("Please solve the word!");
               input = sc.nextLine().toLowerCase();
               if(input.equals(word)) {
                  System.out.println("You win! \r\nYou have guessed the word! Congratulations"); 
                  str = word;
                  break;
               }
               if(!input.equals(word)) {
                  System.out.println("That is not the secret word");
                  guess--;
                  System.out.println("Guesses remaining: " + guess);
               }
            }
            char letter = input.charAt(0);
            if(!isChar(letter)){
                System.out.println("Your input is not valid. Try again.");
                continue;
            }//checks that the input is actually a letter
            System.out.println(); //breaks up the prompts
            System.out.print("Please enter the spaces you want to check (separated by spaces): ");
            input = sc.nextLine();
            if(!validPosition(input, space)){
               System.out.println("Your input is not valid.  Try again.");
               System.out.println("Guesses Remaining: " + guess);
               continue;
            }
            
            int[] inputArr = getPosition(input, space);
            if(!validIndex(inputArr, word)){
               System.out.println("Your input is not valid.  Try again.");
               System.out.println("Guesses Remaining: " + guess);
               continue;
            }
            gCheck = 0;
            for(int i = 0; i < inputArr.length; i++) {
               if(word.charAt(inputArr[i]) == letter) {
                  gCheck++;
                  String s = "";
                    for(int n=0; n<str.length(); n++){
                        if(n==inputArr[i]){
                            s+=letter;
                        }else{
                            s+=str.charAt(n);
                        }
                    }
                    str= s;
               }
            }
            if(gCheck == 0){ 
               guess--;
               System.out.println("Your letter was not found in the spaces you provided.");
            }else{
                System.out.println("Your guess is in the word!");
            }
            
            System.out.println("The updated word is: "+str);
            System.out.println("Guesses remaining: " + guess);
            if(guess == 0)System.out.println("You have failed to guess the word... :(");
            if(word.equals(str)){
                System.out.println("You win! \r\nYou have guessed the word! Congratulations"); 
                break;
            }
        }while (guess != 0);
    }
}
