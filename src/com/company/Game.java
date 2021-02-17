package com.company;

public class Game extends Function{

     private static boolean endGame = false;
     public static void remainingPlayersData(int total,int totalPlayers,Player players[]){
         System.out.print(total+ " players are remaining: ");
         for(int i=0;i<totalPlayers;i++){
             if(!players[i].getIsLost()){
                 int j=i+1;
                 System.out.print("Player"+j+", ");
             }
         }
         System.out.println("are alive");

     }
     public static void startGame(int totalPlayers, Player players[],int playerno){

         int remainingPlayers ;
         int Roundno = 1;
         while(!endGame){
             System.out.println("\nRound "+Roundno+": ");
             remainingPlayers = noOfPlayers(players,totalPlayers);
             remainingPlayersData(remainingPlayers,totalPlayers,players);
             GameLogic.Play(players, totalPlayers, playerno);

             System.out.println("End of Round"+Roundno+"\n\n");
             Roundno++;
             endGame = GameLogic.RoundEndData(players,totalPlayers);
         }

         GameLogic.GameEndData(players, totalPlayers,playerno);

     }
}
