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
import com.shatteredpixel.spd2d.actors.buffs.Invisibility;
import com.shatteredpixel.spd2d.actors.buffs.Weakness;
import com.shatteredpixel.spd2d.actors.hero.Hero;
import com.shatteredpixel.spd2d.effects.Flare;
import com.shatteredpixel.spd2d.effects.particles.ShadowParticle;
import com.shatteredpixel.spd2d.items.Item;
import com.shatteredpixel.spd2d.items.armor.Armor;
import com.shatteredpixel.spd2d.items.bags.Bag;
import com.shatteredpixel.spd2d.items.rings.Ring;
import com.shatteredpixel.spd2d.items.weapon.Weapon;
import com.shatteredpixel.spd2d.messages.Messages;
import com.shatteredpixel.spd2d.utils.GLog;
import com.shatteredpixel.spd2d.windows.WndBag;
import com.watabou.noosa.audio.Sample;

public class ScrollOfRemoveCurse extends InventoryScroll {

	{
		initials = 8;
		mode = WndBag.Mode.UNIDED_OR_CURSED;
	}

	@Override
	public void empoweredRead() {
		for (Item item : curUser.belongings){
			if (item.cursed){
				item.cursedKnown = true;
			}
		}
		Sample.INSTANCE.play( Assets.SND_READ );
		Invisibility.dispel();
		doRead();
	}

	@Override
	protected void onItemSelected(Item item) {
		new Flare( 6, 32 ).show( curUser.sprite, 2f ) ;

		boolean procced = uncurse( curUser, item );

		Weakness.detach( curUser, Weakness.class );

		if (procced) {
			GLog.p( Messages.get(this, "cleansed") );
		} else {
			GLog.i( Messages.get(this, "not_cleansed") );
		}
	}

	public static boolean uncurse( Hero hero, Item... items ) {
		
		boolean procced = false;
		for (Item item : items) {
			if (item != null && item.cursed) {
				item.cursed = false;
				procced = true;
			}
			if (item instanceof Weapon){
				Weapon w = (Weapon) item;
				if (w.hasCurseEnchant()){
					w.enchant(null);
					w.cursed = false;
					procced = true;
				}
			}
			if (item instanceof Armor){
				Armor a = (Armor) item;
				if (a.hasCurseGlyph()){
					a.inscribe(null);
					a.cursed = false;
					procced = true;
				}
			}
			if (item instanceof Bag){
				for (Item bagItem : ((Bag)item).items){
					if (bagItem != null && bagItem.cursed) {
						bagItem.cursed = false;
						procced = true;
					}
				}
			}
		}
		
		if (procced) {
			hero.sprite.emitter().start( ShadowParticle.UP, 0.05f, 10 );
			hero.updateHT( false ); //for ring of might
		}
		
		return procced;
	}
	
	@Override
	public int price() {
		return isKnown() ? 30 * quantity : super.price();
	}
}
