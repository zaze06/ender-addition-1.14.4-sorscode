package net.mcreator.ender_additions;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.World;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Rotation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Mirror;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

@Elementsender_additions.ModElement.Tag
public class MCreatorEndercrystaldropgen extends Elementsender_additions.ModElement {
	public MCreatorEndercrystaldropgen(Elementsender_additions instance) {
		super(instance, 14);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure MCreatorEndercrystaldropgen!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure MCreatorEndercrystaldropgen!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure MCreatorEndercrystaldropgen!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure MCreatorEndercrystaldropgen!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		boolean end1 = false;
		boolean end2 = false;
		double cord = 0;
		double cord2 = 0;
		cord = (double) -3;
		end1 = (boolean) (false);
		for (int index0 = 0; index0 < (int) (100); index0++) {
			cord = (double) -3;
			for (int index1 = 0; index1 < (int) (100); index1++) {
				cord = (double) -3;
				for (int index2 = 0; index2 < (int) (100); index2++) {
					if (((world.getBlockState(new BlockPos((int) (x + (cord)), (int) (y + (cord)), (int) (z + (cord))))).getBlock() == Blocks.END_STONE
							.getDefaultState().getBlock())) {
						end1 = (boolean) (true);
					}
					cord = (double) ((cord) + 1);
				}
				cord = (double) ((cord) + 1);
			}
			cord = (double) ((cord) + 1);
		}
		cord2 = (double) -3;
		end2 = (boolean) (false);
		for (int index3 = 0; index3 < (int) (100); index3++) {
			cord2 = (double) -3;
			for (int index4 = 0; index4 < (int) (100); index4++) {
				cord2 = (double) -3;
				for (int index5 = 0; index5 < (int) (100); index5++) {
					if (((world.getBlockState(new BlockPos((int) (x + (cord2)), (int) (y + (cord2)), (int) (z + (cord2))))).getBlock() == Blocks.AIR
							.getDefaultState().getBlock())) {
						end1 = (boolean) (true);
					}
					cord = (double) ((cord2) + 1);
				}
				cord = (double) ((cord2) + 1);
			}
			cord = (double) ((cord2) + 1);
		}
		if ((((end1) && (end2)) == (true))) {
			if (((Blocks.END_STONE.getDefaultState().getBlock() == (world.getBlockState(new BlockPos((int) x, (int) (y + 5), (int) z))).getBlock()) && (Blocks.AIR
					.getDefaultState().getBlock() == (world.getBlockState(new BlockPos((int) x, (int) (y - 5), (int) z))).getBlock()))) {
				if (!world.isRemote) {
					Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
							.getTemplateDefaulted(new ResourceLocation("ender_additions", "ender_cristal_drop"));
					if (template != null) {
						template.addBlocksToWorldChunk(world, new BlockPos((int) x, (int) y, (int) z),
								new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk((ChunkPos) null)
										.setIgnoreEntities(false));
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			Entity entity = event.player;
			World world = entity.world;
			int i = (int) entity.posX;
			int j = (int) entity.posY;
			int k = (int) entity.posZ;
			java.util.HashMap<String, Object> dependencies = new java.util.HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("event", event);
			this.executeProcedure(dependencies);
		}
	}
}
