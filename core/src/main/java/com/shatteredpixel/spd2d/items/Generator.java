/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015  Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2017 Evan Debenham
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

package com.shatteredpixel.spd2d.items;

import com.shatteredpixel.spd2d.Dungeon;
import com.shatteredpixel.spd2d.ShatteredPixelDungeon;
import com.shatteredpixel.spd2d.actors.mobs.npcs.Ghost;
import com.shatteredpixel.spd2d.items.armor.Armor;
import com.shatteredpixel.spd2d.items.armor.ClothArmor;
import com.shatteredpixel.spd2d.items.armor.LeatherArmor;
import com.shatteredpixel.spd2d.items.armor.MailArmor;
import com.shatteredpixel.spd2d.items.armor.PlateArmor;
import com.shatteredpixel.spd2d.items.armor.ScaleArmor;
import com.shatteredpixel.spd2d.items.artifacts.AlchemistsToolkit;
import com.shatteredpixel.spd2d.items.artifacts.Artifact;
import com.shatteredpixel.spd2d.items.artifacts.CapeOfThorns;
import com.shatteredpixel.spd2d.items.artifacts.ChaliceOfBlood;
import com.shatteredpixel.spd2d.items.artifacts.CloakOfShadows;
import com.shatteredpixel.spd2d.items.artifacts.DriedRose;
import com.shatteredpixel.spd2d.items.artifacts.EtherealChains;
import com.shatteredpixel.spd2d.items.artifacts.HornOfPlenty;
import com.shatteredpixel.spd2d.items.artifacts.LloydsBeacon;
import com.shatteredpixel.spd2d.items.artifacts.MasterThievesArmband;
import com.shatteredpixel.spd2d.items.artifacts.SandalsOfNature;
import com.shatteredpixel.spd2d.items.artifacts.TalismanOfForesight;
import com.shatteredpixel.spd2d.items.artifacts.TimekeepersHourglass;
import com.shatteredpixel.spd2d.items.artifacts.UnstableSpellbook;
import com.shatteredpixel.spd2d.items.bags.Bag;
import com.shatteredpixel.spd2d.items.food.Food;
import com.shatteredpixel.spd2d.items.food.MysteryMeat;
import com.shatteredpixel.spd2d.items.food.Pasty;
import com.shatteredpixel.spd2d.items.potions.Potion;
import com.shatteredpixel.spd2d.items.potions.PotionOfExperience;
import com.shatteredpixel.spd2d.items.potions.PotionOfFrost;
import com.shatteredpixel.spd2d.items.potions.PotionOfHealing;
import com.shatteredpixel.spd2d.items.potions.PotionOfInvisibility;
import com.shatteredpixel.spd2d.items.potions.PotionOfLevitation;
import com.shatteredpixel.spd2d.items.potions.PotionOfLiquidFlame;
import com.shatteredpixel.spd2d.items.potions.PotionOfMight;
import com.shatteredpixel.spd2d.items.potions.PotionOfMindVision;
import com.shatteredpixel.spd2d.items.potions.PotionOfParalyticGas;
import com.shatteredpixel.spd2d.items.potions.PotionOfPurity;
import com.shatteredpixel.spd2d.items.potions.PotionOfStrength;
import com.shatteredpixel.spd2d.items.potions.PotionOfToxicGas;
import com.shatteredpixel.spd2d.items.rings.Ring;
import com.shatteredpixel.spd2d.items.rings.RingOfAccuracy;
import com.shatteredpixel.spd2d.items.rings.RingOfElements;
import com.shatteredpixel.spd2d.items.rings.RingOfEnergy;
import com.shatteredpixel.spd2d.items.rings.RingOfEvasion;
import com.shatteredpixel.spd2d.items.rings.RingOfForce;
import com.shatteredpixel.spd2d.items.rings.RingOfFuror;
import com.shatteredpixel.spd2d.items.rings.RingOfHaste;
import com.shatteredpixel.spd2d.items.rings.RingOfMight;
import com.shatteredpixel.spd2d.items.rings.RingOfSharpshooting;
import com.shatteredpixel.spd2d.items.rings.RingOfTenacity;
import com.shatteredpixel.spd2d.items.rings.RingOfWealth;
import com.shatteredpixel.spd2d.items.scrolls.Scroll;
import com.shatteredpixel.spd2d.items.scrolls.ScrollOfIdentify;
import com.shatteredpixel.spd2d.items.scrolls.ScrollOfLullaby;
import com.shatteredpixel.spd2d.items.scrolls.ScrollOfMagicMapping;
import com.shatteredpixel.spd2d.items.scrolls.ScrollOfMagicalInfusion;
import com.shatteredpixel.spd2d.items.scrolls.ScrollOfMirrorImage;
import com.shatteredpixel.spd2d.items.scrolls.ScrollOfPsionicBlast;
import com.shatteredpixel.spd2d.items.scrolls.ScrollOfRage;
import com.shatteredpixel.spd2d.items.scrolls.ScrollOfRecharging;
import com.shatteredpixel.spd2d.items.scrolls.ScrollOfRemoveCurse;
import com.shatteredpixel.spd2d.items.scrolls.ScrollOfTeleportation;
import com.shatteredpixel.spd2d.items.scrolls.ScrollOfTerror;
import com.shatteredpixel.spd2d.items.scrolls.ScrollOfUpgrade;
import com.shatteredpixel.spd2d.items.wands.Wand;
import com.shatteredpixel.spd2d.items.wands.WandOfBlastWave;
import com.shatteredpixel.spd2d.items.wands.WandOfCorruption;
import com.shatteredpixel.spd2d.items.wands.WandOfDisintegration;
import com.shatteredpixel.spd2d.items.wands.WandOfFireblast;
import com.shatteredpixel.spd2d.items.wands.WandOfFrost;
import com.shatteredpixel.spd2d.items.wands.WandOfLightning;
import com.shatteredpixel.spd2d.items.wands.WandOfMagicMissile;
import com.shatteredpixel.spd2d.items.wands.WandOfPrismaticLight;
import com.shatteredpixel.spd2d.items.wands.WandOfRegrowth;
import com.shatteredpixel.spd2d.items.wands.WandOfTransfusion;
import com.shatteredpixel.spd2d.items.wands.WandOfVenom;
import com.shatteredpixel.spd2d.items.weapon.Weapon;
import com.shatteredpixel.spd2d.items.weapon.melee.AssassinsBlade;
import com.shatteredpixel.spd2d.items.weapon.melee.BattleAxe;
import com.shatteredpixel.spd2d.items.weapon.melee.Dagger;
import com.shatteredpixel.spd2d.items.weapon.melee.Dirk;
import com.shatteredpixel.spd2d.items.weapon.melee.Flail;
import com.shatteredpixel.spd2d.items.weapon.melee.Glaive;
import com.shatteredpixel.spd2d.items.weapon.melee.Greataxe;
import com.shatteredpixel.spd2d.items.weapon.melee.Greatshield;
import com.shatteredpixel.spd2d.items.weapon.melee.Greatsword;
import com.shatteredpixel.spd2d.items.weapon.melee.HandAxe;
import com.shatteredpixel.spd2d.items.weapon.melee.Knuckles;
import com.shatteredpixel.spd2d.items.weapon.melee.Longsword;
import com.shatteredpixel.spd2d.items.weapon.melee.Mace;
import com.shatteredpixel.spd2d.items.weapon.melee.MagesStaff;
import com.shatteredpixel.spd2d.items.weapon.melee.Quarterstaff;
import com.shatteredpixel.spd2d.items.weapon.melee.RoundShield;
import com.shatteredpixel.spd2d.items.weapon.melee.RunicBlade;
import com.shatteredpixel.spd2d.items.weapon.melee.Sai;
import com.shatteredpixel.spd2d.items.weapon.melee.Scimitar;
import com.shatteredpixel.spd2d.items.weapon.melee.Shortsword;
import com.shatteredpixel.spd2d.items.weapon.melee.Spear;
import com.shatteredpixel.spd2d.items.weapon.melee.Sword;
import com.shatteredpixel.spd2d.items.weapon.melee.WarHammer;
import com.shatteredpixel.spd2d.items.weapon.melee.Whip;
import com.shatteredpixel.spd2d.items.weapon.melee.WornShortsword;
import com.shatteredpixel.spd2d.items.weapon.missiles.Boomerang;
import com.shatteredpixel.spd2d.items.weapon.missiles.CurareDart;
import com.shatteredpixel.spd2d.items.weapon.missiles.Dart;
import com.shatteredpixel.spd2d.items.weapon.missiles.IncendiaryDart;
import com.shatteredpixel.spd2d.items.weapon.missiles.Javelin;
import com.shatteredpixel.spd2d.items.weapon.missiles.Shuriken;
import com.shatteredpixel.spd2d.items.weapon.missiles.Tamahawk;
import com.shatteredpixel.spd2d.plants.BlandfruitBush;
import com.shatteredpixel.spd2d.plants.Blindweed;
import com.shatteredpixel.spd2d.plants.Dreamfoil;
import com.shatteredpixel.spd2d.plants.Earthroot;
import com.shatteredpixel.spd2d.plants.Fadeleaf;
import com.shatteredpixel.spd2d.plants.Firebloom;
import com.shatteredpixel.spd2d.plants.Icecap;
import com.shatteredpixel.spd2d.plants.Plant;
import com.shatteredpixel.spd2d.plants.Rotberry;
import com.shatteredpixel.spd2d.plants.Sorrowmoss;
import com.shatteredpixel.spd2d.plants.Starflower;
import com.shatteredpixel.spd2d.plants.Stormvine;
import com.shatteredpixel.spd2d.plants.Sungrass;
import com.watabou.utils.Bundle;
import com.watabou.utils.GameMath;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Generator {

	public enum Category {
		WEAPON	( 6,    Weapon.class ),
		WEP_T1	( 0,    Weapon.class),
		WEP_T2	( 0,    Weapon.class),
		WEP_T3	( 0,    Weapon.class),
		WEP_T4	( 0,    Weapon.class),
		WEP_T5	( 0,    Weapon.class),
		ARMOR	( 4,    Armor.class ),
		POTION	( 20,   Potion.class ),
		SCROLL	( 20,   Scroll.class ),
		WAND	( 3,    Wand.class ),
		RING	( 1,    Ring.class ),
		ARTIFACT( 1,    Artifact.class),
		SEED	( 0,    Plant.Seed.class ),
		FOOD	( 0,    Food.class ),
		GOLD	( 25,   Gold.class );

		public Class<?>[] classes;
		public float[] probs;

		public float prob;
		public Class<? extends Item> superClass;

		private Category( float prob, Class<? extends Item> superClass ) {
			this.prob = prob;
			this.superClass = superClass;
		}

		public static int order( Item item ) {
			for (int i=0; i < values().length; i++) {
				if (values()[i].superClass.isInstance( item )) {
					return i;
				}
			}

			return item instanceof Bag ? Integer.MAX_VALUE : Integer.MAX_VALUE - 1;
		}

		private static final float[] INITIAL_ARTIFACT_PROBS = new float[]{ 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1};

		static {
			GOLD.classes = new Class<?>[]{
					Gold.class };
			GOLD.probs = new float[]{ 1 };

			SCROLL.classes = new Class<?>[]{
					ScrollOfIdentify.class,
					ScrollOfTeleportation.class,
					ScrollOfRemoveCurse.class,
					ScrollOfUpgrade.class,
					ScrollOfRecharging.class,
					ScrollOfMagicMapping.class,
					ScrollOfRage.class,
					ScrollOfTerror.class,
					ScrollOfLullaby.class,
					ScrollOfMagicalInfusion.class,
					ScrollOfPsionicBlast.class,
					ScrollOfMirrorImage.class };
			SCROLL.probs = new float[]{ 30, 10, 20, 0, 15, 15, 12, 8, 8, 0, 4, 10 };

			POTION.classes = new Class<?>[]{
					PotionOfHealing.class,
					PotionOfExperience.class,
					PotionOfToxicGas.class,
					PotionOfParalyticGas.class,
					PotionOfLiquidFlame.class,
					PotionOfLevitation.class,
					PotionOfStrength.class,
					PotionOfMindVision.class,
					PotionOfPurity.class,
					PotionOfInvisibility.class,
					PotionOfMight.class,
					PotionOfFrost.class };
			POTION.probs = new float[]{ 45, 4, 15, 10, 15, 10, 0, 20, 12, 10, 0, 10 };

			WAND.classes = new Class<?>[]{
					WandOfMagicMissile.class,
					WandOfLightning.class,
					WandOfDisintegration.class,
					WandOfFireblast.class,
					WandOfVenom.class,
					WandOfBlastWave.class,
					//WandOfLivingEarth.class,
					WandOfFrost.class,
					WandOfPrismaticLight.class,
					//WandOfWarding.class,
					WandOfTransfusion.class,
					WandOfCorruption.class,
					WandOfRegrowth.class };
			WAND.probs = new float[]{ 5, 4, 4, 4, 4, 3, /*3,*/ 3, 3, /*3,*/ 3, 3, 3 };

			//see generator.randomWeapon
			WEAPON.classes = new Class<?>[]{};
			WEAPON.probs = new float[]{};

			WEP_T1.classes = new Class<?>[]{
					WornShortsword.class,
					Knuckles.class,
					Dagger.class,
					MagesStaff.class,
					Boomerang.class,
					Dart.class
			};
			WEP_T1.probs = new float[]{ 1, 1, 1, 0, 0, 1 };

			WEP_T2.classes = new Class<?>[]{
					Shortsword.class,
					HandAxe.class,
					Spear.class,
					Quarterstaff.class,
					Dirk.class,
					IncendiaryDart.class
			};
			WEP_T2.probs = new float[]{ 6, 5, 5, 4, 4, 6 };

			WEP_T3.classes = new Class<?>[]{
					Sword.class,
					Mace.class,
					Scimitar.class,
					RoundShield.class,
					Sai.class,
					Whip.class,
					Shuriken.class,
					CurareDart.class
			};
			WEP_T3.probs = new float[]{ 6, 5, 5, 4, 4, 4, 6, 6 };

			WEP_T4.classes = new Class<?>[]{
					Longsword.class,
					BattleAxe.class,
					Flail.class,
					RunicBlade.class,
					AssassinsBlade.class,
					Javelin.class
			};
			WEP_T4.probs = new float[]{ 6, 5, 5, 4, 4, 6 };

			WEP_T5.classes = new Class<?>[]{
					Greatsword.class,
					WarHammer.class,
					Glaive.class,
					Greataxe.class,
					Greatshield.class,
					Tamahawk.class
			};
			WEP_T5.probs = new float[]{ 6, 5, 5, 4, 4, 6 };

			//see Generator.randomArmor
			ARMOR.classes = new Class<?>[]{
					ClothArmor.class,
					LeatherArmor.class,
					MailArmor.class,
					ScaleArmor.class,
					PlateArmor.class };
			ARMOR.probs = new float[]{ 0, 0, 0, 0, 0 };

			FOOD.classes = new Class<?>[]{
					Food.class,
					Pasty.class,
					MysteryMeat.class };
			FOOD.probs = new float[]{ 4, 1, 0 };

			RING.classes = new Class<?>[]{
					RingOfAccuracy.class,
					RingOfEvasion.class,
					RingOfElements.class,
					RingOfForce.class,
					RingOfFuror.class,
					RingOfHaste.class,
					RingOfEnergy.class,
					RingOfMight.class,
					RingOfSharpshooting.class,
					RingOfTenacity.class,
					RingOfWealth.class};
			RING.probs = new float[]{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };

			ARTIFACT.classes = new Class<?>[]{
					CapeOfThorns.class,
					ChaliceOfBlood.class,
					CloakOfShadows.class,
					HornOfPlenty.class,
					MasterThievesArmband.class,
					SandalsOfNature.class,
					TalismanOfForesight.class,
					TimekeepersHourglass.class,
					UnstableSpellbook.class,
					AlchemistsToolkit.class, //currently removed from drop tables, pending rework.
					DriedRose.class,
					LloydsBeacon.class,
					EtherealChains.class
			};
			ARTIFACT.probs = INITIAL_ARTIFACT_PROBS.clone();

			SEED.classes = new Class<?>[]{
					Firebloom.Seed.class,
					Icecap.Seed.class,
					Sorrowmoss.Seed.class,
					Blindweed.Seed.class,
					Sungrass.Seed.class,
					Earthroot.Seed.class,
					Fadeleaf.Seed.class,
					Rotberry.Seed.class,
					BlandfruitBush.Seed.class,
					Dreamfoil.Seed.class,
					Stormvine.Seed.class,
					Starflower.Seed.class};
			SEED.probs = new float[]{ 12, 12, 12, 12, 12, 12, 12, 0, 4, 12, 12, 1 };
		}
	}

	private static final float[][] floorSetTierProbs = new float[][] {
			{0, 70, 20,  8,  2},
			{0, 25, 50, 20,  5},
			{0, 10, 40, 40, 10},
			{0,  5, 20, 50, 25},
			{0,  2,  8, 20, 70}
	};

	private static HashMap<Category,Float> categoryProbs = new LinkedHashMap<>();

	public static void reset() {
		for (Category cat : Category.values()) {
			categoryProbs.put( cat, cat.prob );
		}
	}

	public static Item random() {
		Category cat = Random.chances( categoryProbs );
		if (cat == null){
			reset();
			cat = Random.chances( categoryProbs );
		}
		categoryProbs.put( cat, categoryProbs.get( cat ) - 1);
		return random( cat );
	}

	public static Item random( Category cat ) {
		try {

			switch (cat) {
				case ARMOR:
					return randomArmor();
				case WEAPON:
					return randomWeapon();
				case ARTIFACT:
					Item item = randomArtifact();
					//if we're out of artifacts, return a ring instead.
					return item != null ? item : random(Category.RING);
				default:
					return ((Item)cat.classes[Random.chances( cat.probs )].newInstance()).random();
			}

		} catch (Exception e) {

			ShatteredPixelDungeon.reportException(e);
			return null;

		}
	}

	public static Item random( Class<? extends Item> cl ) {
		try {

			return ((Item)cl.newInstance()).random();

		} catch (Exception e) {

			ShatteredPixelDungeon.reportException(e);
			return null;

		}
	}

	public static Armor randomArmor(){
		return randomArmor(Dungeon.depth / 5);
	}

	public static Armor randomArmor(int floorSet) {

		floorSet = (int)GameMath.gate(0, floorSet, floorSetTierProbs.length-1);

		try {
			Armor a = (Armor)Category.ARMOR.classes[Random.chances(floorSetTierProbs[floorSet])].newInstance();
			a.random();
			return a;
		} catch (Exception e) {
			ShatteredPixelDungeon.reportException(e);
			return null;
		}
	}

	public static final Category[] wepTiers = new Category[]{
			Category.WEP_T1,
			Category.WEP_T2,
			Category.WEP_T3,
			Category.WEP_T4,
			Category.WEP_T5
	};

	public static Weapon randomWeapon(){
		return randomWeapon(Dungeon.depth / 5);
	}

	public static Weapon randomWeapon(int floorSet) {

		floorSet = (int)GameMath.gate(0, floorSet, floorSetTierProbs.length-1);

		try {
			Category c = wepTiers[Random.chances(floorSetTierProbs[floorSet])];
			Weapon w = (Weapon)c.classes[Random.chances(c.probs)].newInstance();
			w.random();
			return w;
		} catch (Exception e) {
			ShatteredPixelDungeon.reportException(e);
			return null;
		}
	}

	//enforces uniqueness of artifacts throughout a run.
	public static Artifact randomArtifact() {

		try {
			Category cat = Category.ARTIFACT;
			int i = Random.chances( cat.probs );

			//if no artifacts are left, return null
			if (i == -1){
				return null;
			}

			Class<?extends Artifact> art = (Class<? extends Artifact>) cat.classes[i];

			if (removeArtifact(art)) {
				Artifact artifact = art.newInstance();

				artifact.random();

				return artifact;
			} else {
				return null;
			}

		} catch (Exception e) {
			ShatteredPixelDungeon.reportException(e);
			return null;
		}
	}

	public static boolean removeArtifact(Class<?extends Artifact> artifact) {
		if (spawnedArtifacts.contains(artifact))
			return false;

		Category cat = Category.ARTIFACT;
		for (int i = 0; i < cat.classes.length; i++)
			if (cat.classes[i].equals(artifact)) {
				if (cat.probs[i] == 1){
					cat.probs[i] = 0;
					spawnedArtifacts.add(artifact);
					return true;
				} else
					return false;
			}

		return false;
	}

	//resets artifact probabilities, for new dungeons
	public static void initArtifacts() {
		Category.ARTIFACT.probs = Category.INITIAL_ARTIFACT_PROBS.clone();
		spawnedArtifacts = new ArrayList<>();
	}

	private static ArrayList<Class<?extends Artifact>> spawnedArtifacts = new ArrayList<>();

	private static final String GENERAL_PROBS = "general_probs";
	private static final String SPAWNED_ARTIFACTS = "spawned_artifacts";

	public static void storeInBundle(Bundle bundle) {
		Float[] genProbs = categoryProbs.values().toArray(new Float[0]);
		float[] storeProbs = new float[genProbs.length];
		for (int i = 0; i < storeProbs.length; i++){
			storeProbs[i] = genProbs[i];
		}
		bundle.put( GENERAL_PROBS, storeProbs);

		bundle.put( SPAWNED_ARTIFACTS, spawnedArtifacts.toArray(new Class[0]));
	}

	public static void restoreFromBundle(Bundle bundle) {
		if (bundle.contains(GENERAL_PROBS)){
			float[] probs = bundle.getFloatArray(GENERAL_PROBS);
			for (int i = 0; i < probs.length; i++){
				categoryProbs.put(Category.values()[i], probs[i]);
			}
		} else {
			reset();
		}

		initArtifacts();
		if (bundle.contains(SPAWNED_ARTIFACTS)){
			for ( Class<?extends Artifact> artifact : bundle.getClassArray(SPAWNED_ARTIFACTS) ){
				removeArtifact(artifact);
			}
			//pre-0.6.1 saves
		} else if (bundle.contains("artifacts")) {
			String[] names = bundle.getStringArray("artifacts");
			Category cat = Category.ARTIFACT;

			for (String artifact : names)
				for (int i = 0; i < cat.classes.length; i++)
					if (cat.classes[i].getSimpleName().equals(artifact))
						cat.probs[i] = 0;
		}
	}
}
