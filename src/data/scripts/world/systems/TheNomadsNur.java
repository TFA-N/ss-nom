package data.scripts.world.systems;

import java.awt.Color;
import java.util.Iterator;

import com.fs.starfarer.api.FactoryAPI;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.FleetDataAPI;
import com.fs.starfarer.api.campaign.JumpPointAPI;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.SectorGeneratorPlugin;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.EconomyAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.fleet.FleetMemberType;
import com.fs.starfarer.api.impl.campaign.ids.Conditions;
import com.fs.starfarer.api.impl.campaign.ids.StarTypes;
import com.fs.starfarer.api.impl.campaign.ids.Submarkets;
import com.fs.starfarer.api.util.Misc;

import data.scripts.trylobot.TrylobotUtils;
import data.scripts.world.armada.CampaignArmadaController;
import data.scripts.world.armada.CampaignArmadaController.CampaignArmadaControllerEvent;
import data.scripts.world.armada.CampaignArmadaController.CampaignArmadaControllerEventListener;
import data.scripts.world.armada.CampaignArmadaResourceSharingController;


@SuppressWarnings( "unchecked" )
public class TheNomadsNur implements SectorGeneratorPlugin, CampaignArmadaControllerEventListener
{
  private boolean colony_armada_feature_bit = true;
  
  private FactoryAPI factory;
  private SectorEntityToken station;
  
  private final Color factionColor = new Color(234,214,124,255);
  
  
  public TheNomadsNur() {
  }
  
