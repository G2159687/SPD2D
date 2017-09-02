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
package com.shatteredpixel.spd2d.items.scrolls;

import com.shatteredpixel.spd2d.Assets;
import com.shatteredpixel.spd2d.Dungeon;
import com.shatteredpixel.spd2d.actors.buffs.Amok;
import com.shatteredpixel.spd2d.actors.buffs.Buff;
import com.shatteredpixel.spd2d.actors.buffs.Invisibility;
import com.shatteredpixel.spd2d.actors.mobs.Mimic;
import com.shatteredpixel.spd2d.actors.mobs.Mob;
import com.shatteredpixel.spd2d.effects.Speck;
import com.shatteredpixel.spd2d.items.Heap;
import com.shatteredpixel.spd2d.levels.Level;
import com.shatteredpixel.spd2d.messages.Messages;
import com.shatteredpixel.spd2d.utils.GLog;
import com.watabou.noosa.audio.Sample;

public class ScrollOfRage extends Scroll {

	{
		initials = 6;
	}

	@Override
	public void doRead() {

		for (Mob mob : Dungeon.level.mobs.toArray( new Mob[0] )) {
			mob.beckon( curUser.pos );
			if (Level.fieldOfView[mob.pos]) {
				Buff.prolong(mob, Amok.class, 5f);
			}
		}

		for (Heap heap : Dungeon.level.heaps.values()) {
			if (heap.type == Heap.Type.MIMIC) {
				Mimic m = Mimic.spawnAt( heap.pos, heap.items );
				if (m != null) {
					m.beckon( curUser.pos );
					heap.destroy();
				}
			}
		}

		GLog.w( Messages.get(this, "roar") );
		setKnown();
		
		curUser.sprite.centerEmitter().start( Speck.factory( Speck.SCREAM ), 0.3f, 3 );
		Sample.INSTANCE.play( Assets.SND_CHALLENGE );
		Invisibility.dispel();

		readAnimation();
	}

	@Override
	public void empoweredRead() {
		for (Mob mob : Dungeon.level.mobs.toArray( new Mob[0] )) {
			if (Level.fieldOfView[mob.pos]) {
				Buff.prolong(mob, Amok.class, 5f);
			}
		}

		setKnown();

		curUser.sprite.centerEmitter().start( Speck.factory( Speck.SCREAM ), 0.3f, 3 );
		Sample.INSTANCE.play( Assets.SND_READ );
		Invisibility.dispel();

		readAnimation();
	}

	@Override
	public int price() {
		return isKnown() ? 30 * quantity : super.price();
	}
}
