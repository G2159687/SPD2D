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
package com.shatteredpixel.spd2d.actors.blobs;

import com.shatteredpixel.spd2d.Assets;
import com.shatteredpixel.spd2d.Badges;
import com.shatteredpixel.spd2d.Dungeon;
import com.shatteredpixel.spd2d.DungeonTilemap;
import com.shatteredpixel.spd2d.journal.Notes.Landmark;
import com.shatteredpixel.spd2d.journal.Notes;
import com.shatteredpixel.spd2d.actors.buffs.Awareness;
import com.shatteredpixel.spd2d.actors.buffs.Buff;
import com.shatteredpixel.spd2d.actors.hero.Hero;
import com.shatteredpixel.spd2d.effects.BlobEmitter;
import com.shatteredpixel.spd2d.effects.Identification;
import com.shatteredpixel.spd2d.effects.Speck;
import com.shatteredpixel.spd2d.items.Item;
import com.shatteredpixel.spd2d.levels.Terrain;
import com.shatteredpixel.spd2d.messages.Messages;
import com.shatteredpixel.spd2d.scenes.GameScene;
import com.shatteredpixel.spd2d.utils.GLog;
import com.watabou.noosa.audio.Sample;

public class WaterOfAwareness extends WellWater {

	@Override
	protected boolean affectHero( Hero hero ) {
		
		Sample.INSTANCE.play( Assets.SND_DRINK );
		emitter.parent.add( new Identification( DungeonTilemap.tileCenterToWorld( pos ) ) );
		
		hero.belongings.observe();
		
		for (int i=0; i < Dungeon.level.length(); i++) {
			
			int terr = Dungeon.level.map[i];
			if ((Terrain.flags[terr] & Terrain.SECRET) != 0) {
				
				Dungeon.level.discover( i );
				
				if (Dungeon.visible[i]) {
					GameScene.discoverTile( i, terr );
				}
			}
		}
		
		Buff.affect( hero, Awareness.class, Awareness.DURATION );
		Dungeon.observe();

		Dungeon.hero.interrupt();
	
		GLog.p( Messages.get(this, "procced") );
		
		Notes.remove( Landmark.WELL_OF_AWARENESS );
		
		return true;
	}
	
	@Override
	protected Item affectItem( Item item ) {
		if (item.isIdentified()) {
			return null;
		} else {
			item.identify();
			Badges.validateItemLevelAquired( item );
			
			emitter.parent.add( new Identification( DungeonTilemap.tileCenterToWorld( pos ) ) );
			
			Notes.remove( Landmark.WELL_OF_AWARENESS );
			
			return item;
		}
	}
	
	@Override
	public void use( BlobEmitter emitter ) {
		super.use( emitter );
		emitter.pour( Speck.factory( Speck.QUESTION ), 0.3f );
	}
	
	@Override
	public String tileDesc() {
		return Messages.get(this, "desc");
	}
}
