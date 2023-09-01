package com.mountblue.IPLProject;

import com.mountblue.IPLProject.MatchService.MatchServices;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;

@SpringBootApplication
public class IplProjectApplication
{

	public static void main(String[] args) throws FileNotFoundException {

		SpringApplication.run(IplProjectApplication.class, args);
		MatchServices matchServices = new MatchServices();
		matchServices.readMatchData();
		matchServices.numberOfMatchesPerYear();
		matchServices.numberOfMatchesWonByTeams();
		matchServices.readDeliveriesData();
		matchServices.extraRunsConcededPerTeam();
		matchServices.topEconimcalBowlers();
		matchServices.noOfTossWonByTeams();
	}

}
