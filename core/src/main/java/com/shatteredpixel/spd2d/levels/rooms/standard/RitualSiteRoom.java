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
package com.shatteredpixel.spd2d.levels.rooms.standard;

import com.shatteredpixel.spd2d.Assets;
import com.shatteredpixel.spd2d.items.quest.CeremonialCandle;
import com.shatteredpixel.spd2d.levels.Level;
import com.shatteredpixel.spd2d.levels.painters.Painter;
import com.shatteredpixel.spd2d.levels.Terrain;
import com.shatteredpixel.spd2d.messages.Messages;
import com.shatteredpixel.spd2d.ui.CustomTileVisual;
import com.watabou.utils.Point;

public class RitualSiteRoom extends StandardRoom {

    @Override
    public int minWidth() {
        return Math.max(super.minWidth(), 5);
    }

    @Override
    public int minHeight() {
        return Math.max(super.minHeight(), 5);
    }

	public void paint( Level level ) {

		for (Door door : connected.values()) {
			door.set( Door.Type.REGULAR );
		}

		Painter.fill(level, this, Terrain.WALL);
		Painter.fill(level, this, 1, Terrain.EMPTY);

		RitualMarker vis = new RitualMarker();
		Point c = center();
		vis.pos(c.x - 1, c.y - 1);

		level.customTiles.add(vis);

		Painter.fill(level, c.x-1, c.y-1, 3, 3, Terrain.EMPTY_DECO);

		level.addItemToSpawn(new CeremonialCandle());
		level.addItemToSpawn(new CeremonialCandle());
		level.addItemToSpawn(new CeremonialCandle());
		level.addItemToSpawn(new CeremonialCandle());

		CeremonialCandle.ritualPos = c.x + (level.width() * c.y);
	}

	public static class RitualMarker extends CustomTileVisual{

		{
			name = Messages.get(this, "name");

			tx = Assets.PRISON_QUEST;
			txX = txY = 0;
			tileW = tileH = 3;
		}

		@Override
		public String desc() {
			return Messages.get(this, "desc");
		}
	}

}
