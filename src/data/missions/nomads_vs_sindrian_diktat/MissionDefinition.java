package data.missions.nomads_vs_sindrian_diktat;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.fleet.FleetGoal;
import com.fs.starfarer.api.fleet.FleetMemberType;
import com.fs.starfarer.api.mission.FleetSide;
import com.fs.starfarer.api.mission.MissionDefinitionAPI;
import com.fs.starfarer.api.mission.MissionDefinitionPlugin;
import data.scripts.plugins.TheNomadsCombatEnginePlugin;


public class MissionDefinition implements MissionDefinitionPlugin
{
  @Override
  public void defineMission(MissionDefinitionAPI api)
  {
    //api.addPlugin(new TheNomadsCombatEnginePlugin());
    
    // Set up the fleets
    api.initFleet(FleetSide.PLAYER, "NFS", FleetGoal.ATTACK, false);
    api.setFleetTagline(FleetSide.PLAYER, "The Nomad Armada");

    api.initFleet(FleetSide.ENEMY, "SDS", FleetGoal.ATTACK, true);
    api.setFleetTagline(FleetSide.ENEMY, "Sindrian Diktat Planetary Fortress Fleet");

    // 528 point fleet
    api.addToFleet(FleetSide.PLAYER, "nom_oasis_standard", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.PLAYER, "nom_gila_monster_antibattleship", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.PLAYER, "nom_gila_monster_antibattleship", FleetMemberType.SHIP, true);
		api.addToFleet(FleetSide.PLAYER, "nom_queen_bee_attack", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.PLAYER, "nom_sandstorm_assault", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.PLAYER, "nom_sandstorm_assault", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.PLAYER, "nom_rattlesnake_assault", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.PLAYER, "nom_rattlesnake_assault", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.PLAYER, "nom_rattlesnake_assault", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.PLAYER, "nom_rattlesnake_assault", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.PLAYER, "nom_rattlesnake_assault", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.PLAYER, "nom_scorpion_assault", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.PLAYER, "nom_scorpion_assault", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.PLAYER, "nom_scorpion_assault", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.PLAYER, "nom_komodo_royal_vanguard", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.PLAYER, "nom_komodo_mk2_assault", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.PLAYER, "nom_komodo_mk2_assault", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.PLAYER, "nom_komodo_assault", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.PLAYER, "nom_komodo_assault", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.PLAYER, "nom_komodo_assault", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.PLAYER, "nom_komodo_assault", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.PLAYER, "nom_komodo_assault", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.PLAYER, "nom_roadrunner_pursuit", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.PLAYER, "nom_flycatcher_scarab", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.PLAYER, "nom_flycatcher_toad", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.PLAYER, "nom_flycatcher_iguana", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.PLAYER, "nom_death_bloom_strike", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.PLAYER, "nom_death_bloom_strike", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.PLAYER, "nom_yellowjacket_sniper", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.PLAYER, "nom_yellowjacket_sniper", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.PLAYER, "nom_yellowjacket_sniper", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.PLAYER, "nom_wurm_royal_vanguard", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.PLAYER, "nom_wurm_assault", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.PLAYER, "nom_wurm_assault", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.PLAYER, "nom_wurm_assault", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.PLAYER, "nom_wurm_assault", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.PLAYER, "nom_wurm_assault", FleetMemberType.SHIP, true);

    // 551 point fleet
    api.addToFleet(FleetSide.ENEMY, "conquest_Elite", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.ENEMY, "conquest_Elite", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.ENEMY, "conquest_Elite", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.ENEMY, "apogee_Balanced", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.ENEMY, "eagle_Assault", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.ENEMY, "eagle_Assault", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.ENEMY, "eagle_Balanced", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.ENEMY, "eagle_Balanced", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.ENEMY, "falcon_Attack", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.ENEMY, "falcon_Attack", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.ENEMY, "falcon_Attack", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.ENEMY, "falcon_CS", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.ENEMY, "falcon_CS", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.ENEMY, "falcon_CS", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.ENEMY, "gemini_Standard", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.ENEMY, "gemini_Standard", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.ENEMY, "gemini_Standard", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.ENEMY, "gemini_Standard", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.ENEMY, "gemini_Standard", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.ENEMY, "sunder_CS", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.ENEMY, "sunder_CS", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.ENEMY, "sunder_CS", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.ENEMY, "hammerhead_Elite", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.ENEMY, "hammerhead_Elite", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.ENEMY, "hammerhead_Elite", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.ENEMY, "hammerhead_Balanced", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.ENEMY, "hammerhead_Balanced", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.ENEMY, "hammerhead_Balanced", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.ENEMY, "hammerhead_Balanced", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.ENEMY, "hammerhead_Balanced", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.ENEMY, "vigilance_Standard", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.ENEMY, "vigilance_Standard", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.ENEMY, "vigilance_Standard", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.ENEMY, "vigilance_Standard", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.ENEMY, "vigilance_Standard", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.ENEMY, "brawler_Elite", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.ENEMY, "brawler_Elite", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.ENEMY, "brawler_Elite", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.ENEMY, "brawler_Assault", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.ENEMY, "brawler_Assault", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.ENEMY, "brawler_Assault", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.ENEMY, "brawler_Assault", FleetMemberType.SHIP, true);
    api.addToFleet(FleetSide.ENEMY, "brawler_Assault", FleetMemberType.SHIP, true);

    // Set up the map.
    float width = 20000f;
    float height = 26000f;
    api.initMap((float) -width / 2f, (float) width / 2f, (float) -height / 2f, (float) height / 2f);

    float minX = -width / 2;
    float minY = -height / 2;

    for (int i = 0; i < 50; i++) {
        float x = (float) Math.random() * width - width / 2;
        float y = (float) Math.random() * height - height / 2;
        float radius = 100f + (float) Math.random() * 400f;
        api.addNebula(x, y, radius);
    }

    // Add objectives
    api.addObjective(minX + width * 0.25f, minY + height * 0.25f, "nav_buoy");
    api.addObjective(minX + width * 0.75f, minY + height * 0.25f, "comm_relay");
    api.addObjective(minX + width * 0.75f, minY + height * 0.75f, "nav_buoy");
    api.addObjective(minX + width * 0.25f, minY + height * 0.75f, "comm_relay");
    api.addObjective(minX + width * 0.5f, minY + height * 0.5f, "sensor_array");

    // // Add a randomized asteroid field, maybe
    // if ((float) Math.random() > 0.5f) {
    // 	api.addAsteroidField(10f + (float) Math.random() * (width - 20f), 10f + (float) Math.random() * (height - 20f), (float) Math.random() * 360f, (float) Math.random() * 1000 + 1000,
    // 						 20f, 70f, (int) ((float) Math.random() * 100 + 50));
    // }
  }

}