  public TheNomadsNur( boolean colony_armada_feature_enabled ) {
    this.colony_armada_feature_bit = colony_armada_feature_enabled;
  }
  
  
  @Override
	public void generate( SectorAPI sector )
	{
		factory = Global.getFactory();
    // stars, planets, moons
		StarSystemAPI system = sector.createStarSystem( "Nur" );
    system.setLightColor( new Color( 185, 185, 240 )); // light color in entire system, affects all entities
		system.getLocation().set( 18000f, -900f );
		SectorEntityToken system_center_of_mass = system.initNonStarCenter();
    //
    PlanetAPI starA = system.addPlanet("nur_a", system_center_of_mass, "Nur-A", StarTypes.BLUE_GIANT, 90f, 1000f, 1500f, 30f);
    system.setStar(starA);
    system.addCorona(starA, 300f, 5f, 0f, 1f );
    //
    PlanetAPI starB = system.addPlanet("nur_b", system_center_of_mass, "Nur-B", StarTypes.RED_GIANT, 270f, 300f, 600f, 30f);
    system.setSecondary(starB);
    system.addCorona(starB, 50f, 5f, 0.05f, 0.5f);
    // planets
    PlanetAPI planet_I = system.addPlanet("nur_c", system_center_of_mass, "Naera", "desert", 45f, 300f, 8000f, 199f);
    system.addRingBand(planet_I, "misc", "rings_asteroids0", 256f, 0, Color.white, 256f, 630f, 30f);
    planet_I.setCustomDescriptionId("nom_planet_naera");
    planet_I.getSpec().setAtmosphereColor(new Color(160, 110, 45, 140));
    planet_I.getSpec().setCloudColor(new Color(255, 255, 255, 23));
    planet_I.getSpec().setTilt(15);
    planet_I.applySpecChanges();
    // moons
    PlanetAPI planet_I__moon_a = system.addPlanet("nur_d", planet_I, "Ixaith", "rocky_unstable", 0f, 60f, 800f, 67f);
    PlanetAPI planet_I__moon_b = system.addPlanet("nur_e", planet_I, "Ushaise", "rocky_ice", 45f, 45f, 1000f, 120f);
    PlanetAPI planet_I__moon_c = system.addPlanet("nur_f", planet_I, "Riaze", "barren", 90f, 100f, 1200f, 130f);
    PlanetAPI planet_I__moon_d = system.addPlanet("nur_g", planet_I, "Riaze-Tremn", "frozen", 135f, 35f, 1500f, 132f);
    PlanetAPI planet_I__moon_e = system.addPlanet("nur_h", planet_I, "Eufariz", "frozen", 180f, 65f, 1750f, 200f);
    PlanetAPI planet_I__moon_f = system.addPlanet("nur_i", planet_I, "Thumn", "rocky_ice", 225f, 100f, 2000f, 362f);
    // stations
    station = system.addOrbitalStation("nur_fabricator_station", 
      planet_I__moon_e, 180f, 300f, 50f, "Naeran Orbital Storage & Resupply", "nomads");
    station.addTag("station");
    station.setCircularOrbitPointingDown(system.getEntityById("nur_h"), 45, 300, 50);
    // market
    MarketAPI market = Global.getFactory().createMarket(station.getId()+"_market", station.getName(), 3);
    market.setSurveyLevel(MarketAPI.SurveyLevel.FULL);
    market.setFactionId("nomads");
    market.setPrimaryEntity(station);
    market.setBaseSmugglingStabilityValue(0);
    market.getTariff().modifyFlat("generator", 0.3f);
    // conditions
    market.addCondition(Conditions.FRONTIER);
    market.addCondition(Conditions.SPACEPORT);
    market.addCondition(Conditions.ORBITAL_STATION);
    market.addCondition(Conditions.VICE_DEMAND);
    market.addCondition(Conditions.POPULATION_2);
    market.addCondition(Conditions.SHIPBREAKING_CENTER);
    // submarkets
    market.addSubmarket(Submarkets.SUBMARKET_OPEN);
    market.addSubmarket(Submarkets.GENERIC_MILITARY);
    market.addSubmarket(Submarkets.SUBMARKET_BLACK);
    market.addSubmarket(Submarkets.SUBMARKET_STORAGE);
    Global.getSector().getEconomy().addMarket(market);
    station.setMarket(market);
    // hyperspace
    JumpPointAPI jumpPoint = factory.createJumpPoint("nur_jump_station", "Orbital Station Jump Point");
    jumpPoint.setOrbit(Global.getFactory().createCircularOrbit(planet_I__moon_e, 90f, 300f, 50f));
    jumpPoint.setStandardWormholeToHyperspaceVisual();
    system.addEntity(jumpPoint);
    //
    system.autogenerateHyperspaceJumpPoints(true, true);
    
    // relationships
		FactionAPI nomads_faction = sector.getFaction( "nomads" );
    for (FactionAPI cur_faction : sector.getAllFactions()) {
    	if( "nomads".equals(cur_faction.getId())
      ||  "independent".equals(cur_faction.getId())
      ||  "scavengers".equals(cur_faction.getId())
      ||  "neutral".equals(cur_faction.getId()) ) {
				nomads_faction.setRelationship( cur_faction.getId(), 1.00f );
      } else if( "pirates".equals(cur_faction.getId())) {
				nomads_faction.setRelationship( cur_faction.getId(), -0.65f );
      } else if( "hegemony".equals(cur_faction.getId())) {
				nomads_faction.setRelationship( cur_faction.getId(), -0.65f );
			} else {
				nomads_faction.setRelationship( cur_faction.getId(), 0.00f );
			}
		}
		
    // DEPRECATED
    //// armada formation
    //CampaignArmadaEscortFleetPositionerAPI armada_formation =
    //  new CampaignArmadaFormationOrbit(
    //    sector,
    //    300.0f, // orbitRadius
    //    1.0f, // orbitDirection
    //    0.8f // orbitPeriodDays
    //  );
    
		// armada (leader fleet + escorts) controller script
		String[] escort_pool = { 
			"scout", 
			"longRangeScout", 
			"battleGroup", 
			"assassin", 
			"royalGuard", 
			"jihadFleet", 
			"carrierGroup",
			"royalCommandFleet"
		};
		int[] escort_weights = {    
			220,
			200,
			230,
			185,
			175,
			125,
			200,
			100
		};
		CampaignArmadaController nomad_armada =
			new CampaignArmadaController(
        //////////////////////////
        // spawn info
				"nomads", // faction
				"colonyFleet", // leader/VIP fleet
				"nom_oasis", // flagship of flagships
				sector, // global sector api
				planet_I__moon_f, // spawn location
        station.getMarket(), // market
        //////////////////////////
				// escort fleet info
        8, // escort_fleet_count
				escort_pool,
				escort_weights,
        500f, // "leash" length
        //////////////////////////
        // waypoint info
				1, // waypoint_per_trip_minimum
				6, // waypoint_per_trip_maximum
				30 // dead_time_days (idle time per waypoint)
        //////////////////////////
			);
		sector.addScript( nomad_armada );
		nomad_armada.addListener( this );
		
		// armada resource pooling script
		CampaignArmadaResourceSharingController armada_resource_pool = 
			new CampaignArmadaResourceSharingController( 
				sector, 
				nomad_armada,
				3.0f, // 3 days at fleet's current usage (whatever it happens to be)
				0.10f, // skeleton crew requirement, plus 10%
				3.0f, // 5 light-years worth of fuel at fleet's current fuel consumption rate
				12.0f, // 12 days at fleet's current usage (whatever it happens to be)
				0.50f, // skeleton crew requirement, plus 25%
				20.0f // 15 light-years worth of fuel at fleet's current fuel consumption rate
			);
		sector.addScript( armada_resource_pool );
        
		// restocker script
		StockDescriptor[] restock = {
      //
      new StockDescriptor(StockDescriptor.HULLMOD_SPEC, "fluxbreakers", 1, 1f),
      new StockDescriptor(StockDescriptor.HULLMOD_SPEC, "fluxcoil", 1, 1f),
      new StockDescriptor(StockDescriptor.HULLMOD_SPEC, "unstable_injector", 1, 1f),
      new StockDescriptor(StockDescriptor.HULLMOD_SPEC, "recovery_shuttles", 1, 1f),
      new StockDescriptor(StockDescriptor.HULLMOD_SPEC, "expanded_deck_crew", 1, 1f),
      new StockDescriptor(StockDescriptor.HULLMOD_SPEC, "magazines", 1, 1f),
      new StockDescriptor(StockDescriptor.HULLMOD_SPEC, "targetingunit", 1, 1f),
      new StockDescriptor(StockDescriptor.HULLMOD_SPEC, "heavyarmor", 1, 1f),
      new StockDescriptor(StockDescriptor.HULLMOD_SPEC, "armoredweapons", 1, 1f),
      new StockDescriptor(StockDescriptor.HULLMOD_SPEC, "turretgyros", 1, 1f),
      new StockDescriptor(StockDescriptor.HULLMOD_SPEC, "blast_doors", 1, 1f),
      new StockDescriptor(StockDescriptor.HULLMOD_SPEC, "reinforcedhull", 1, 1f),
      new StockDescriptor(StockDescriptor.HULLMOD_SPEC, "autorepair", 1, 1f),
      new StockDescriptor(StockDescriptor.HULLMOD_SPEC, "unstable_injector", 1, 1f),
      new StockDescriptor(StockDescriptor.HULLMOD_SPEC, "nav_relay", 1, 1f),
      new StockDescriptor(StockDescriptor.HULLMOD_SPEC, "missleracks", 1, 1f),
      new StockDescriptor(StockDescriptor.HULLMOD_SPEC, "eccm", 1, 1f),
      new StockDescriptor(StockDescriptor.HULLMOD_SPEC, "auxiliarythrusters", 1, 1f),
      //
      new StockDescriptor(StockDescriptor.SHIP, "nom_gila_monster_antibattleship", 1, 11f),
      new StockDescriptor(StockDescriptor.SHIP, "nom_queen_bee_attack", 1, 11f),
      new StockDescriptor(StockDescriptor.SHIP, "nom_sandstorm_assault", 2, 8f),
      new StockDescriptor(StockDescriptor.SHIP, "nom_rattlesnake_assault", 3, 8f),
      new StockDescriptor(StockDescriptor.SHIP, "nom_scorpion_royal_vanguard", 1, 3f),
      new StockDescriptor(StockDescriptor.SHIP, "nom_scorpion_assault", 3, 3f),
      new StockDescriptor(StockDescriptor.SHIP, "nom_komodo_p_overdriven", 1, 5f),
      new StockDescriptor(StockDescriptor.SHIP, "nom_komodo_royal_vanguard", 1, 5f),
      new StockDescriptor(StockDescriptor.SHIP, "nom_komodo_mk2_assault", 3, 3f),
      new StockDescriptor(StockDescriptor.SHIP, "nom_komodo_assault", 4, 3f),
      new StockDescriptor(StockDescriptor.SHIP, "nom_roadrunner_pursuit", 4, 3f),
      new StockDescriptor(StockDescriptor.SHIP, "nom_flycatcher_fang", 2, 1f),
      new StockDescriptor(StockDescriptor.SHIP, "nom_flycatcher_iguana", 1, 1f),
      new StockDescriptor(StockDescriptor.SHIP, "nom_flycatcher_ant", 2, 1f),
      new StockDescriptor(StockDescriptor.SHIP, "nom_flycatcher_toad", 1, 1f),
      new StockDescriptor(StockDescriptor.SHIP, "nom_yellowjacket_sniper", 4, 1f),
      new StockDescriptor(StockDescriptor.SHIP, "nom_death_bloom_strike", 2, 1f),
      new StockDescriptor(StockDescriptor.SHIP, "nom_dust_devil_assault", 2, 3f),
      new StockDescriptor(StockDescriptor.SHIP, "nom_wurm_royal_vanguard", 1, 3f),
      new StockDescriptor(StockDescriptor.SHIP, "nom_wurm_assault", 4, 1f),
      new StockDescriptor(StockDescriptor.SHIP, "nom_cobra_personnel", 1, 2f),
      new StockDescriptor(StockDescriptor.SHIP, "nom_tortoise_freighter", 1, 2f),
      new StockDescriptor(StockDescriptor.SHIP, "nom_cactus_tanker", 1, 2f),
      new StockDescriptor(StockDescriptor.SHIP, "nom_willow_salvage", 1, 2f),
      new StockDescriptor(StockDescriptor.SHIP, "nom_dragonfly_tug", 1, 2f),
      new StockDescriptor(StockDescriptor.SHIP, "nom_leaf_probe", 1, 2f),
      //
      new StockDescriptor(StockDescriptor.FIGHTER_LPC, "nom_fang_wing", 2, 1f),
      new StockDescriptor(StockDescriptor.FIGHTER_LPC, "nom_tarantula_wing", 2, 1f),
      new StockDescriptor(StockDescriptor.FIGHTER_LPC, "nom_toad_wing", 2, 1f),
      new StockDescriptor(StockDescriptor.FIGHTER_LPC, "nom_iguana_wing", 2, 1f),
      new StockDescriptor(StockDescriptor.FIGHTER_LPC, "nom_ant_wing", 2, 1f)
    };
		TheNomadsNurStationRestocker station_cargo_restocker
      = new TheNomadsNurStationRestocker( restock, station, Submarkets.GENERIC_MILITARY );
		system.addScript( station_cargo_restocker );
    
    
	}
	
