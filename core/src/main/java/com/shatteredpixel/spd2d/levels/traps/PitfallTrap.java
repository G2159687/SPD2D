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
package com.shatteredpixel.spd2d.levels.traps;

import com.shatteredpixel.spd2d.Dungeon;
import com.shatteredpixel.spd2d.actors.Actor;
import com.shatteredpixel.spd2d.actors.Char;
import com.shatteredpixel.spd2d.actors.mobs.Mob;
import com.shatteredpixel.spd2d.items.Heap;
import com.shatteredpixel.spd2d.items.Item;
import com.shatteredpixel.spd2d.levels.Level;
import com.shatteredpixel.spd2d.levels.Terrain;
import com.shatteredpixel.spd2d.levels.features.Chasm;
import com.shatteredpixel.spd2d.scenes.GameScene;
import com.watabou.utils.PathFinder;

public class PitfallTrap extends Trap {

	{
		color = RED;
		shape = DIAMOND;
	}

	@Override
	public void activate() {
		Heap heap = Dungeon.level.heaps.get( pos );

		if (heap != null){
			for (Item item : heap.items){
				Dungeon.dropToChasm(item);
			}
			heap.sprite.kill();
			GameScene.discard(heap);
			Dungeon.level.heaps.remove( pos );
		}

		Char ch = Actor.findChar( pos );

		if (ch == Dungeon.hero){
			Chasm.heroFall( pos );
		} else if (ch != null){
			Chasm.mobFall((Mob)ch);
		}
	}

	@Override
	protected void disarm() {
		super.disarm();

        int stateChanges = 0;
        boolean curPassable = Level.passable[pos + PathFinder.CIRCLE8[PathFinder.CIRCLE8.length-1]];
        for (int i : PathFinder.CIRCLE8){
            if (curPassable != Level.passable[pos + i]){
                stateChanges++;
                curPassable = Level.passable[pos + i];
            }
        }

		//if making a pit here wouldn't block any paths, make a pit tile instead of a disarmed trap tile.
        if (stateChanges <= 2){

			Level.set(pos, Terrain.CHASM);
			GameScene.updateMap( pos );
		}
	}
}
