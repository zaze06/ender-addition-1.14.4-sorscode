package net.mcreator.ender_additions;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

@Elementsender_additions.ModElement.Tag
public class MCreatorEndercrystalitem extends Elementsender_additions.ModElement {
	@ObjectHolder("ender_additions:endercrystalitem")
	public static final Item block = null;

	public MCreatorEndercrystalitem(Elementsender_additions instance) {
		super(instance, 7);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(MCreatorEnderadditions.tab).maxStackSize(64));
			setRegistryName("endercrystalitem");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}
	}
}
