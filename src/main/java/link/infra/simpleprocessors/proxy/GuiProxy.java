package link.infra.simpleprocessors.proxy;

import link.infra.simpleprocessors.blocks.processorcase.CaseContainer;
import link.infra.simpleprocessors.blocks.processorcase.CaseGui;
import link.infra.simpleprocessors.blocks.processorcase.CaseTileEntity;
import link.infra.simpleprocessors.blocks.programmer.ProgrammerContainer;
import link.infra.simpleprocessors.blocks.programmer.ProgrammerGui;
import link.infra.simpleprocessors.blocks.programmer.ProgrammerTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiProxy implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == ProgrammerGui.GUI_ID) {
			BlockPos pos = new BlockPos(x, y, z);
			TileEntity te = world.getTileEntity(pos);
			if (te instanceof ProgrammerTileEntity) {
				return new ProgrammerContainer(player.inventory, (ProgrammerTileEntity) te);
			}
		}
		if (ID == CaseGui.GUI_ID) {
			BlockPos pos = new BlockPos(x, y, z);
			TileEntity te = world.getTileEntity(pos);
			if (te instanceof CaseTileEntity) {
				return new CaseContainer(player.inventory, (CaseTileEntity) te);
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == ProgrammerGui.GUI_ID) {
			BlockPos pos = new BlockPos(x, y, z);
			TileEntity te = world.getTileEntity(pos);
			if (te instanceof ProgrammerTileEntity) {
				ProgrammerTileEntity containerTileEntity = (ProgrammerTileEntity) te;
				return new ProgrammerGui(containerTileEntity, new ProgrammerContainer(player.inventory, containerTileEntity));
			}
		}
		if (ID == CaseGui.GUI_ID) {
			BlockPos pos = new BlockPos(x, y, z);
			TileEntity te = world.getTileEntity(pos);
			if (te instanceof CaseTileEntity) {
				CaseTileEntity containerTileEntity = (CaseTileEntity) te;
				return new CaseGui(new CaseContainer(player.inventory, containerTileEntity));
			}
		}
		return null;
	}
}
