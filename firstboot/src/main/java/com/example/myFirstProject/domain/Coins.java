package com.example.myFirstProject.domain;

/**
 * Created by serena on 3/25/17.
 */

public class Coins {

    private int id;

    private int userId;
    private int coins;

    public Coins() {}

    public Coins(int userId, int coins) {
        this.userId = userId;
        this.coins = coins;
    }

    public void setUserId(int userId) { this.userId = userId; }
    public int getUserId() { return userId; }
    public void setCoins(int coins) { this.coins = coins; }
    public int getCoins() { return coins; }
}
