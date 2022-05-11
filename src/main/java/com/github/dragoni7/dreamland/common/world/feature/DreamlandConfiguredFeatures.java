package com.github.dragoni7.dreamland.common.world.feature;

import java.util.List;

import com.github.dragoni7.dreamland.Dreamland;
import com.github.dragoni7.dreamland.common.blocks.DreamlandBlockTags;
import com.github.dragoni7.dreamland.common.world.feature.generation.BorderedDisk;
import com.github.dragoni7.dreamland.setup.DreamlandBlocks;
import com.github.dragoni7.dreamland.setup.DreamlandFeatures;
import com.github.dragoni7.dreamland.setup.DreamlandFluids;
import com.github.dragoni7.dreamland.util.DreamlandLoc;

import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockColumnConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.DeltaFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ReplaceBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.DualNoiseProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraftforge.fml.common.Mod;

public class DreamlandConfiguredFeatures {
	
	public static final RuleTest HIVE_ORES_REPLACEABLE = new TagMatchTest(DreamlandBlockTags.HIVE_ORES_REPLACEABLE);
	
	public static final List<OreConfiguration.TargetBlockState> MOSS_GRASS_TARGET_LIST = List.of(OreConfiguration.target(new TagMatchTest(DreamlandBlockTags.GARDEN_SURFACE_REPLACEABLE), Blocks.MOSS_BLOCK.defaultBlockState()));
	public static final List<OreConfiguration.TargetBlockState> FILLED_HIVE_TARGET_LIST = List.of(OreConfiguration.target(HIVE_ORES_REPLACEABLE, DreamlandBlocks.HIVE_BLOCK_WITH_JELLY.get().defaultBlockState()));
	public static final List<OreConfiguration.TargetBlockState> HIVE_IRON_TARGET_LIST = List.of(OreConfiguration.target(HIVE_ORES_REPLACEABLE, DreamlandBlocks.HIVE_IRON.get().defaultBlockState()));
	public static final List<OreConfiguration.TargetBlockState> HIVE_COPPER_TARGET_LIST = List.of(OreConfiguration.target(HIVE_ORES_REPLACEABLE, DreamlandBlocks.HIVE_COPPER.get().defaultBlockState()));
	public static final List<OreConfiguration.TargetBlockState> HIVE_GOLD_TARGET_LIST = List.of(OreConfiguration.target(HIVE_ORES_REPLACEABLE, DreamlandBlocks.HIVE_GOLD.get().defaultBlockState()));
	public static final List<OreConfiguration.TargetBlockState> HIVE_REDSTONE_TARGET_LIST = List.of(OreConfiguration.target(HIVE_ORES_REPLACEABLE, DreamlandBlocks.HIVE_REDSTONE.get().defaultBlockState()));
	public static final List<OreConfiguration.TargetBlockState> HIVE_LAPIS_TARGET_LIST = List.of(OreConfiguration.target(HIVE_ORES_REPLACEABLE, DreamlandBlocks.HIVE_LAPIS.get().defaultBlockState()));
	public static final List<OreConfiguration.TargetBlockState> HIVE_DIAMOND_TARGET_LIST = List.of(OreConfiguration.target(HIVE_ORES_REPLACEABLE, DreamlandBlocks.HIVE_DIAMOND.get().defaultBlockState()));
	
	public static final Holder<ConfiguredFeature<BlockColumnConfiguration, ?>> CAVE_SLIME = registerConfiguredFeature("cave_slime", 
			Feature.BLOCK_COLUMN,
					new BlockColumnConfiguration(
							List.of(BlockColumnConfiguration.layer( 
									new WeightedListInt(
											SimpleWeightedRandomList.<IntProvider>builder()
											.add(UniformInt.of(0, 19), 2)
											.add(UniformInt.of(0, 2), 3)
											.add(UniformInt.of(0, 6), 10)
											.build()), 
									BlockStateProvider.simple(
											DreamlandBlocks.CAVE_SLIME_PLANT.get())),
									BlockColumnConfiguration.layer(
											ConstantInt.of(1), 
											BlockStateProvider.simple(
													DreamlandBlocks.CAVE_SLIME.get()))),
							Direction.DOWN, 
							BlockPredicate.ONLY_IN_AIR_PREDICATE, 
							true));
	
	public static final Holder<ConfiguredFeature<BlockStateConfiguration,?>> HIVE_COMB = registerConfiguredFeature("hive_comb", DreamlandFeatures.HIVE_COMB, new BlockStateConfiguration(DreamlandBlocks.HIVE_MEMBRANE.get().defaultBlockState()));
	
	public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> HIVE_STRAND = registerConfiguredFeature("hive_strand", DreamlandFeatures.HIVE_STRAND, new NoneFeatureConfiguration());

	public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> HIVE_JELLY_CLUSTER = registerConfiguredFeature("hive_jelly_cluster", Feature.SIMPLE_BLOCK, (new SimpleBlockConfiguration(BlockStateProvider.simple(DreamlandBlocks.HIVE_JELLY_CLUSTER.get()))));
	
	public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> INFESTED_HIVE_JELLY_CLUSTER = registerConfiguredFeature("infested_hive_jelly_cluster", Feature.SIMPLE_BLOCK, (new SimpleBlockConfiguration(BlockStateProvider.simple(DreamlandBlocks.INFESTED_HIVE_JELLY_CLUSTER.get()))));
	
