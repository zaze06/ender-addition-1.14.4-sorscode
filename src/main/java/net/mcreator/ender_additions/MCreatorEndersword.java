package net.mcreator.ender_additions;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;

@Elementsender_additions.ModElement.Tag
public class MCreatorEndersword extends Elementsender_additions.ModElement {
	@ObjectHolder("ender_additions:endersword")
	public static final Item block = null;

	public MCreatorEndersword(Elementsender_additions instance) {
		super(instance, 11);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new SwordItem(new IItemTier() {
			public int getMaxUses() {
				return 2531;
			}

			public float getEfficiency() {
				return 7f;
			}

			public float getAttackDamage() {
				return 13f;
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
		}, 3, -3.5F, new Item.Properties().group(MCreatorEnderadditions.tab)) {
		}.setRegistryName("endersword"));
	}
}
