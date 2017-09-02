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
import com.shatteredpixel.spd2d.effects.CellEmitter;
import com.shatteredpixel.spd2d.effects.Lightning;
import com.shatteredpixel.spd2d.effects.particles.SparkParticle;
import com.shatteredpixel.spd2d.items.Heap;
import com.shatteredpixel.spd2d.items.Item;
import com.shatteredpixel.spd2d.items.wands.Wand;
import com.shatteredpixel.spd2d.messages.Messages;
import com.shatteredpixel.spd2d.utils.GLog;
import com.watabou.noosa.Camera;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class LightningTrap extends Trap {

	{
		color = TEAL;
		shape = CROSSHAIR;
	}

	@Override
	public void activate() {

		Char ch = Actor.findChar( pos );

		if (ch != null) {
			ch.damage( Math.max( 1, Random.Int( ch.HP / 3, 2 * ch.HP / 3 ) ), LIGHTNING );
			if (ch == Dungeon.hero) {

				Camera.main.shake( 2, 0.3f );

				if (!ch.isAlive()) {
					Dungeon.fail( getClass() );
					GLog.n( Messages.get(this, "ondeath") );
				}
			}

			ArrayList<Lightning.Arc> arcs = new ArrayList<>();
			arcs.add(new Lightning.Arc(pos - Dungeon.level.width(), pos + Dungeon.level.width()));
			arcs.add(new Lightning.Arc(pos - 1, pos + 1));

			ch.sprite.parent.add( new Lightning( arcs, null ) );
		}

		Heap heap = Dungeon.level.heaps.get(pos);
		if (heap != null){
			Item item = heap.items.peek();
			if (item instanceof Wand){
				Wand wand = (Wand)item;
				((Wand)item).curCharges += (int)Math.ceil((wand.maxCharges - wand.curCharges)/2f);
			}
		}

		CellEmitter.center( pos ).burst( SparkParticle.FACTORY, Random.IntRange( 3, 4 ) );
	}

	public static final Electricity LIGHTNING = new Electricity();
	public static class Electricity {
	}
}
