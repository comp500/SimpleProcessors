package link.infra.simpleprocessors.blocks.programmer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class ProgrammerTileEntity extends TileEntity {
	
	private static final String TEST_DIR = "E:/test";

	public boolean canInteractWith(EntityPlayer playerIn) {
		// If we are too far away from this tile entity you cannot use it
		return !isInvalid() && playerIn.getDistanceSq(pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
	}

    private NBTTagCompound storageMap; 

    public String getFile(String file) {
        return storageMap.getString(file);
    }
    
    public HashMap<String, Integer> getFileList() {
    	if (storageMap == null || storageMap.hasNoTags()) {
    		return new HashMap<String, Integer>();
    	} else {
    		HashMap<String, Integer> tempMap = new HashMap<String, Integer>();
    		for (String s : storageMap.getKeySet()) {
    			tempMap.put(s, storageMap.getString(s).length());
    		}
    		return tempMap;
    	}
    }

    public void setFile(String file, String data) {
        this.storageMap.setString(file, data);
        markDirty();
        if (world != null) {
            IBlockState state = world.getBlockState(getPos());
            world.notifyBlockUpdate(getPos(), state, state, 3);
        }
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        // getUpdateTag() is called whenever the chunkdata is sent to the
        // client. In contrast getUpdatePacket() is called when the tile entity
        // itself wants to sync to the client. In many cases you want to send
        // over the same information in getUpdateTag() as in getUpdatePacket().
    	NBTTagCompound nbtTag = new NBTTagCompound();
        this.writeToNBT(nbtTag);
        return writeToNBT(new NBTTagCompound());
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        // Prepare a packet for syncing our TE to the client. Since we only have to sync the stack
        // and that's all we have we just write our entire NBT here. If you have a complex
        // tile entity that doesn't need to have all information on the client you can write
        // a more optimal NBT here.
        NBTTagCompound nbtTag = new NBTTagCompound();
        this.writeToNBT(nbtTag);
        return new SPacketUpdateTileEntity(getPos(), 1, nbtTag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
        // Here we get the packet from the server and read it into our client side tile entity
        this.readFromNBT(packet.getNbtCompound());
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if (compound.hasKey("storage")) {
        	storageMap = compound.getCompoundTag("storage");
        } else {
            storageMap = new NBTTagCompound();
            // TEST
            storageMap.setString("index.js", "hello");
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        if (!storageMap.hasNoTags()) {
            compound.setTag("storage", storageMap);
        }
        return compound;
    }
    
    public void openLocal() {
    	for (String s : storageMap.getKeySet()) {
			try {
				String[] fileArray = storageMap.getString(s).split("\n");
				List<String> fileArrayList = Arrays.asList(fileArray);
				Files.write(Paths.get(TEST_DIR, s), fileArrayList, StandardCharsets.UTF_8);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidPathException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
    
    public void readLocal() {
    	Path p = Paths.get(TEST_DIR);
    	FileVisitor<Path> fv = new SimpleFileVisitor<Path>() {
    		public int currentSize = 0;
    		public HashMap<String, String> pendingMap = new HashMap<String, String>();
    		
    		@Override
    		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
    			currentSize += Files.size(file);
    			if (currentSize > 64000) {
    				// won't fit on biggest processor
    				return FileVisitResult.TERMINATE;
    			}
    			System.out.println(file);
    			List<String> fileStringList = Files.readAllLines(file, StandardCharsets.UTF_8);
    			Path testDir = Paths.get(TEST_DIR);
    			pendingMap.put(testDir.relativize(file).toString(), String.join("\n", fileStringList));
    			return FileVisitResult.CONTINUE;
    		}
    	};

    	try {
    		Files.walkFileTree(p, fv);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }

}
