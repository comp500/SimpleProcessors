package link.infra.simpleprocessors.util;

import java.util.List;

import link.infra.simpleprocessors.ModItems;
import link.infra.simpleprocessors.SimpleProcessors;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SPBlock extends Block {

	private boolean extraTooltip;
	private String itemId;

	public SPBlock(String itemID, Material blockMaterial, float hardness, boolean tooltip) {
		super(blockMaterial);
		setUnlocalizedName(SimpleProcessors.MODID + "." + itemID);
		setRegistryName(itemID);
		setCreativeTab(ModItems.tab);
		setHardness(hardness);
		itemId = itemID;
		extraTooltip = tooltip;
	}

	public SPBlock(String itemID, Material blockMaterial, boolean tooltip) {
		super(blockMaterial);
		setUnlocalizedName(SimpleProcessors.MODID + "." + itemID);
		setRegistryName(itemID);
		setCreativeTab(ModItems.tab);
		setHardness(1.0F);
		itemId = itemID;
		extraTooltip = tooltip;
	}

	public SPBlock(String itemID, Material blockMaterial, float hardness) {
		super(blockMaterial);
		setUnlocalizedName(SimpleProcessors.MODID + "." + itemID);
		setRegistryName(itemID);
		setCreativeTab(ModItems.tab);
		setHardness(hardness);
		itemId = itemID;
	}

	public SPBlock(String itemID, Material blockMaterial) {
		super(blockMaterial);
		setUnlocalizedName(SimpleProcessors.MODID + "." + itemID);
		setRegistryName(itemID);
		setCreativeTab(ModItems.tab);
		setHardness(1.0F);
		itemId = itemID;
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (extraTooltip) {
			tooltip.add(I18n.format("tooltip." + SimpleProcessors.MODID + ".block." + itemId + ".name"));
		}
	}

}
