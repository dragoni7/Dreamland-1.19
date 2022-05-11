package com.github.dragoni7.dreamland.common.world.biome;

import com.github.dragoni7.dreamland.util.DreamlandLoc;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

public class BiomeKeys {
	
	public static final ResourceKey<Biome> HIVE = registerBiome("hive");
	public static final ResourceKey<Biome> GARDEN = registerBiome("garden");
	public static final ResourceKey<Biome> TARLANDS = registerBiome("tarlands");
	
	private static ResourceKey<Biome> registerBiome(String name) {
		return ResourceKey.create(Registry.BIOME_REGISTRY, DreamlandLoc.createLoc(name));
	}
}
