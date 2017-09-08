package link.infra.simpleprocessors.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketUpCode implements IMessage {
	
	private NBTTagCompound newStorage;
	private BlockPos programmerBlockPos;

	@Override
	public void fromBytes(ByteBuf buf) {
		newStorage = ByteBufUtils.readTag(buf);
		programmerBlockPos = BlockPos.fromLong(buf.readLong());
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, newStorage);
		buf.writeLong(programmerBlockPos.toLong());
	}
	
	public PacketUpCode() {
		// for server initialisation
	}

	public PacketUpCode(NBTTagCompound newStorage, BlockPos programmerBlockPos) {
		this.newStorage = newStorage;
		this.programmerBlockPos = programmerBlockPos;
	}

	public static class Handler implements IMessageHandler<PacketUpCode, IMessage> {
		@Override
		public IMessage onMessage(PacketUpCode message, MessageContext ctx) {
			// Always use a construct like this to actually handle your message. This ensures that
			// your 'handle' code is run on the main Minecraft thread. 'onMessage' itself
			// is called on the networking thread so it is not safe to do a lot of things
			// here.
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
			return null;
		}

		private void handle(PacketUpCode message, MessageContext ctx) {
			// This code is run on the server side. So you can do server-side calculations here
			EntityPlayerMP playerEntity = ctx.getServerHandler().player;
			World world = playerEntity.getEntityWorld();
			// Check if the block (chunk) is loaded to prevent abuse from a client
			// trying to overload a server by randomly loading chunks
			if (world.isBlockLoaded(message.programmerBlockPos)) {
				Block block = world.getBlockState(message.programmerBlockPos).getBlock();
				// Note: if this is a real message you want to show to a player and not a debug message you should
				// use localized messages with TextComponentTranslated.
				playerEntity.sendStatusMessage(new TextComponentString(TextFormatting.GREEN + "Hit block: " + block.getRegistryName()), false);
			}
		}
	}
}
