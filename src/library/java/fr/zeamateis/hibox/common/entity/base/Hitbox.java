package fr.zeamateis.hibox.common.entity.base;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;

/**
 * Hitbox Class <br>
 * Add custom hitboxes to your entity with custom parameters
 * {@link #getPotionEffect()}<br>
 * {@link #getHitboxDamage()}<br>
 * {@link #nonCollidable()}
 * 
 * @author ZeAmateis
 * @version 0.1
 */
public class Hitbox extends Entity {

	public final IHitbox parent;
	public final String hitboxName;
	private boolean canBeCollided = true;

	private PotionEffect potionEffect[];
	private float hitboxDamage = 1.0F;

	public Hitbox(IHitbox parentEntityIn, String hitboxNameIn, float width, float height) {
		super(parentEntityIn.getParentEntityWorld());
		this.setSize(width, height);
		this.parent = parentEntityIn;
		this.hitboxName = hitboxNameIn;
	}

	public Hitbox(IHitbox parentEntityIn, String hitboxNameIn, float size) {
		super(parentEntityIn.getParentEntityWorld());
		this.setSize(size, size);
		this.parent = parentEntityIn;
		this.hitboxName = hitboxNameIn;
	}

	public float getHitboxDamage() {
		return hitboxDamage;
	}

	/**
	 * Define the damage amount the Entity will receive on hit this part
	 * 
	 * @default 1.0F
	 */
	public Hitbox setHitDamage(float hitboxDamage) {
		this.hitboxDamage = hitboxDamage;
		return this;
	}

	public PotionEffect[] getPotionEffect() {
		return potionEffect;
	}

	/**
	 * Define potion effect on specific part to apply them on the parent entity
	 */
	public Hitbox setPotionEffect(PotionEffect... potionEffect) {
		this.potionEffect = potionEffect;
		return this;
	}

	protected void entityInit() {}

	/**
	 * Call {@link #onUpdate()} and {@link #setLocationAndAngles()} to update logics
	 * of the hitbox entity
	 */
	public void updateAndSetPosition(double posX, double posY, double posZ) {
		this.onUpdate();
		this.setLocationAndAngles(posX, posY, posZ, 0.0F, 0.0F);
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	protected void readEntityFromNBT(NBTTagCompound compound) {}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	protected void writeEntityToNBT(NBTTagCompound compound) {}

	/**
	 * Define if the hitbox is collidable or not with other entities
	 */
	public Hitbox nonCollidable() {
		this.canBeCollided = false;
		return this;
	}

	/**
	 * Returns true if other Entities should be prevented from moving through this
	 * Entity.
	 */
	@Override
	public boolean canBeCollidedWith() {
		return this.canBeCollided;
	}

	/**
	 * Called when the entity is attacked.
	 */
	public boolean attackEntityFrom(DamageSource source, float amount) {
		return this.isEntityInvulnerable(source) ? false : this.parent.attackEntityFromHitbox(this, source, amount);
	}

	/**
	 * Returns true if Entity argument is equal to this Entity
	 */
	public boolean isEntityEqual(Entity entityIn) {
		return this == entityIn || this.parent == entityIn;
	}

	@Override
	public String getName() {
		return this.hitboxName;
	}
}