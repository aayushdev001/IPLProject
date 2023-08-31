package com.mountblue.IPLProject.Model;

import javax.annotation.processing.Generated;

public class Delivery
{
    public int id;
    public int matchId;
    public String bowlingTeam;
    public String bowler;
    public int totalRuns;
    public int extraRun;

    public Delivery(int id, int matchId,  String bowlingTeam, String bowler, int totalRuns, int extraRun)
    {
        this.id = id;
        this.matchId = matchId;
        this.bowlingTeam = bowlingTeam;
        this.bowler = bowler;
        this.totalRuns = totalRuns;
        this.extraRun = extraRun;
    }
}
