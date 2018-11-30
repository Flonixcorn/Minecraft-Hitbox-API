package fr.zeamateis.hibox.common;

import org.apache.logging.log4j.Logger;

import fr.zeamateis.hibox.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = HitboxAPI.MODID, name = HitboxAPI.NAME, version = HitboxAPI.VERSION)
public class HitboxAPI {

	public static final String MODID = "hitbox_api";
	public static final String NAME = "Hitbox API";
	public static final String VERSION = "0.0.1";

	private static Logger logger;

	@Instance(MODID)
	private static HitboxAPI instance;

	@SidedProxy(clientSide = "fr.zeamateis.hibox.proxy.ClientProxy", serverSide = "fr.zeamateis.hibox.proxy.ServerProxy")
	private static CommonProxy proxy;

	@EventHandler
	public void onPreInitialization(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		proxy.onPreInitialization(event);
	}

	@EventHandler
	public void onInitialization(FMLInitializationEvent event) {
		proxy.onInitialization(event);
	}

	@EventHandler
	public void onPostInitialization(FMLPostInitializationEvent event) {
		proxy.onPostInitialization(event);
	}

	public static Logger getLogger() {
		return logger;
	}

	/**
	 * Currently unused
	 * 
	 * @since 0.0.1
	 */
	public static HitboxAPI getInstance() {
		return instance;
	}

	/* TODO */
	/**
	 * Currently unused
	 * 
	 * @since 0.0.1
	 */
	private static CommonProxy getProxy() {
		return proxy;
	}
}
