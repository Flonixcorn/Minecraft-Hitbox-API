package fr.zeamateis.hitboxtest.common;

import org.apache.logging.log4j.Logger;

import fr.zeamateis.hitboxtest.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = HitboxTestMod.MODID, name = HitboxTestMod.NAME, version = HitboxTestMod.VERSION, dependencies = "required-after:hitbox_api;")
public class HitboxTestMod {

	public static final String MODID = "hitbox_test";
	public static final String NAME = "Hitbox Test";
	public static final String VERSION = "1.0";

	private static Logger logger;

	@Instance(MODID)
	private static HitboxTestMod instance;

	@SidedProxy(clientSide = "fr.zeamateis.hitboxtest.proxy.ClientProxy", serverSide = "fr.zeamateis.hitboxtest.proxy.ServerProxy")
	private static CommonProxy proxy;

	@EventHandler
	public void onPreInitialization(FMLPreInitializationEvent event) {
		logger = event.getModLog();
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

	public static HitboxTestMod getInstance() {
		return instance;
	}

	public static CommonProxy getProxy() {
		return proxy;
	}
}
