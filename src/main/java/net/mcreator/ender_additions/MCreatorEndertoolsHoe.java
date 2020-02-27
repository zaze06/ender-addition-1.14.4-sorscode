package net.mcreator.ender_additions;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;
import net.minecraft.item.HoeItem;

@Elementsender_additions.ModElement.Tag
public class MCreatorEndertoolsHoe extends Elementsender_additions.ModElement {
	@ObjectHolder("ender_additions:endertoolshoe")
	public static final Item block = null;

	public MCreatorEndertoolsHoe(Elementsender_additions instance) {
		super(instance, 8);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new HoeItem(new IItemTier() {
			public int getMaxUses() {
				return 3072;
			}

			public float getEfficiency() {
				return 18f;
			}

			public float getAttackDamage() {
				return 10f;
			}

			public int getHarvestLevel() {
				return 12;
			}

			public int getEnchantability() {
				return 84;
			}

			public Ingredient getRepairMaterial() {
				return null;
			}
		}, 18f, new Item.Properties().group(MCreatorEnderadditions.tab)) {
		}.setRegistryName("endertoolshoe"));
	}
}
