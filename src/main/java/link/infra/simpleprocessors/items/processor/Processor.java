package link.infra.simpleprocessors.items.processor;

import java.util.Arrays;
import java.util.List;

import link.infra.simpleprocessors.SimpleProcessors;
import link.infra.simpleprocessors.util.SPItemMeta;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Processor extends SPItemMeta {
	
	public List<Integer> storageList = Arrays.asList(1, 2, 4, 8, 8, 16, 32);
	public List<Integer> addonCardList = Arrays.asList(0, 1, 2, 4, 4, 8, 8);

	public Processor() {
		super("processor", true, 7);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		int damage = stack.getItemDamage();
		if (damage < 7) {
			tooltip.add(I18n.format("tooltip." + SimpleProcessors.MODID + ".item.processor.storage.name") + ": " + storageList.get(damage) + " KB");
			tooltip.add(I18n.format("tooltip." + SimpleProcessors.MODID + ".item.processor.addon.name") + ": " + addonCardList.get(damage));
		}
	}

}
