package net.mcreator.ender_additions;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Rotation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Mirror;

import java.util.Random;

@Elementsender_additions.ModElement.Tag
public class MCreatorEndercristaldrop extends Elementsender_additions.ModElement {
	public MCreatorEndercristaldrop(Elementsender_additions instance) {
		super(instance, 11);
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		Feature<NoFeatureConfig> feature = new Feature<NoFeatureConfig>(NoFeatureConfig::deserialize) {
			@Override
			public boolean place(IWorld iworld, ChunkGenerator generator, Random random, BlockPos pos, NoFeatureConfig config) {
				int i = pos.getX();
				int k = pos.getZ();
				DimensionType dimensionType = iworld.getDimension().getType();
				boolean dimensionCriteria = false;
				if (dimensionType == DimensionType.THE_END)
					dimensionCriteria = true;
				if (!dimensionCriteria)
					return false;
				if ((random.nextInt(1000000) + 1) <= 0) {
					i += random.nextInt(16) + 8;
					k += random.nextInt(16) + 8;
					int j = iworld.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, i, k);
					j = Math.abs(random.nextInt(Math.max(1, j)) - 24);
					Template template = ((ServerWorld) iworld.getWorld()).getSaveHandler().getStructureTemplateManager()
							.getTemplateDefaulted(new ResourceLocation("ender_additions", "ender_cristal_drop"));
					if (template == null)
						return false;
					Rotation rotation = Rotation.values()[random.nextInt(3)];
					Mirror mirror = Mirror.values()[random.nextInt(2)];
					BlockPos spawnTo = new BlockPos(i, j + -10, k);
					template.addBlocksToWorldChunk(iworld, spawnTo, new PlacementSettings().setRotation(rotation).setRandom(random).setMirror(mirror)
							.setChunk((ChunkPos) null).setIgnoreEntities(false));
					return true;
				}
				return false;
			}
		};
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES,
					Biome.createDecoratedFeature(feature, IFeatureConfig.NO_FEATURE_CONFIG, Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));
		}
	}
}
