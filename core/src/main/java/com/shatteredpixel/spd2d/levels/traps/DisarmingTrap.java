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

import com.shatteredpixel.spd2d.Assets;
import com.shatteredpixel.spd2d.Dungeon;
import com.shatteredpixel.spd2d.actors.hero.Hero;
import com.shatteredpixel.spd2d.effects.CellEmitter;
import com.shatteredpixel.spd2d.effects.Speck;
import com.shatteredpixel.spd2d.items.Heap;
import com.shatteredpixel.spd2d.items.Item;
import com.shatteredpixel.spd2d.items.KindOfWeapon;
import com.shatteredpixel.spd2d.items.weapon.melee.Knuckles;
import com.shatteredpixel.spd2d.messages.Messages;
import com.shatteredpixel.spd2d.scenes.GameScene;
import com.shatteredpixel.spd2d.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;

public class DisarmingTrap extends Trap{

	{
		color = RED;
		shape = LARGE_DOT;
	}

	@Override
	public void activate() {
		Heap heap = Dungeon.level.heaps.get( pos );

		if (heap != null){
			int cell = Dungeon.level.randomRespawnCell();

			if (cell != -1) {
				Item item = heap.pickUp();
				Dungeon.level.drop( item, cell ).seen = true;
				for (int i : PathFinder.NEIGHBOURS9)
					Dungeon.level.visited[cell+i] = true;
				GameScene.updateFog();

				Sample.INSTANCE.play(Assets.SND_TELEPORT);
				CellEmitter.get(pos).burst(Speck.factory(Speck.LIGHT), 4);
			}
		}

		if (Dungeon.hero.pos == pos){
			Hero hero = Dungeon.hero;
			KindOfWeapon weapon = hero.belongings.weapon;

			if (weapon != null && !(weapon instanceof Knuckles) && !weapon.cursed) {

				int cell = Dungeon.level.randomRespawnCell();
				if (cell != -1) {
					hero.belongings.weapon = null;
					Dungeon.quickslot.clearItem(weapon);
					weapon.updateQuickslot();

					Dungeon.level.drop(weapon, cell).seen = true;
					for (int i : PathFinder.NEIGHBOURS9)
						Dungeon.level.visited[cell+i] = true;
					GameScene.updateFog();

					GLog.w( Messages.get(this, "disarm") );

					Sample.INSTANCE.play(Assets.SND_TELEPORT);
					CellEmitter.get(pos).burst(Speck.factory(Speck.LIGHT), 4);

				}

			}
		}
	}
}
