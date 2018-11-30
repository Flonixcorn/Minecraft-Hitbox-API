package fr.zeamateis.hitboxtest.common.entity;

import fr.zeamateis.hibox.common.entity.base.Hitbox;
import fr.zeamateis.hibox.common.entity.base.IHitbox;
import fr.zeamateis.hibox.common.hitbox.BipedHitbox;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityTest extends EntityZombie implements IMob, IHitbox {

	private BipedHitbox<EntityTest> bipedHitbox = new BipedHitbox<EntityTest>(this);

	/**
	 * Copied from EntityZombie / ModelDragon / ModelZombie
	 */
	public EntityTest(World worldIn) {
		super(worldIn);
		// this.setSize(0.6F, 1.95F);
		// this.tasks.addTask(1, new EntityAILookIdle(this));
		this.bipedHitbox.getHitboxLeftArm().setPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 300, 0), new PotionEffect(MobEffects.WEAKNESS, 300, 0));
		this.bipedHitbox.getHitboxRightArm().setPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 300, 0), new PotionEffect(MobEffects.WEAKNESS, 300, 0));
		this.bipedHitbox.getHitboxHead().setHitDamage(this.getMaxHealth());
		// Just for testing two hitboxes system on body
		// this.bipedHitbox.getHitboxBody(0).setPotionEffect(new
		// PotionEffect(MobEffects.INVISIBILITY, 50, 0));
		// Or
		// for (Hitbox bodyParts : this.bipedHitbox.getHitboxBody())
		// bodyParts.setPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 50, 0));

		// TODO Debug
		// HitboxTestMod.getLogger().info(this.bipedHitbox.toString());
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		this.bipedHitbox.update();
	}

	/**
	 * Returns true if other Entities should be prevented from moving through this
	 * Entity.
	 */
	@Override
	public boolean canBeCollidedWith() {
		return false;
	}

	@Override
	public World getParentEntityWorld() {
		return this.getEntityWorld();
	}

	@Override
	public Hitbox[] getParts() {
		return this.bipedHitbox.getHitboxes();
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