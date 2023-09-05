package com.mountblue.IPLProject;


import com.mountblue.IPLProject.Model.Delivery;
import com.mountblue.IPLProject.Model.Match;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@SpringBootApplication
public class IplProjectApplication
{
	private static final int MATCH_ID = 0;
	private static final int MATCH_YEAR = 1;
	private static final int MATCH_CITY = 2;
	private static final int MATCH_DATE = 3;
	private static final int TEAM_1 = 4;
	private static final int TEAM_2 = 5;
	private static final int TOSS_WON = 6;
	private static final int TOSS_DECISION = 7;
	private static final int RESULT = 8;
	private static final int DL_APPLIED = 9;
	private static final int TEAM_WON = 10;
	private static final int WIN_BY_RUN = 11;
	private static final int WIN_BY_WICKET = 12;
	private static final int PLAYER_OF_MATCH = 13;
	private static final int MATCH_VENUE = 14;

	private static final int INNING = 1;
	private static final int BATTING_TEAM = 2;
	private static final int BOWLING_TEAM = 3;
	private static final int OVER = 4;
	private static final int BALL = 5;
	private static final int BATSMAN = 6;
	private static final int NON_STRIKER = 7;
	private static final int BOWLER = 8;
	private static final int IS_SUPER_OVER = 9;
	private static final int WIDE_RUNS = 10;
	private static final int BYE_RUNS = 11;
	private static final int LEG_BYE_RUNS = 12;
	private static final int NO_BALL_RUNS = 13;
	private static final int PENALTY_RUNS = 14;
	private static final int BATSMAN_RUNS = 15;
	private static final int EXTRA_RUNS = 16;
	private static final int TOTAL_RUNS = 17;
	private static final int PLAYER_DISMISSED = 18;
	private static final int DISMISSAL_KIND = 19;
	private static final int FIELDER = 20;


	public static void main(String[] args) throws IOException
	{

		SpringApplication.run(IplProjectApplication.class, args);
		List<Match> matches = readMatchData();
		numberOfMatchesPerYear(matches);
		numberOfMatchesWonByTeam(matches);
		List<Delivery> deliveries = readDeliveryData();
		extraRunsConcededPerTeam(deliveries, matches);
		topEconomicalBowlers(deliveries, matches);
		maximumSixesByBatsmanByVenue(deliveries, matches);
	}

	public static void maximumSixesByBatsmanByVenue(List<Delivery> deliveries, List<Match> matches)
	{
		Map<Integer, String> idAndVenueMap = new HashMap<>();
		for (Match m : matches)
		{
			idAndVenueMap.put(m.getId(), m.getVenue());
		}

		Map<Integer, List<Map.Entry<String, Integer>>> batsmanSixesListCorrespondingVenue = new HashMap<>();

	}


	public static void topEconomicalBowlers(List<Delivery> deliveries, List<Match> matches)
	{
		Map<String, Integer> totalRunsByBowlers = new HashMap<>();
        Map<String, Integer> totalDeliveriesByBowlers = new HashMap<>();
        Map<String, Double> economyByBowlers = new HashMap<>();

		for (Match m : matches)
		{
			if (m.getYear() == 2015)
			{
				for (Delivery d : deliveries)
				{
					if(d.getMatchId() == m.getId())
					{
						String bowler = d.getBowler();
						int runs = d.getTotalRuns();
						totalRunsByBowlers.put(bowler, totalRunsByBowlers.getOrDefault(bowler, 0)+runs);
						totalDeliveriesByBowlers.put(bowler, totalDeliveriesByBowlers.getOrDefault(bowler, 0)+1);
					}
				}
			}
		}
		for (String bowler : totalRunsByBowlers.keySet())
		{
			int runs = totalRunsByBowlers.get(bowler);
			int delivery = totalDeliveriesByBowlers.get(bowler);
			double economy = ((double) runs/(double) delivery )*6;
			economyByBowlers.put(bowler, economy);
		}
		List<Map.Entry<String, Double>> entryList = new ArrayList<>(economyByBowlers.entrySet());
		entryList.sort(Map.Entry.comparingByValue());
		Map<String, Double> sortedMap = new LinkedHashMap<>();
		for(Map.Entry<String, Double> e : entryList)
		{
			sortedMap.put(e.getKey(), e.getValue());
		}
		System.out.println(sortedMap);
	}

	public static void extraRunsConcededPerTeam(List<Delivery> deliveries, List<Match> matches)
	{
		Map<String, Integer> extraRunsByTeam = new HashMap<>();

		for (Match m : matches)
		{
			if(m.getYear() == 2016)
			{
				for (Delivery d : deliveries)
				{
					if (m.getId() == d.getMatchId())
					{
						extraRunsByTeam.put(d.getBowlingTeam(), extraRunsByTeam.getOrDefault(d.getBowlingTeam(), 0)+d.getExtraRun());
					}
				}
			}
		}
		System.out.println(extraRunsByTeam);
	}

