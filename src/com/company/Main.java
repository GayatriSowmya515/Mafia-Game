package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


/*
    Name: Gayatri Sowmya
    Roll no : 18011P0513
    Btech 3rd year
    CSE IDP(Mtech)
 */



public class Main extends Function{
    public static void main(String[] args) {
           Scanner input  = new Scanner(System.in);
           System.out.println("Welcome To Mafia");

           System.out.print("Enter the number of players : ");
           int n = input.nextInt();
           //System.out.println("n: " +n);

               int ch;
               System.out.println("Choose a character :");
               System.out.println("1) Mafia\n" +
                       "2) Detective\n" +
                       "3) Healer\n" +
                       "4) Commoner\n" +
                       "5) Assign Randomly");

               ch = input.nextInt();

               while(ch==5){
                   ch = (int) (Math.random()*4 +1);
               }

               int numOfMafias = n/5;
               int numOfHealers = Math.max(1,n/10);
               int numOfDetectives = n/5;
               int numOfCommoners = n - numOfDetectives - numOfHealers - numOfMafias;
               //System.out.println("N:"+n);
               Player[] players = new Player[n];
               for(int i=0;i<n;i++){
                   players[i] = new Player(i);
               }
              //System.out.println("Players: "+ players[0].getHP());
               List<Integer> list = ListOfPlayers(players,n);
               for(int i=0;i<numOfMafias;i++){
                   int num = getRandomElement(list);
                   players[num].setPerson("Mafia",2500);
                   list.remove(Integer.valueOf(num));
               }

               for(int i=0;i<numOfDetectives;i++){
                   int num = getRandomElement(list);
                   players[num].setPerson("Detective",800);
                   list.remove(Integer.valueOf(num));
               }

               for(int i=0;i<numOfHealers;i++){
                   int num = getRandomElement(list);
                   players[num].setPerson("Healer",800);
                  // System.out.println("num: "+num+"/n "+"num");
                   list.remove(Integer.valueOf(num));
               }

               for(int i=0;i<numOfCommoners;i++){
                   int num = getRandomElement(list);
                   players[num].setPerson("Commoner",1000);
                   list.remove(Integer.valueOf(num));
               }

               int k = ch-1;
               List<Integer> list1= ListOfPlayers(players,n,characters[k]);
               //System.out.println("LELMENT: "+list1.size());
               int playerno = getRandomElement(list1);
               int k2 = playerno+1;
               System.out.println("You are Player"+k2);
               System.out.print("You are a "+characters[k]+".");

               if(ch!=4){
                   ch--;
                   System.out.print(" Other "+characters[ch]+"s are:");
                   for(int i=0;i<n;i++){
                       if(players[i].getCharacter().equals(characters[ch])&&i!=playerno){
                           int j=i+1;
                           System.out.print("Player"+j+", ");
                       }
                   }
               }
               else{
                   System.out.println();
               }
               Game.startGame(n,players,playerno);
    }
}
