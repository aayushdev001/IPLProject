package com.mountblue.IPLProject.Model;

public class Match
{
    public int id;
    public int year;
    public String team1;
    public String team2;
    public String toss;
    public String teamWon;

    public Match(int id, int year, String team1, String team2, String toss, String teamWon)
    {
        this.id = id;
        this.year = year;
        this.team1 = team1;
        this.team2 = team2;
        this.toss = toss;
        this.teamWon = teamWon;
    }
}
