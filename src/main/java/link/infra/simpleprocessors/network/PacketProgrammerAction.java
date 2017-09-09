package link.infra.simpleprocessors.network;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import io.netty.buffer.ByteBuf;
import link.infra.simpleprocessors.SimpleProcessors;
import link.infra.simpleprocessors.blocks.programmer.ProgrammerTileEntity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketProgrammerAction implements IMessage {
	
	public static enum ProgrammerAction {
		FLASH_PROCESSOR(0),
		WIPE_PROCESSOR(1);

		private static final Map<Byte, ProgrammerAction> lookup = new HashMap<Byte, ProgrammerAction>();

		static {
			for (ProgrammerAction a : EnumSet.allOf(ProgrammerAction.class)) {
				lookup.put(a.getCode(), a);
			}
		}

		private byte code;

		private ProgrammerAction(int code) {
			this.code = (byte) code;
		}

		public byte getCode() {
			return code;
		}

		public static ProgrammerAction get(byte code) { 
			return lookup.get(code); 
		}
	}
	
	private BlockPos programmerBlockPos;
	private ProgrammerAction action;

	@Override
	public void fromBytes(ByteBuf buf) {
		action = ProgrammerAction.get(buf.readByte());
		programmerBlockPos = BlockPos.fromLong(buf.readLong());
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeByte(action.getCode());
		buf.writeLong(programmerBlockPos.toLong());
	}
	
	public PacketProgrammerAction() {
		// for server initialisation
	}

	public PacketProgrammerAction(ProgrammerAction action, BlockPos programmerBlockPos) {
		this.action = action;
		this.programmerBlockPos = programmerBlockPos;
	}

	public static class Handler implements IMessageHandler<PacketProgrammerAction, IMessage> {
		@Override
		public IMessage onMessage(PacketProgrammerAction message, MessageContext ctx) {
			// Always use a construct like this to actually handle your message. This ensures that
			// your 'handle' code is run on the main Minecraft thread. 'onMessage' itself
			// is called on the networking thread so it is not safe to do a lot of things
			// here.
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
			return null;
		}

		private void handle(PacketProgrammerAction message, MessageContext ctx) {
			// This code is run on the server side. So you can do server-side calculations here
			EntityPlayerMP playerEntity = ctx.getServerHandler().player;
			World world = playerEntity.getEntityWorld();
			// Check if the block (chunk) is loaded to prevent abuse from a client
			// trying to overload a server by randomly loading chunks
			if (world.isBlockLoaded(message.programmerBlockPos)) {
				TileEntity te = world.getTileEntity(message.programmerBlockPos);
				if (te instanceof ProgrammerTileEntity) {
					// Note: if this is a real message you want to show to a player and not a debug message you should
					// use localized messages with TextComponentTranslated.
					playerEntity.sendStatusMessage(new TextComponentString(TextFormatting.GREEN + "Hit block: " + te.getBlockType().getRegistryName()), false);
					
					((ProgrammerTileEntity) te).serverAction(message.action, playerEntity.openContainer);
				} else {
					SimpleProcessors.logger.warn("Player tried to action something that isn't a programmer (or doesn't have a TE)!");
				}
			}
		}
	}
}
