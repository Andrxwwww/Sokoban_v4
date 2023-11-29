package pt.iscte.poo.sokobanstarter;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Score {

    public static void createHighScoreFile(){
        try{
            File file = new File("Scores.txt");
            if (!file.exists()) {
                file.createNewFile();
                FileWriter writingFile = new FileWriter(file);
                writingFile.write("Top 3 at : \n");
                writingFile.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while trying to write in the file.");
            e.printStackTrace();
        }
    }

    public static void writePlayerScoreInFile(int level_num, String playerName, int score) {
        try {

            List<PlayerScore> playerScoreList = new ArrayList<>();
            Scanner readingFile = new Scanner(new File("Scores.txt"));
            while (readingFile.hasNextLine()) {
                String line = readingFile.nextLine();
                if (line.contains("At Level-> " + level_num)) {
                    readingFile.nextLine();
                }
                if (line.contains("had a total score of")) {
                    String[] playerScore = line.split(" ");
                    playerScoreList.add(new PlayerScore(playerScore[0], Integer.parseInt(playerScore[6])));
                }
            }

            playerScoreList.add(new PlayerScore(playerName, score));
            Collections.sort(playerScoreList);

            FileWriter writingFile = new FileWriter("Scores.txt" , true);
            writingFile.write("Level-> " + level_num + "\n");
            for (int i = 0; i < Math.min(3, playerScoreList.size()); i++) {
                writingFile.write(playerScoreList.get(i).totalPlayerScore() + "\n");
            }
            writingFile.close();

        } catch (IOException e) {
            System.out.println("An error occurred while trying to write in the file.");
            e.printStackTrace();
        }

    }

    private static class PlayerScore implements Comparable<PlayerScore> {

        private String player;
        private int score;

        public PlayerScore(String player, int score) {
            this.player = player;
            this.score = score;
        }
        //FORMULA FOR THE POINTS: 1000 + (10*gameEngine.moves) + (2*gameEngine.bobcat.getBattery())

        public int getScore() {
            return score;
        }

        public String totalPlayerScore() {
            return player + " had a total score of " + score;
        }

        @Override
        public int compareTo(PlayerScore b) {
            return b.getScore() - this.getScore();
        }
    }
}
