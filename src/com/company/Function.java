package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Function {

    protected static String[] characters = {"Mafia", "Detective", "Healer", "Commoner"};

    public static int noOfPlayers(Player[] players, int totalPlayers, String character){
        int num = 0;
        for(int i=0;i<totalPlayers;i++){
            if(players[i].getCharacter().equals(character) && !players[i].getIsLost()){
                num++;
            }
        }
        return num;
    }

    public static int noOfPlayers(Player[] players, int totalPlayers){
        int num =0;
        for(int i =0;i<totalPlayers;i++){
            if(!players[i].getIsLost()){
                num++;
            }
        }
        return num;
    }


    public static List<Integer> ListOfPlayers(Player[] players, int totalPlayers){
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<totalPlayers;i++){
            if(!players[i].getIsLost()){
                list.add(i);
            }
        }
        return list;
    }

    public static List<Integer> ListOfPlayers(Player[] players, int totalPlayers, String character){
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<totalPlayers;i++){
            if(players[i].getCharacter().equals(character) && !players[i].getIsLost()){
                list.add(i);
            }
        }

        return list;
    }

    public static List<Integer> ListOfAllPlayers(Player[] players, int totalPlayers, String character){
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<totalPlayers;i++){
            if(players[i].getCharacter().equals(character)){
                list.add(i);
            }
        }

        return list;
    }

    public static int getRandomElement(List<Integer> list)
    {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

    public static List<Integer> ListOfOtherPlayers(Player[] players, int totalPlayers, String character){
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<totalPlayers;i++){
            if(!players[i].getCharacter().equals(character) && !players[i].getIsLost()){
                list.add(i);
            }
        }

        return list;
    }

    public static boolean mafiaRatio(int noOfMafia,int total){
        return noOfMafia == total && noOfMafia !=0 ;
    }



}
