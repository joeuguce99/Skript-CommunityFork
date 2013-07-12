/*
 *   This file is part of Skript.
 *
 *  Skript is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Skript is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Skript.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * 
 * Copyright 2011, 2012 Peter Güttinger
 * 
 */

package ch.njol.skript.util;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.block.Biome;

import ch.njol.skript.localization.Language;
import ch.njol.skript.localization.LanguageChangeListener;
import ch.njol.util.StringUtils;

/**
 * @author Peter Güttinger
 */
public abstract class BiomeUtils {
	
	private BiomeUtils() {}
	
	private final static Map<String, Biome> parseMap = new HashMap<String, Biome>();
	private final static String[] names = new String[Biome.values().length];
	static {
		Language.addListener(new LanguageChangeListener() {
			@Override
			public void onLanguageChange() {
				parseMap.clear();
				for (final Biome b : Biome.values()) {
					if (b == null)
						continue;
					final String[] ls = Language.getList("biomes." + b.name());
					names[b.ordinal()] = ls[0];
					for (final String l : ls) {
						parseMap.put(l.toLowerCase(), b);
					}
				}
			}
		});
	}
	
	public final static Biome parse(final String s) {
		return parseMap.get(s.toLowerCase());
	}
	
	public final static String toString(final Biome b) {
		return names[b.ordinal()];
	}
	
	public final static String getAllNames() {
		return StringUtils.join(names, ", ");
	}
	
}