	public static List<Delivery> readDeliveryData() throws IOException {
		List<Delivery> deliveries = new ArrayList<>();
		String line = "";
		try
		{
			BufferedReader br =
					new BufferedReader(new FileReader("src/main/java/com/mountblue/IPLProject/deliveries.csv"));
			boolean flag = false;
			int i = 1;
			while ((line=br.readLine()) != null)
			{
				String[] data = line.split(",");
				if (flag)
				{
					Delivery delivery = new Delivery();
					delivery.setId(i);
					delivery.setMatchId(Integer.parseInt(data[MATCH_ID]));
					delivery.setInning(Integer.parseInt(data[INNING]));
					delivery.setBattingTeam(data[BATTING_TEAM]);
					delivery.setBowlingTeam(data[BOWLING_TEAM]);
					delivery.setOver(Integer.parseInt(data[OVER]));
					delivery.setBall(Integer.parseInt(data[BALL]));
					delivery.setBatsman(data[BATSMAN]);
					delivery.setNonStriker(data[NON_STRIKER]);
					delivery.setBowler(data[BOWLER]);
					delivery.setIsSuperOver(Integer.parseInt(data[IS_SUPER_OVER]));
					delivery.setWideRuns((Integer.parseInt(data[WIDE_RUNS])));
					delivery.setByeRuns(Integer.parseInt(data[BYE_RUNS]));
					delivery.setLegByeRuns(Integer.parseInt(data[LEG_BYE_RUNS]));
					delivery.setNoBallRuns(Integer.parseInt(data[NO_BALL_RUNS]));
					delivery.setPenaltyRuns(Integer.parseInt(data[PENALTY_RUNS]));
					delivery.setBatsmanRuns(Integer.parseInt(data[BATSMAN_RUNS]));
					delivery.setExtraRun(Integer.parseInt(data[EXTRA_RUNS]));
					delivery.setTotalRuns(Integer.parseInt(data[TOTAL_RUNS]));
					if(data.length-1 >= PLAYER_DISMISSED)
						delivery.setPlayerDismissed(data[PLAYER_DISMISSED]);
					if(data.length-1 >= DISMISSAL_KIND)
						delivery.setDismissalKind(data[DISMISSAL_KIND]);
					if(data.length-1 >= FIELDER)
						delivery.setFielder(data[FIELDER]);
					i++;
					deliveries.add(delivery);
				}
				flag = true;
			}
		} catch (FileNotFoundException e)
		{
			throw new FileNotFoundException();
		} catch (IOException e) {
			throw new IOException(e);
		}
		return deliveries;
	}

	public static void numberOfMatchesWonByTeam(List<Match> matches)
	{
		Map<String, Integer> numberOfMatchesWonByTeam = new HashMap<>();
		for(Match m : matches)
		{
			if(m.teamWon != "")
				numberOfMatchesWonByTeam.put(m.getTeamWon(), numberOfMatchesWonByTeam.getOrDefault(m.getTeamWon(), 0)+1);
			else
				numberOfMatchesWonByTeam.put("No result", numberOfMatchesWonByTeam.getOrDefault("No result", 0)+1);

		}
		System.out.println(numberOfMatchesWonByTeam);
	}

	public static void numberOfMatchesPerYear(List<Match> matches)
	{
		Map<Integer, Integer> matchesPlayedPerYear = new HashMap<>();
		for (Match m : matches)
		{
			matchesPlayedPerYear.put(m.getYear(), matchesPlayedPerYear.getOrDefault(m.getYear(),0)+1);
		}
		System.out.println(matchesPlayedPerYear);
	}

	public static List<Match> readMatchData() throws FileNotFoundException
	{
		List<Match> matches = new ArrayList<>();
		try
		{
			String line = "";
			BufferedReader br =
					new BufferedReader(new FileReader("src/main/java/com/mountblue/IPLProject/matches.csv"));
			boolean flag = false;
			while((line=br.readLine()) != null)
			{
				String data[] = line.split(",");
				if (flag)
				{
					Match match = new Match();
					match.setId(Integer.parseInt(data[MATCH_ID]));
					match.setYear(Integer.parseInt(data[MATCH_YEAR]));
					match.setVenue(data[MATCH_CITY]);
					match.setDate(data[MATCH_DATE]);
					match.setTeam1(data[TEAM_1]);
					match.setTeam2(data[TEAM_2]);
					match.setToss(data[TOSS_WON]);
					match.setTossDecision(data[TOSS_WON]);
					match.setTossDecision(data[TOSS_DECISION]);
					match.setResult(data[RESULT]);
					match.setDlApplied(data[DL_APPLIED]);
					match.setTeamWon(data[TEAM_WON]);
					match.setWinByRun(data[WIN_BY_RUN]);
					match.setWinByWicket(data[WIN_BY_WICKET]);
					match.setPlayerOfMatch(data[PLAYER_OF_MATCH]);
					match.setVenue(data[MATCH_VENUE]);
					matches.add(match);
				}
				flag = true;
			}
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
		return matches;
	}
}
