package fr.zeamateis.hibox.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public interface IProxy {

	void onPreInitialization(FMLPreInitializationEvent event);

	void onInitialization(FMLInitializationEvent event);

	void onPostInitialization(FMLPostInitializationEvent event);
}
