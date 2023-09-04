package com.mountblue.IPLProject.Model;

public class Match
{
    private int id;
    private int year;
    private String date;

    private String team1;
    private String team2;
    private String toss;
    private String tossDecision;
    private String result;
    String dlApplied;
    public String teamWon;
    private String winByRun;
    private String winByWicket;
    private String playerOfMatch;
    private String venue;
    private String city;

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getYear()
    {
        return year;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getTeam1()
    {
        return team1;
    }

    public void setTeam1(String team1)
    {
        this.team1 = team1;
    }

    public String getTeam2()
    {
        return team2;
    }

    public void setTeam2(String team2)
    {
        this.team2 = team2;
    }

    public String getToss()
    {
        return toss;
    }

    public void setToss(String toss)
    {
        this.toss = toss;
    }

    public String getTossDecision()
    {
        return tossDecision;
    }

    public void setTossDecision(String tossDecision)
    {
        this.tossDecision = tossDecision;
    }

    public String getResult()
    {
        return result;
    }

    public void setResult(String result)
    {
        this.result = result;
    }

    public String getDlApplied()
    {
        return dlApplied;
    }

    public void setDlApplied(String dlApplied)
    {
        this.dlApplied = dlApplied;
    }

    public String getTeamWon()
    {
        return teamWon;
    }

    public void setTeamWon(String teamWon)
    {
        this.teamWon = teamWon;
    }

    public String getWinByRun()
    {
        return winByRun;
    }

    public void setWinByRun(String winByRun)
    {
        this.winByRun = winByRun;
    }

    public String getWinByWicket()
    {
        return winByWicket;
    }

    public void setWinByWicket(String winByWicket)
    {
        this.winByWicket = winByWicket;
    }

    public String getPlayerOfMatch()
    {
        return playerOfMatch;
    }

    public void setPlayerOfMatch(String playerOfMatch)
    {
        this.playerOfMatch = playerOfMatch;
    }

    public String getVenue()
    {
        return venue;
    }

    public void setVenue(String venue)
    {
        this.venue = venue;
    }

}
