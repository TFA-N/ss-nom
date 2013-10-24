package data.scripts.plugins;

import com.fs.starfarer.api.AnimationAPI;
import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.CombatEnginePlugin;
import com.fs.starfarer.api.combat.EveryFrameCombatPlugin;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipSystemAPI;
import com.fs.starfarer.api.combat.WeaponAPI;
import data.scripts.trylobot._;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import org.lwjgl.util.vector.Vector2f;

public class TheNomadsDamselflyTowCableFXPlugin implements CombatEnginePlugin, EveryFrameCombatPlugin
{
	private CombatEngineAPI engine;
	private HashMap tow_cable_to_anchor_map = new HashMap();
	private float accumulator = 0.0f;
	private static final float MIN_SEARCH_DELAY_SEC = 1.0f;
	
	public void init( CombatEngineAPI engine )
	{
		this.engine = engine;
	}

	public void advance( float amount, List events )
	{
		if( engine.isPaused() )
			return;
		accumulator += amount;
		if( accumulator < MIN_SEARCH_DELAY_SEC )
		{
			do_cheap_update();
		}
		else
		{
			accumulator -= MIN_SEARCH_DELAY_SEC;
			do_expensive_update();
			do_cheap_update();
		}
	}
	
	public void do_expensive_update()
	{
		tow_cable_to_anchor_map.clear();
		for( Iterator s = engine.getShips().iterator(); s.hasNext(); )
		{
			ShipAPI ship = (ShipAPI) s.next();
			if( ship == null || ship.isHulk() )
				continue;
			ShipSystemAPI system = ship.getSystem();
			if( !"nom_damselfly_drone".equals( system.getId() ))
				continue;
			// ship is alive and capable of launching damselflies
			for( Iterator d = ship.getDeployedDrones().iterator(); d.hasNext(); )
			{
				ShipAPI drone = (ShipAPI) d.next();
				if( !"nom_damselfly".equals( drone.getHullSpec().getHullId() ))
					continue;
				WeaponAPI tow_cable = get_tow_cable( drone );
				if( tow_cable == null )
					continue;
				WeaponAPI tow_anchor = get_nearest_tow_anchor( drone.getLocation(), ship );
				if( tow_anchor == null )
					continue;
				tow_cable_to_anchor_map.put( tow_cable, tow_anchor );
			}
		}
	}
	
	public void do_cheap_update()
	{
		for( Iterator t = tow_cable_to_anchor_map.entrySet().iterator(); t.hasNext(); )
		{
			Entry entry = (Entry) t.next();
			WeaponAPI tow_cable = (WeaponAPI) entry.getKey();
			WeaponAPI tow_anchor = (WeaponAPI) entry.getValue();
			float angle = _.get_angle( tow_cable.getLocation(), tow_anchor.getLocation() );
			tow_cable.setCurrAngle( angle );
			AnimationAPI anim = tow_cable.getAnimation();
			anim.setFrame( 1 );
			anim.pause();
		}
	}
	
	public WeaponAPI get_tow_cable( ShipAPI damselfly_drone )
	{
		for( Iterator w = damselfly_drone.getAllWeapons().iterator(); w.hasNext(); )
		{
			WeaponAPI weapon = (WeaponAPI) w.next();
			if( !"nom_damselfly_tow_cable".equals( weapon.getId() ))
				continue;
			return weapon;
		}
		return null;
	}
	
	public WeaponAPI get_nearest_tow_anchor( Vector2f tow_cable_source, ShipAPI ship_attached_to )
	{
		WeaponAPI closest_anchor = null;
		float closest_anchor_distance_squared = Float.MAX_VALUE;
		for( Iterator i = ship_attached_to.getAllWeapons().iterator(); i.hasNext(); )
		{
			WeaponAPI weapon = (WeaponAPI) i.next();
			if( "nom_tow_cable_anchor".equals( weapon.getId() ))
			{
				float distance_squared = _.get_distance_squared( tow_cable_source, weapon.getLocation() );
				if( distance_squared < closest_anchor_distance_squared )
				{
					closest_anchor = weapon;
					closest_anchor_distance_squared = distance_squared;
				}
			}
		}
		return closest_anchor;
	}
}
