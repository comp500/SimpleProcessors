package link.infra.simpleprocessors.blocks.processorcase;

import link.infra.simpleprocessors.SimpleProcessors;
import link.infra.simpleprocessors.util.SPBlock;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class CaseBasic extends SPBlock implements ITileEntityProvider {
	
	// Multiple inheritance would be so convenient - I'd like this to work with both SPBlock and SPBlockOrientable
	// I heard it causes problems though.
	
	// Why do I have to overload everything?
	public CaseBasic(String itemID, Material blockMaterial, float hardness, boolean tooltip) {
		super(itemID, blockMaterial, hardness, tooltip);
	}
	
	public CaseBasic(String itemID, Material blockMaterial, boolean tooltip) {
		super(itemID, blockMaterial, tooltip);
	}
	
	public CaseBasic(String itemID, Material blockMaterial, float hardness) {
		super(itemID, blockMaterial, hardness);
	}
	
	public CaseBasic(String itemID, Material blockMaterial) {
		super(itemID, blockMaterial);
	}
	
	/**
	 * Here, have a tile entity.
	 * @param worldIn World that the tile entity is in.
	 * @param meta I don't really care about metadata, set this to 0 for now.
	 */
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new CaseTileEntity();
	}
	
	/**
	 * Here's a block.
	 */
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		// Only execute on the server
		if (world.isRemote) {
			return true;
		}
		TileEntity te = world.getTileEntity(pos);
		if (!(te instanceof CaseTileEntity)) {
			return false;
		}
		player.openGui(SimpleProcessors.instance, CaseGui.GUI_ID, world, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}
}
