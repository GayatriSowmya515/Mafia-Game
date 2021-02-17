package com.company;

public class Player {

    private int HP;
    private String Character;
    private boolean isLost;

    public int getIndex() {
        return index;
    }

    private int index;


    public Player(int i) {
        //System.out.println("1");
        index = i;
        isLost = false;
        HP = 1000;
    }

    public Boolean getIsLost() {
        return isLost;
    }

    public void setIsLost(Boolean islost) {
        this.isLost = islost;
    }

    public String getCharacter() {
        return Character;
    }

    public int getHP() {
        return HP;
    }

    public void addHP(int HP) {
        this.HP = this.HP + HP;
    }

    public void setPerson(String character, int HP){
        this.HP = HP;
        this.Character = character;
    }


    public void setHP(int HP) {
        this.HP = 0;
    }
}
