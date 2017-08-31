package link.infra.simpleprocessors.util;

import java.util.List;

import link.infra.simpleprocessors.SimpleProcessors;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SPItemMeta extends SPItem {
	
	private int metas;

	public SPItemMeta(String itemID, boolean tooltip, int stackAmount, int metas) {
		super(itemID, tooltip, stackAmount);
		setHasSubtypes(true);
		setMaxDamage(0);
		this.metas = metas;
	}

	public SPItemMeta(String itemID, boolean tooltip, int metas) {
		super(itemID, tooltip);
		setHasSubtypes(true);
		setMaxDamage(0);
		this.metas = metas;
	}

	public SPItemMeta(String itemID, int stackAmount, int metas) {
		super(itemID, stackAmount);
		setHasSubtypes(true);
		setMaxDamage(0);
		this.metas = metas;
	}

	public SPItemMeta(String itemID, int metas) {
		super(itemID);
		setHasSubtypes(true);
		setMaxDamage(0);
		this.metas = metas;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void initModel() {
		for (int i = 0; i < metas; i++) {
			ModelLoader.setCustomModelResourceLocation(this, i, new ModelResourceLocation(getRegistryName() + "_" + i, "inventory"));
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(CreativeTabs tabs, NonNullList<ItemStack> list) {
		if (this.isInCreativeTab(tabs)) {
			for (int i = 0; i < metas; i++) {
				list.add(new ItemStack(this, 1, i));
			}
		}
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
        return this.getUnlocalizedName() + "_" + stack.getItemDamage();
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (extraTooltip) {
			tooltip.add(I18n.format("tooltip." + SimpleProcessors.MODID + ".item." + itemId + "_" + stack.getItemDamage() + ".name"));
		}
	}

}
