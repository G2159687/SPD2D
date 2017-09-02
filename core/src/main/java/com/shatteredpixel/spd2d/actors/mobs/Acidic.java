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
package com.shatteredpixel.spd2d.actors.mobs;

import com.shatteredpixel.spd2d.Badges;
import com.shatteredpixel.spd2d.Dungeon;
import com.shatteredpixel.spd2d.actors.Char;
import com.shatteredpixel.spd2d.messages.Messages;
import com.shatteredpixel.spd2d.sprites.AcidicSprite;
import com.shatteredpixel.spd2d.utils.GLog;
import com.watabou.utils.Random;

public class Acidic extends Scorpio {

	{
		spriteClass = AcidicSprite.class;
	}
	
	@Override
	public int defenseProc( Char enemy, int damage ) {
		
		int dmg = Random.IntRange( 0, damage );
		if (dmg > 0) {
			enemy.damage( dmg, this );
            if (!enemy.isAlive() && enemy == Dungeon.hero) {
                Dungeon.fail(getClass());
                GLog.n(Messages.capitalize(Messages.get(Char.class, "kill", name)));
            }
		}
		
		return super.defenseProc( enemy, damage );
	}
	
	@Override
	public void die( Object cause ) {
		super.die( cause );
		Badges.validateRare( this );
	}
}