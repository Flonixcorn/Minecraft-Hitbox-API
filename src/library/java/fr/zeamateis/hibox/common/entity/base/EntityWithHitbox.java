package fr.zeamateis.hibox.common.entity.base;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class EntityWithHitbox extends EntityCreature implements IHitbox {

	public EntityWithHitbox(World worldIn) {
		super(worldIn);
	}

	/**
	 * Hook to {@linkplain #net.minecraft.entity.Entity.getParts()} <br>
	 * Because only Ender Dragon use this "multi-hiboxes" system
	 * 
	 * @return Hitboxes list
	 */
	@Override
	public abstract Hitbox[] getParts();

	/**
	 * Returns true if other Entities should be prevented from moving through this
	 * Entity.
	 */
	@Override
	public boolean canBeCollidedWith() {
		return false;
	}

	@Override
	public boolean attackEntityFromHitbox(Hitbox hitboxPart, DamageSource source, float damage) {
		if (hitboxPart != null)
			if (!hitboxPart.getEntityWorld().isRemote) {
				if (source.getTrueSource() instanceof EntityPlayer) {
					EntityPlayer player = (EntityPlayer) source.getTrueSource();
					if (hitboxPart.getPotionEffect() != null)
						for (PotionEffect potionEffect : hitboxPart.getPotionEffect()) {
							this.addPotionEffect(potionEffect);
						}
					this.attackEntityFrom(source, hitboxPart.getHitboxDamage());
					return true;
				}
			}
		return false;
	}

}
