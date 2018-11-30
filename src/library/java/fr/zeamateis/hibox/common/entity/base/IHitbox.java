package fr.zeamateis.hibox.common.entity.base;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public interface IHitbox {

	/**
	 * @return Parent Entity World
	 */
	World getParentEntityWorld();

	/**
	 * Hook to {@linkplain #net.minecraft.entity.Entity.getParts()} <br>
	 * Because only Ender Dragon use this "multi-hiboxes" system
	 * 
	 * @return Hitboxes list
	 */
	Entity[] getParts();

	/**
	 * Define which hitbox damage the parent entity
	 * 
	 * @param hitboxPart
	 *            Hitbox
	 * @param source
	 *            DamageSource
	 * @param damage
	 *            float
	 * @return
	 */
	boolean attackEntityFromHitbox(Hitbox hitboxPart, DamageSource source, float damage);
}