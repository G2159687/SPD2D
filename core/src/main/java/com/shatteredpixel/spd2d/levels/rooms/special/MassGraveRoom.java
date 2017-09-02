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

import com.shatteredpixel.spd2d.Assets;
import com.shatteredpixel.spd2d.actors.mobs.Skeleton;
import com.shatteredpixel.spd2d.items.Generator;
import com.shatteredpixel.spd2d.items.Gold;
import com.shatteredpixel.spd2d.items.Heap;
import com.shatteredpixel.spd2d.items.Item;
import com.shatteredpixel.spd2d.items.potions.PotionOfLiquidFlame;
import com.shatteredpixel.spd2d.items.quest.CorpseDust;
import com.shatteredpixel.spd2d.levels.Level;
import com.shatteredpixel.spd2d.levels.painters.Painter;
import com.shatteredpixel.spd2d.levels.Terrain;
import com.shatteredpixel.spd2d.messages.Messages;
import com.shatteredpixel.spd2d.ui.CustomTileVisual;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class MassGraveRoom extends SpecialRoom {

    @Override
    public int minWidth() { return 7; }

    @Override
    public int minHeight() { return 7; }

	public void paint( Level level){

		Door entrance = entrance();
		entrance.set(Door.Type.BARRICADE);
		level.addItemToSpawn(new PotionOfLiquidFlame());

		Painter.fill(level, this, Terrain.WALL);
		Painter.fill(level, this, 1, Terrain.EMPTY_SP);

		level.customTiles.addAll(Bones.CustomTilesForRoom(this, Bones.class));

		//50% 1 skeleton, 50% 2 skeletons
		for (int i = 0; i <= Random.Int(2); i++){
			Skeleton skele = new Skeleton();

			int pos;
			do {
				pos = level.pointToCell(random());
			} while (level.map[pos] != Terrain.EMPTY_SP || level.findMob(pos) != null);
			skele.pos = pos;
			level.mobs.add( skele );
		}

		ArrayList<Item> items = new ArrayList<>();
		//100% corpse dust, 2x100% 1 coin, 2x30% coins, 1x60% random item, 1x30% armor
		items.add(new CorpseDust());
		items.add(new Gold(1));
		items.add(new Gold(1));
		if (Random.Float() <= 0.3f) items.add(new Gold());
		if (Random.Float() <= 0.3f) items.add(new Gold());
		if (Random.Float() <= 0.6f) items.add(Generator.random());
		if (Random.Float() <= 0.3f) items.add(Generator.randomArmor());

		for (Item item : items){
			int pos;
			do {
				pos = level.pointToCell(random());
			} while (level.map[pos] != Terrain.EMPTY_SP || level.heaps.get(pos) != null);
			Heap h = level.drop(item, pos);
			h.type = Heap.Type.SKELETON;
		}
	}

	public static class Bones extends CustomTileVisual {
		{
			name = Messages.get(this, "name");

			tx = Assets.PRISON_QUEST;
			txX = 3;
			txY = 0;
		}

		@Override
		public String desc() {
			if (ofsX == 1 && ofsY == 1) {
				return Messages.get(this, "desc");
			} else {
				return null;
			}
		}
	}
}
