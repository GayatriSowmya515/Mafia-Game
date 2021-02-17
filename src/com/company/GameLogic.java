package com.company;

import java.util.List;
import java.util.Scanner;

public class GameLogic extends Function{

    private static String mafiaResult;


    public static void Play(Player[] players, int totalPlayers,int playerno){

        Scanner input  = new Scanner(System.in);
        int targetPlayer=-1, testPlayer=-1, healedPlayer=-1;
        //String[] sentences = {"Choose a target","Choose a player to test", "Choose a player to heal"};
        int chosePlayer = -1;
        int chosenOne = -1;

        //////////////////////////////////////////Mafia/////////////////////////////////

        if(players[playerno].getCharacter().equals("Mafia")&&!players[playerno].getIsLost()){
            do{
                if(chosePlayer>0){
                    System.out.print("You cannot choose a Mafia. ");
                }
                System.out.print("Choose a target: ");
                chosePlayer = input.nextInt();
            }while(players[chosePlayer-1].getCharacter().equals("Mafia"));
            chosenOne = chosePlayer-1;
        }
        else{
            List<Integer> list =ListOfOtherPlayers(players,totalPlayers,"Mafia");
            if(list.size()>0) {
                chosenOne = getRandomElement(list);
            }
            System.out.println("Mafias have chosen their target.");

        }
        targetPlayer = chosenOne;

        setHp(players,totalPlayers,"Mafia",chosenOne);

        ///////////////////////////Detective///////////////////////////////////////
        chosePlayer = -1;
        chosenOne = -1;
        if(players[playerno].getCharacter().equals("Detective")&&!players[playerno].getIsLost()){
            do{
                if(chosePlayer>0){
                    System.out.print("You cannot choose a Detective. ");
                }
                System.out.print("Choose a player to test: ");
                chosePlayer = input.nextInt();
            }while(players[chosePlayer-1].getCharacter().equals("Detective"));
            if(players[chosePlayer-1].getCharacter().equals("Mafia")){
                System.out.println("Player"+chosePlayer+" is a mafia");
            }
            else{
                System.out.println("Player"+chosePlayer+" is not a mafia");
            }
            chosenOne = chosePlayer-1;
        }
        else{
            List<Integer> list =ListOfOtherPlayers(players,totalPlayers,"Detective");
            if(list.size()>0) {
                chosenOne = getRandomElement(list);
            }
            System.out.println("Detectives have chosen a player to test.");
        }
        if(players[chosenOne].getCharacter().equals("Mafia")){
            players[chosenOne].setIsLost(true);
        }
        testPlayer = chosenOne;

        /////////////////////////////Healer////////////////////////////////
        chosePlayer = -1;
        chosenOne = -1;
        if(players[playerno].getCharacter().equals("Healer")&&!players[playerno].getIsLost()){
            do{
                if(chosePlayer>0){
                    System.out.print("You cannot choose a Healer. ");
                }
                System.out.print("Choose a player to heal: ");
                chosePlayer = input.nextInt();
            }while(players[chosePlayer-1].getCharacter().equals("Healer"));
            chosenOne = chosePlayer-1;
        }
        else{
            List<Integer> list =ListOfOtherPlayers(players,totalPlayers,"Healers");
            if(list.size()>0) {
                chosenOne = getRandomElement(list);
            }
            System.out.println("Healers have chosen someone to heal.");
        }
        if (chosenOne >= 0) {
            players[chosenOne].addHP(500);
        }



        /////////////////////////////////////////////////////////////////////////////////////

        System.out.println("--End of actions--");



        if(players[targetPlayer].getHP()>0){
            System.out.println("No one died");
        }
        else{

            players[targetPlayer].setIsLost(true);
            targetPlayer++;
            System.out.println("Player"+targetPlayer+" has died");
        }



        if(players[testPlayer].getCharacter().equals("Mafia")){
            players[testPlayer].setIsLost(true);
            testPlayer++;
            System.out.println("Player"+testPlayer+" has been voted out");
        }
        else{
            if(!players[playerno].getIsLost()){
                System.out.print("Select a person to vote out: ");
                int num1 = input.nextInt();
            }

            int num = Vote(players,totalPlayers);
            players[num].setIsLost(true);
            num++;
            System.out.println("Player"+num+" has been voted out");

        }
    }

    public static int Vote(Player[] players, int totalPlayers){
        List<Integer> list =ListOfPlayers(players,totalPlayers);
        int num = getRandomElement(list);
        return num;
    }

    public static  boolean RoundEndData(Player[] players, int totalPlayers){
        int remainingPlayers = noOfPlayers(players,totalPlayers);
        int noOfRemainingMafias = noOfPlayers(players,totalPlayers,"Mafia");
        boolean equalRatio = mafiaRatio(noOfRemainingMafias,remainingPlayers);
        if(equalRatio){
            mafiaResult = "Mafia has won";
            return true;
        }
        else if(noOfRemainingMafias==0){
            mafiaResult = "Mafia have lost.";
            return true;
        }
        return false;

    }

    public static  void GameEndData(Player[] players, int totalPlayers,int playerno) {

        System.out.println("Game Over.");
        System.out.println(mafiaResult);
        for (int j = 0; j < 4; j++) {

            List<Integer> list = ListOfAllPlayers(players, totalPlayers, characters[j]);
            for(int i = 0; i < list.size(); i++) {
                System.out.print("Player" + (list.get(i)+1));
                if(list.get(i)==playerno){
                    System.out.print("[user],");
                }
                else{
                    System.out.print(",");
                }
            }

            System.out.println(" were " + characters[j] + "s.");

        }
    }


    public static void setHp(Player[] players, int totalPlayers, String character, int chosenOne){

         int PlayerHP = players[chosenOne].getHP();
         players[chosenOne].setHP(0);
         int noOfMafia = noOfPlayers(players,totalPlayers,"Mafia");
         int avg = PlayerHP/noOfMafia;
         int sum=0;
         int noOfRemainingMafias = 0;
         for(int i=0;i<totalPlayers;i++){
            if(players[i].getCharacter().equals(character)&&players[i].getHP()<=avg){
                sum = sum  + avg - players[i].getHP();
                players[i].setHP(0);

            }
            else{
                noOfRemainingMafias++;
                players[i].addHP((-1)*avg);
            }
         }
         for(int i=0;i<totalPlayers;i++){
            if(players[i].getCharacter().equals(character)&&players[i].getHP()>0) {
                players[i].addHP(sum / noOfRemainingMafias);
            }
         }
    }
}
