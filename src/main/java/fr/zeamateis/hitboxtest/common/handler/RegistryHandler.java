package fr.zeamateis.hitboxtest.common.handler;

import fr.zeamateis.hitboxtest.common.HitboxTestMod;
import fr.zeamateis.hitboxtest.common.entity.EntityTest;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

@EventBusSubscriber(modid = HitboxTestMod.MODID)
public class RegistryHandler {
	public static int entityID = 0;

	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
		final EntityEntry[] entries = { createBuilder("entity_test").entity(EntityTest.class).tracker(64, 20, true).build() };
		event.getRegistry().registerAll(entries);
		HitboxTestMod.getProxy().bindEntityRenderer();
	}

	/**
	 * Create an {@link EntityEntryBuilder} with the specified unlocalised/registry
	 * name and an automatically-assigned network ID.
	 *
	 * @param name
	 *            The name
	 * @param <E>
	 *            The entity type
	 * @return The builder
	 */
	public static <E extends Entity> EntityEntryBuilder<E> createBuilder(final String name) {
		final EntityEntryBuilder<E> builder = EntityEntryBuilder.create();
		final ResourceLocation registryName = new ResourceLocation(HitboxTestMod.MODID, name);
		return builder.id(registryName, entityID++).name(registryName.toString());
	}
}