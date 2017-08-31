package link.infra.simpleprocessors.proxy;

import link.infra.simpleprocessors.blocks.programmer.Programmer;
import link.infra.simpleprocessors.blocks.programmer.ProgrammerContainer;
import link.infra.simpleprocessors.blocks.programmer.ProgrammerTileEntity;
import link.infra.simpleprocessors.gui.ProgrammerGui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiProxy implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == Programmer.GUI_ID) {
			BlockPos pos = new BlockPos(x, y, z);
			TileEntity te = world.getTileEntity(pos);
			if (te instanceof ProgrammerTileEntity) {
				return new ProgrammerContainer(player.inventory, (ProgrammerTileEntity) te);
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == Programmer.GUI_ID) {
			BlockPos pos = new BlockPos(x, y, z);
			TileEntity te = world.getTileEntity(pos);
			if (te instanceof ProgrammerTileEntity) {
				ProgrammerTileEntity containerTileEntity = (ProgrammerTileEntity) te;
				return new ProgrammerGui(containerTileEntity, new ProgrammerContainer(player.inventory, containerTileEntity));
			}
		}
		return null;
	}
}
