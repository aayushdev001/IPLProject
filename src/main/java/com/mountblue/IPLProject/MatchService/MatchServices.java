package com.mountblue.IPLProject.MatchService;

import com.mountblue.IPLProject.Model.Delivery;
import com.mountblue.IPLProject.Model.Match;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MatchServices
{
    String line = "";
    Map<Integer, Match> matchMap = new HashMap<>();
    Map<Integer, Delivery> deliveryMap = new HashMap<>();
    public void readMatchData() throws FileNotFoundException
    {
        try
        {
            BufferedReader br =
                    new BufferedReader(new FileReader("src/main/java/com/mountblue/IPLProject/matches.csv"));
            boolean flag = false;
            while((line=br.readLine()) != null)
            {
                String data[] = line.split(",");
                if (flag)
                {
                    Match match = new Match(Integer.parseInt(data[0].trim()), Integer.parseInt(data[1]), data[4], data[5], data[6], data[10]);
                    matchMap.put(match.id, match);
                }
                flag = true;
            }
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        line = "";
    }
    public void numberOfMatchesPerYear()
    {
        Map<Integer, Integer> map = new HashMap<>();
        for(int id : matchMap.keySet())
        {
            map.put(matchMap.get(id).year, map.getOrDefault(matchMap.get(id).year,0)+1);
        }
        System.out.println("Number of matches per year");
        System.out.println(map);
    }
    public void numberOfMatchesWonByTeams()
    {
        Map<String, Integer> map = new HashMap<>();
        for (int id : matchMap.keySet())
        {
            String teamWon = matchMap.get(id).teamWon;
            if (!teamWon.isEmpty())
                map.put(teamWon, map.getOrDefault(teamWon, 0)+1);
            else
            {
                map.put("No result", map.getOrDefault("No result", 0)+1);
            }

        }
        System.out.println("Number of matches won by teams");
        System.out.println(map);
    }

    public void readDeliveriesData()
    {
        try
        {
            BufferedReader br =
                    new BufferedReader(new FileReader("src/main/java/com/mountblue/IPLProject/deliveries.csv"));
            boolean flag = false;
            int i = 1;
            while((line=br.readLine()) != null)
            {
                String data[] = line.split(",");
                if (flag)
                {
                    Delivery delivery = new Delivery(i, Integer.parseInt(data[0]), data[3], data[8], Integer.parseInt(data[17]), Integer.parseInt(data[16]));
                    deliveryMap.put(delivery.id, delivery);
                    i = i+1;
                }
                flag = true;
            }
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    public void extraRunsConcededPerTeam()
    {
        Map<String, Integer> map = new HashMap<>();
        for(int id : matchMap.keySet())
        {
            if(matchMap.get(id).year == 2016)
            {
                for(int id2 : deliveryMap.keySet())
                {
                    if(deliveryMap.get(id2).matchId == id)
                    {
                        String team = deliveryMap.get(id2).bowlingTeam;
                        int extraRuns = deliveryMap.get(id2).extraRun;
                        map.put(team, map.getOrDefault(team, 0)+extraRuns);
                    }
                }
            }
        }
        System.out.println("Extra runs conceded per team");
        System.out.println(map);
    }
    public void topEconimcalBowlers()
    {
        Map<String, Integer> map = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        for (int id : matchMap.keySet())
        {
            if (matchMap.get(id).year == 2015)
            {
                for(int id2 : deliveryMap.keySet())
                {
                    if(deliveryMap.get(id2).matchId == id)
                    {
                        String bowler = deliveryMap.get(id2).bowler;
                        int runs = deliveryMap.get(id2).totalRuns;
                        map.put(bowler, map.getOrDefault(bowler, 0)+runs);
                    }
                }
            }
        }
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(map.entrySet());
        entryList.sort(Map.Entry.comparingByValue());
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : entryList) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        System.out.println("Top economical bowlers");
        System.out.println(sortedMap);
    }
    public void noOfTossWonByTeams()
    {
        Map<String, Integer> map = new HashMap<>();

        for (int id : matchMap.keySet())
        {
            String tossWonBy = matchMap.get(id).toss;
            map.put(tossWonBy, map.getOrDefault(tossWonBy, 0)+1);
        }
        System.out.println("No of toss won by teams");
        System.out.println(map);
    }
}
