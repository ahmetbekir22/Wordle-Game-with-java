import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Wordle {
    static String[] wordsArray = new String[2317];  // create an array .
    public static void main(String[] args) {

        try {// Open the dictionary file and read it line by line.
            BufferedReader reader = new BufferedReader(new FileReader("dict.txt"));
            String line = reader.readLine();
            int k = 0;
            while (line != null) {
                wordsArray[k++] = line;
                line = reader.readLine();}
            reader.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        String keyWord =RandomKeyWord();// get a key word by calling RandomKeyWord method.
        int tryCounter = 0;
        String[] guessWords = Arrays.copyOf(args, 6);

        for (String word : guessWords) { // this loop checks the words in dictionary .
            System.out.print("try" + (tryCounter + 1) + "  (" + word + "):");

            if (word.length() != 5) {
                System.out.print("The length of word must be five!\n");
                tryCounter++;
                continue;
            } else if (!Arrays.asList(wordsArray).contains(word)) {
                System.out.print("Word does not exist in the dictionary!\n");
                tryCounter++;
                continue;

            }

            if (tryCounter == 6) {// If the user has used  all 6 tries, end the game and display the key word.
                System.out.println("You exceeded maximum try shot!");
                System.out.println("You failed! The key word is " + keyWord + ".");
                return;
            }

            boolean superVisior = false;

            while (!superVisior && tryCounter < 6) {// While the user has not guessed the correct word and has not exceeded the 6 tries limit.
                tryCounter++;
                if (word.equals(keyWord)) {
                    if (tryCounter == 1) {
                        System.out.print("Congratulations! You guess right in 1st shot!");
                    } else if (tryCounter == 2) {
                        System.out.print("Congratulations! You guess right in 2nd shot!");
                    } else if (tryCounter == 3) {
                        System.out.print("Congratulations! You guess right in 3rd shot!");
                    } else if (tryCounter > 3) {
                        System.out.print("Congratulations! You guess right in " + tryCounter + "th shot!");
                    }return;

                } else {// If the user didn't  guess the correct word, provide feedback on their guess.
                    System.out.println();
                    for (int i = 0; i < 5; i++) {
                        char guessLetter = word.charAt(i);
                        if (guessLetter == keyWord.charAt(i)) {
                            System.out.println((i + 1) + ". letter exists and located in right position.");
                        }
                        else if (keyWord.indexOf(guessLetter) != -1) {
                            System.out.println((i + 1) + ". letter exists but located in wrong position.");
                        } else {
                            System.out.println((i + 1) + ". letter does not exist.");
                        }
                    }
                }superVisior = true;
            }
        }
        if (tryCounter == 6) { // If the user has used up all 6 tries, end the game and display the key word.
            System.out.println("You exceeded maximum try shot!");
            System.out.println("You failed! The key word is " + keyWord + ".");}
    }

    private   static String RandomKeyWord() {// get random key word from WordsArray.
        Random random = new Random();
        int index = random.nextInt(wordsArray.length); // choose an int randomly between 0 to length of wordsArray.
        return wordsArray[index];
    }
}