package link.infra.simpleprocessors.blocks.programmer;

import link.infra.simpleprocessors.SimpleProcessors;
import link.infra.simpleprocessors.util.SPBlockOrientable;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Programmer extends SPBlockOrientable implements ITileEntityProvider {

	public Programmer() {
		super("programmer", Material.ROCK, true);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new ProgrammerTileEntity();
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		// Only execute on the server
		if (world.isRemote) {
			return true;
		}
		TileEntity te = world.getTileEntity(pos);
		if (!(te instanceof ProgrammerTileEntity)) {
			return false;
		}
		player.openGui(SimpleProcessors.instance, ProgrammerGui.GUI_ID, world, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}
	
	@Override
	@Deprecated
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	@Deprecated
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

}
