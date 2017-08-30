package link.infra.simpleprocessors;

import org.apache.logging.log4j.Logger;

import link.infra.simpleprocessors.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = SimpleProcessors.MODID, name = SimpleProcessors.MODNAME, version = SimpleProcessors.VERSION, useMetadata = true)
public class SimpleProcessors {

	public static final String MODID = "simpleprocessors";
    public static final String MODNAME = "Simple Processors";
    public static final String VERSION = "1.0.0.0";
    
    @SidedProxy(clientSide = "link.infra.simpleprocessors.proxy.ClientProxy", serverSide = "link.infra.simpleprocessors.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static SimpleProcessors instance;

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
}
