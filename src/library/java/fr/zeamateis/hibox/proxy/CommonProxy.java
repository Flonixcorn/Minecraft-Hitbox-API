package fr.zeamateis.hibox.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy implements IProxy {

	@Override
	public void onPreInitialization(FMLPreInitializationEvent event) {}

	@Override
	public void onInitialization(FMLInitializationEvent event) {}

	@Override
	public void onPostInitialization(FMLPostInitializationEvent event) {}

}