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

package com.shatteredpixel.spd2d.journal;

import com.shatteredpixel.spd2d.ShatteredPixelDungeon;
import com.watabou.noosa.Game;
import com.watabou.utils.Bundle;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Journal {

    private static final String JOURNAL_FILE = "journal.dat";

    private static boolean loaded = false;

    public static void loadGlobal(){
        if (loaded){
            return;
        }

        Bundle bundle;
        try {
            InputStream input = Game.instance.openFileInput( JOURNAL_FILE );
            bundle = Bundle.read(input);
            input.close();

        } catch (IOException e){
            bundle = new Bundle();
        }

        Catalog.restore( bundle );
        Document.restore( bundle );

        loaded = true;
    }

    //package-private
    static boolean saveNeeded = false;

    public static void saveGlobal(){
        if (!saveNeeded){
            return;
        }

        Bundle bundle = new Bundle();

        Catalog.store(bundle);
        Document.store(bundle);

        try {
            OutputStream output = Game.instance.openFileOutput( JOURNAL_FILE, Game.MODE_PRIVATE );
            Bundle.write( bundle, output );
            output.close();
            saveNeeded = false;
        } catch (IOException e) {
            ShatteredPixelDungeon.reportException(e);
        }

    }

}