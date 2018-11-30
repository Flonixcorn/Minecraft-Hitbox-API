package fr.zeamateis.hitboxtest.proxy;

import fr.zeamateis.hitboxtest.client.render.entity.RenderEntityTest;
import fr.zeamateis.hitboxtest.common.entity.EntityTest;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

	@Override
	public void onPreInitialization(FMLPreInitializationEvent event) {
		super.onPreInitialization(event);
	}

	@Override
	public void onInitialization(FMLInitializationEvent event) {
		super.onInitialization(event);
	}

	@Override
	public void onPostInitialization(FMLPostInitializationEvent event) {
		super.onPostInitialization(event);
	}

	@Override
	public void bindEntityRenderer() {
		RenderingRegistry.registerEntityRenderingHandler(EntityTest.class, RenderEntityTest.FACTORY);
	}

}