	public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> HIVE_GROWTH = registerConfiguredFeature("hive_growth", Feature.SIMPLE_BLOCK, (new SimpleBlockConfiguration(BlockStateProvider.simple(DreamlandBlocks.HIVE_GROWTH.get()))));
	
	public static final Holder<ConfiguredFeature<VegetationPatchConfiguration, ?>> GROWTH_PATCH = registerConfiguredFeature("growth_patch", Feature.VEGETATION_PATCH, (new VegetationPatchConfiguration(DreamlandBlockTags.HIVE_ORES_REPLACEABLE, BlockStateProvider.simple(DreamlandBlocks.HIVE_BLOCK.get()), PlacementUtils.inlinePlaced(HIVE_GROWTH), CaveSurface.FLOOR, ConstantInt.of(1), 0.0F, 5, 0.5F, UniformInt.of(1, 3), 0.3F)));
	
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> FILLED_HIVE_BLOCK = registerConfiguredFeature("filled_hive_block", Feature.ORE, new OreConfiguration(FILLED_HIVE_TARGET_LIST, 10));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> HIVE_IRON = registerConfiguredFeature("hive_iron", Feature.ORE, new OreConfiguration(HIVE_IRON_TARGET_LIST, 9));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> HIVE_GOLD = registerConfiguredFeature("hive_gold", Feature.ORE, new OreConfiguration(HIVE_GOLD_TARGET_LIST, 4, 0.5F));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> HIVE_REDSTONE = registerConfiguredFeature("hive_redstone", Feature.ORE, new OreConfiguration(HIVE_REDSTONE_TARGET_LIST, 8));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> HIVE_LAPIS = registerConfiguredFeature("hive_lapis", Feature.ORE, new OreConfiguration(HIVE_LAPIS_TARGET_LIST, 7));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> HIVE_COPPER = registerConfiguredFeature("hive_copper", Feature.ORE, new OreConfiguration(HIVE_COPPER_TARGET_LIST, 10));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> HIVE_COPPER_LARGE = registerConfiguredFeature("hive_copper_large", Feature.ORE, new OreConfiguration(HIVE_COPPER_TARGET_LIST, 20));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> HIVE_DIAMOND = registerConfiguredFeature("hive_diamond", Feature.ORE, new OreConfiguration(HIVE_DIAMOND_TARGET_LIST, 4, 0.5F));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> HIVE_DIAMOND_LARGE = registerConfiguredFeature("hive_diamond_large", Feature.ORE, new OreConfiguration(HIVE_DIAMOND_TARGET_LIST, 12, 0.7F));
	
	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> RED_FLOWERS = registerConfiguredFeature("red_flowers", Feature.FLOWER, new RandomPatchConfiguration(96, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.RED_TULIP.defaultBlockState(), 50).add(Blocks.LILY_OF_THE_VALLEY.defaultBlockState(), 25).add(Blocks.POPPY.defaultBlockState(), 25))))));
	
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> MOSS_GRASS_PATCH = registerConfiguredFeature("moss_grass_patch", Feature.ORE, new OreConfiguration(MOSS_GRASS_TARGET_LIST, 30));
	
	public static final Holder<ConfiguredFeature<BorderedDisk.Configuration, ?>> DROUGHT_BORDERED_DISK = registerConfiguredFeature("drought_bordered_disk", DreamlandFeatures.BORDERED_DISK, new BorderedDisk.Configuration(BlockStateProvider.simple(DreamlandBlocks.DROUGHT_SOIL.get().defaultBlockState()), BlockStateProvider.simple(DreamlandBlocks.DRIED_TAR.get().defaultBlockState())));
	public static final Holder<ConfiguredFeature<DiskConfiguration, ?>> DROUGHT_DISK = registerConfiguredFeature("drought_disk", Feature.DISK, new DiskConfiguration(DreamlandBlocks.DROUGHT_SOIL.get().defaultBlockState(), UniformInt.of(2, 5), 2, List.of(DreamlandBlocks.TAR_SOIL.get().defaultBlockState(), DreamlandBlocks.DRIED_TAR.get().defaultBlockState())));
	public static final Holder<ConfiguredFeature<DeltaFeatureConfiguration, ?>> TAR_DELTA = registerConfiguredFeature("tar_delta", Feature.DELTA_FEATURE, new DeltaFeatureConfiguration(DreamlandFluids.TAR_BLOCK.get().defaultBlockState(), DreamlandBlocks.DRIED_TAR.get().defaultBlockState(), UniformInt.of(3, 7), UniformInt.of(1, 4)));
	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> DROUGHT_VEGETATION = registerConfiguredFeature("drought_vegetation", Feature.RANDOM_PATCH, new RandomPatchConfiguration(96, 8, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.OXEYE_DAISY.defaultBlockState(), 10).add(Blocks.DEAD_BUSH.defaultBlockState(), 40).add(Blocks.GRASS.defaultBlockState(), 50))))));
	public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> JOSHUA_TREE = registerConfiguredFeature("joshua_tree", DreamlandFeatures.JOSHUA_TREE_FEATURE, new NoneFeatureConfiguration());
	
	private static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<FC, ?>> registerConfiguredFeature(String name, F feature, FC config) {
		return BuiltinRegistries.registerExact(BuiltinRegistries.CONFIGURED_FEATURE, DreamlandLoc.createLoc(name).toString(), new ConfiguredFeature<>(feature,config));
	}
}