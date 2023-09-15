package com.mountblue.IPLProject;

import com.mountblue.IPLProject.Model.Delivery;
import com.mountblue.IPLProject.Model.Match;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class IplProjectApplicationTest
{
    IplProjectApplication i = new IplProjectApplication();
    List<Match> matches = i.readMatchData();
    List<Delivery> deliveries = i.readDeliveryData();

    IplProjectApplicationTest() throws IOException
    {
    }

    @Test
    void topEconomicalBowlers()
    {
        Map<String, Double> economyByBowlers = i.topEconomicalBowlers(deliveries, matches);
        assertAll(
                ()->assertTrue(economyByBowlers.containsKey("RN ten Doeschate")),
                ()->assertTrue(economyByBowlers.containsKey("J Yadav")),
                ()->assertTrue(economyByBowlers.containsKey("V Kohli")),
                ()->assertTrue(economyByBowlers.containsKey("R Ashwin")),
                ()->assertTrue(economyByBowlers.containsKey("S Nadeem")),
                ()->assertTrue(economyByBowlers.containsKey("Z Khan")),
                ()->assertTrue(economyByBowlers.containsKey("Parvez Rasool"))
        );
    }

    @Test
    void extraRunsConcededPerTeam()
    {
        Map<String, Integer> extraRunsByTeam = i.extraRunsConcededPerTeam(deliveries, matches);
        assertAll(
                ()->assertEquals(98, extraRunsByTeam.get("Gujarat Lions")),
                ()->assertEquals(102, extraRunsByTeam.get("Mumbai Indians")),
                ()->assertEquals(107, extraRunsByTeam.get("Sunrisers Hyderabad")),
                ()->assertEquals(100, extraRunsByTeam.get("Kings XI Punjab")),
                ()->assertEquals(106, extraRunsByTeam.get("Delhi Daredevils")),
                ()->assertEquals(108, extraRunsByTeam.get("Rising Pune Supergiants")),
                ()->assertEquals(122, extraRunsByTeam.get("Kolkata Knight Riders")),
                ()->assertEquals(156, extraRunsByTeam.get("Royal Challengers Bangalore"))
        );
    }

    @Test
    void numberOfMatchesWonByTeam()
    {
        Map<String, Integer> numberOfMatchesWonByTeam = i.numberOfMatchesWonByTeam(matches);
        assertAll(
                ()-> assertEquals(92, numberOfMatchesWonByTeam.get("Mumbai Indians")),
                ()-> assertEquals(42, numberOfMatchesWonByTeam.get("Sunrisers Hyderabad")),
                ()-> assertEquals(12, numberOfMatchesWonByTeam.get("Pune Warriors")),
                ()-> assertEquals(73, numberOfMatchesWonByTeam.get("Royal Challengers Bangalore")),
                ()-> assertEquals(3, numberOfMatchesWonByTeam.get("No result")),
                ()-> assertEquals(13, numberOfMatchesWonByTeam.get("Gujarat Lions")),
                ()-> assertEquals(10, numberOfMatchesWonByTeam.get("Rising Pune Supergiant")),
                ()-> assertEquals(6, numberOfMatchesWonByTeam.get("Kochi Tuskers Kerala")),
                ()-> assertEquals(70, numberOfMatchesWonByTeam.get("Kings XI Punjab")),
                ()-> assertEquals(29, numberOfMatchesWonByTeam.get("Deccan Chargers")),
                ()-> assertEquals(62, numberOfMatchesWonByTeam.get("Delhi Daredevils")),
                ()-> assertEquals(5, numberOfMatchesWonByTeam.get("Rising Pune Supergiants")),
                ()-> assertEquals(79, numberOfMatchesWonByTeam.get("Chennai Super Kings"))
        );
    }

    @Test
    void numberOfMatchesPerYear()
    {
        Map<Integer, Integer> noOfMatchesPlayedPerYear = i.numberOfMatchesPerYear(matches);
        assertAll(
                ()-> assertEquals(60, noOfMatchesPlayedPerYear.get(2016)),
                ()-> assertEquals(59, noOfMatchesPlayedPerYear.get(2017)),
                ()-> assertEquals(58, noOfMatchesPlayedPerYear.get(2008)),
                ()-> assertEquals(57, noOfMatchesPlayedPerYear.get(2009)),
                ()-> assertEquals(60, noOfMatchesPlayedPerYear.get(2010)),
                ()-> assertEquals(73, noOfMatchesPlayedPerYear.get(2011)),
                ()-> assertEquals(74, noOfMatchesPlayedPerYear.get(2012)),
                ()-> assertEquals(76, noOfMatchesPlayedPerYear.get(2013)),
                ()-> assertEquals(60, noOfMatchesPlayedPerYear.get(2014)),
                ()-> assertEquals(59, noOfMatchesPlayedPerYear.get(2015))
        );
    }
}