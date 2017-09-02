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
package com.shatteredpixel.spd2d.items.armor.glyphs;

import com.shatteredpixel.spd2d.actors.Char;
import com.shatteredpixel.spd2d.actors.buffs.Buff;
import com.shatteredpixel.spd2d.actors.buffs.Roots;
import com.shatteredpixel.spd2d.effects.CellEmitter;
import com.shatteredpixel.spd2d.effects.particles.EarthParticle;
import com.shatteredpixel.spd2d.items.armor.Armor;
import com.shatteredpixel.spd2d.items.armor.Armor.Glyph;
import com.shatteredpixel.spd2d.plants.Earthroot;
import com.shatteredpixel.spd2d.sprites.ItemSprite;
import com.shatteredpixel.spd2d.sprites.ItemSprite.Glowing;
import com.watabou.noosa.Camera;
import com.watabou.utils.Random;

public class Entanglement extends Glyph {
	
	private static ItemSprite.Glowing BROWN = new ItemSprite.Glowing( 0x663300 );
	
	@Override
	public int proc( Armor armor, Char attacker, Char defender, int damage ) {

		int level = Math.max( 0, armor.level() );
		
		if (Random.Int( 4 ) == 0) {
			
			Buff.prolong( defender, Roots.class, 3 );
			Buff.affect( defender, Earthroot.Armor.class ).level( 5 + 2* level );
			CellEmitter.bottom( defender.pos ).start( EarthParticle.FACTORY, 0.05f, 8 );
			Camera.main.shake( 1, 0.4f );
			
		}

		return damage;
	}

	@Override
	public Glowing glowing() {
		return BROWN;
	}
		
}