  @Override
	public void handle_event( CampaignArmadaControllerEvent event )
	{
    if (station == null || station.getMarket() == null
    ||  station.getMarket().getSubmarket("open_market") == null)
      return;
    
    SubmarketAPI open_market = station.getMarket().getSubmarket("open_market");
    // Oasis is not in play; put it for sale at the station (yay!)
		if( "NON_EXISTENT".equals( event.controller_state ))
		{
			// add no more than one Oasis
			int count = 0; // first count oasis ships (player could have bought one previously and sold it back)
			FleetDataAPI station_ships = open_market.getCargo().getMothballedShips();
			for( Iterator i = station_ships.getMembersInPriorityOrder().iterator(); i.hasNext(); )
			{
				FleetMemberAPI ship = (FleetMemberAPI)i.next();
				if( "nom_oasis".equals( ship.getHullId() ))
					++count;
			}
			if( count == 0 )
			{
				station_ships.addFleetMember( factory.createFleetMember( FleetMemberType.SHIP, "nom_oasis_standard" ));
				TrylobotUtils.debug("added OASIS to station cargo");
			}
		}
		// Oasis is in play; be patient! T_T
		else if( "JOURNEYING_LIKE_A_BOSS".equals( event.controller_state ))
		{
			// remove all Oasis hulls, there's only supposed to be one, and it's cruising around.
			FleetDataAPI station_ships = open_market.getCargo().getMothballedShips();
			for( Iterator i = station_ships.getMembersInPriorityOrder().iterator(); i.hasNext(); )
			{
				FleetMemberAPI ship = (FleetMemberAPI)i.next();
				if( "nom_oasis".equals( ship.getHullId() ))
				{
					station_ships.removeFleetMember( ship );
					TrylobotUtils.debug("removed OASIS from station cargo");
				}
			}
		}
	}
}
