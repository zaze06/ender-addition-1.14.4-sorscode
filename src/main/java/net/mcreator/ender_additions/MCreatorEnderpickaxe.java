package net.mcreator.ender_additions;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;

@Elementsender_additions.ModElement.Tag
public class MCreatorEnderpickaxe extends Elementsender_additions.ModElement {
	@ObjectHolder("ender_additions:enderpickaxe")
	public static final Item block = null;

	public MCreatorEnderpickaxe(Elementsender_additions instance) {
		super(instance, 8);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new PickaxeItem(new IItemTier() {
			public int getMaxUses() {
				return 2643;
			}

			public float getEfficiency() {
				return 8f;
			}

			public float getAttackDamage() {
				return 9f;
			}

			public int getHarvestLevel() {
				return 6;
			}

			public int getEnchantability() {
				return 3;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(MCreatorEndercrystalitem.block, (int) (1)));
			}
		}, 1, 1F, new Item.Properties().group(MCreatorEnderadditions.tab)) {
		}.setRegistryName("enderpickaxe"));
	}
}
