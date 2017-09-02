/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015  Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2016 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.shatteredpixel.spd2d.levels.rooms.special;

import com.shatteredpixel.spd2d.ShatteredPixelDungeon;
import com.shatteredpixel.spd2d.actors.blobs.WaterOfAwareness;
import com.shatteredpixel.spd2d.actors.blobs.WaterOfHealth;
import com.shatteredpixel.spd2d.actors.blobs.WaterOfTransmutation;
import com.shatteredpixel.spd2d.actors.blobs.WellWater;
import com.shatteredpixel.spd2d.levels.Level;
import com.shatteredpixel.spd2d.levels.painters.Painter;
import com.shatteredpixel.spd2d.levels.Terrain;
import com.watabou.utils.Point;
import com.watabou.utils.Random;

public class MagicWellRoom extends SpecialRoom {

	private static final Class<?>[] WATERS =
		{WaterOfAwareness.class, WaterOfHealth.class, WaterOfTransmutation.class};

    public Class<?extends WellWater> overrideWater = null;
	
	public void paint( Level level ) {

		Painter.fill( level, this, Terrain.WALL );
		Painter.fill( level, this, 1, Terrain.EMPTY );
		
		Point c = center();
		Painter.set( level, c.x, c.y, Terrain.WELL );
		
		@SuppressWarnings("unchecked")
		Class<? extends WellWater> waterClass =
                overrideWater != null ?
                        overrideWater :
			(Class<? extends WellWater>)Random.element( WATERS );
			
		if (waterClass == WaterOfTransmutation.class) {
            SpecialRoom.disableGuaranteedWell();
		}
		
		WellWater water = (WellWater)level.blobs.get( waterClass );
		if (water == null) {
			try {
				water = waterClass.newInstance();
			} catch (Exception e) {
				ShatteredPixelDungeon.reportException(e);
				return;
			}
		}
		water.seed( level, c.x + level.width() * c.y, 1 );
		level.blobs.put( waterClass, water );
		
		entrance().set( Door.Type.REGULAR );
	}
}