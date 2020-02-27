/*
 *    MCreator note:
 *
 *    This file is autogenerated to connect all MCreator mod elements together.
 *
 */
package net.mcreator.ender_additions;

import net.minecraftforge.forgespi.language.ModFileScanData;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.storage.WorldSavedData;
import net.minecraft.world.biome.Biome;
import net.minecraft.tags.Tag;
import net.minecraft.network.PacketBuffer;
import net.minecraft.item.Item;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.block.Block;

import java.util.function.Supplier;
import java.util.function.Function;
import java.util.function.BiConsumer;
import java.util.Set;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;

public class Elementsender_additions {
	protected final List<ModElement> elements = new ArrayList<>();
	protected final List<Supplier<Block>> blocks = new ArrayList<>();
	protected final List<Supplier<Item>> items = new ArrayList<>();
	protected final List<Supplier<Biome>> biomes = new ArrayList<>();
	protected final List<Supplier<EntityType<?>>> entities = new ArrayList<>();

	public Elementsender_additions() {
		try {
			ModFileScanData modFileInfo = ModList.get().getModFileById("ender_additions").getFile().getScanResult();
			Set<ModFileScanData.AnnotationData> annotations = modFileInfo.getAnnotations();
			for (ModFileScanData.AnnotationData annotationData : annotations) {
				if (annotationData.getAnnotationType().getClassName().equals(ModElement.Tag.class.getName())) {
					Class<?> clazz = Class.forName(annotationData.getClassType().getClassName());
					if (clazz.getSuperclass() == Elementsender_additions.ModElement.class)
						elements.add((Elementsender_additions.ModElement) clazz.getConstructor(this.getClass()).newInstance(this));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Collections.sort(elements);
		elements.forEach(Elementsender_additions.ModElement::initElements);
		this.addNetworkMessage(ender_additionsVariables.WorldSavedDataSyncMessage.class, ender_additionsVariables.WorldSavedDataSyncMessage::buffer,
				ender_additionsVariables.WorldSavedDataSyncMessage::new, ender_additionsVariables.WorldSavedDataSyncMessage::handler);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public void registerSounds(RegistryEvent.Register<net.minecraft.util.SoundEvent> event) {
	}

	@SubscribeEvent
	public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		if (!event.getPlayer().world.isRemote) {
			WorldSavedData mapdata = ender_additionsVariables.MapVariables.get(event.getPlayer().world);
			WorldSavedData worlddata = ender_additionsVariables.WorldVariables.get(event.getPlayer().world);
			if (mapdata != null)
				ender_additions.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()),
						new ender_additionsVariables.WorldSavedDataSyncMessage(0, mapdata));
			if (worlddata != null)
				ender_additions.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()),
						new ender_additionsVariables.WorldSavedDataSyncMessage(1, worlddata));
		}
	}

	@SubscribeEvent
	public void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
		if (!event.getPlayer().world.isRemote) {
			WorldSavedData worlddata = ender_additionsVariables.WorldVariables.get(event.getPlayer().world);
			if (worlddata != null)
				ender_additions.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()),
						new ender_additionsVariables.WorldSavedDataSyncMessage(1, worlddata));
		}
	}
	private int messageID = 0;

	public <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, PacketBuffer> encoder, Function<PacketBuffer, T> decoder,
			BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
		ender_additions.PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
		messageID++;
	}

	public List<ModElement> getElements() {
		return elements;
	}

	public List<Supplier<Block>> getBlocks() {
		return blocks;
	}

	public List<Supplier<Item>> getItems() {
		return items;
	}

	public List<Supplier<Biome>> getBiomes() {
		return biomes;
	}

	public List<Supplier<EntityType<?>>> getEntities() {
		return entities;
	}

	public static class ModElement implements Comparable<ModElement> {
		@Retention(RetentionPolicy.RUNTIME)
		public @interface Tag {
		}
		protected final Elementsender_additions elements;
		protected final int sortid;

		public ModElement(Elementsender_additions elements, int sortid) {
			this.elements = elements;
			this.sortid = sortid;
		}

		public void initElements() {
		}

		public void init(FMLCommonSetupEvent event) {
		}

		public void serverLoad(FMLServerStartingEvent event) {
		}

		@Override
		public int compareTo(ModElement other) {
			return this.sortid - other.sortid;
		}
	}
}
