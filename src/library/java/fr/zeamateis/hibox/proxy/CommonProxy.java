package fr.zeamateis.hibox.proxy;

import fr.zeamateis.hibox.common.hitbox.HitboxCapabilities;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy implements IProxy {
	
	@Override
	public void onPreInitialization(FMLPreInitializationEvent event) {
		CapabilityManager.INSTANCE.register(HitboxCapabilities.class, new HitboxCapabilities.Storage(), new HitboxCapabilities.Factory());
	}

	@Override
	public void onInitialization(FMLInitializationEvent event) {}

	@Override
	public void onPostInitialization(FMLPostInitializationEvent event) {}

}