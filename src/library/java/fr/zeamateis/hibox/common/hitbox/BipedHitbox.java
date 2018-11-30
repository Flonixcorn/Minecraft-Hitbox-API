package fr.zeamateis.hibox.common.hitbox;

import java.util.Arrays;

import fr.zeamateis.hibox.common.entity.base.Hitbox;
import fr.zeamateis.hibox.common.entity.base.IHitbox;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

/**
 * Modders Facility Class <br>
 * Add custom hitboxes to biped <br>
 * {@link #hitboxBody } Defined two hitboxes for more precision <br>
 * {@link #hitboxHead} <br>
 * {@link #hitboxLeftArm}<br>
 * {@link #hitboxRightArm}<br>
 * {@link #hitboxLeftLeg} <br>
 * {@link #hitboxRightLeg}
 * 
 * @author ZeAmateis
 * @version 0.1
 */
public class BipedHitbox<T extends IHitbox> {

	private final EntityLivingBase parentEntity;

	private Hitbox hitboxHead;
	private final Hitbox[] hitboxBody;

	private Hitbox hitboxLeftArm;
	private Hitbox hitboxRightArm;

	private Hitbox hitboxLeftLeg;
	private Hitbox hitboxRightLeg;

	/**
	 * @param parentEntityIn
	 */
	public BipedHitbox(EntityLivingBase parentEntityIn) {
		this.parentEntity = parentEntityIn;

		this.hitboxHead = new Hitbox((T) parentEntityIn, "head", 0.5F);
		this.hitboxBody = new Hitbox[] { new Hitbox((T) parentEntityIn, "body_1", 0.25F, 0.8F), new Hitbox((T) parentEntityIn, "body_2", 0.25F, 0.8F) };

		this.hitboxLeftArm = new Hitbox((T) parentEntityIn, "leftArm", 0.25F, 0.8F);
		this.hitboxRightArm = new Hitbox((T) parentEntityIn, "rightArm", 0.25F, 0.8F);

		this.hitboxLeftLeg = new Hitbox((T) parentEntityIn, "leftLeg", 0.25F, 0.7F);
		this.hitboxRightLeg = new Hitbox((T) parentEntityIn, "rightLeg", 0.25F, 0.7F);
	}

	public void update() {
		float rotation = this.parentEntity.getRotationYawHead() * 0.017453292F;
		float sinRotation = MathHelper.sin(rotation);
		float cosRotation = MathHelper.cos(rotation);

		this.hitboxLeftArm.updateAndSetPosition(this.parentEntity.posX + (double) (cosRotation * 0.4F), this.parentEntity.posY + 0.7D, this.parentEntity.posZ + (double) (sinRotation * 0.4F));
		this.hitboxRightArm.updateAndSetPosition(this.parentEntity.posX - (double) (cosRotation * 0.4F), this.parentEntity.posY + 0.7D, this.parentEntity.posZ - (double) (sinRotation * 0.4F));

		this.hitboxHead.updateAndSetPosition(this.parentEntity.posX, this.parentEntity.posY + 1.5D, this.parentEntity.posZ);

		this.hitboxBody[0].updateAndSetPosition(this.parentEntity.posX + (double) (cosRotation * 0.1F), this.parentEntity.posY + 0.7D, this.parentEntity.posZ + (double) (sinRotation * 0.1F));
		this.hitboxBody[1].updateAndSetPosition(this.parentEntity.posX - (double) (cosRotation * 0.1F), this.parentEntity.posY + 0.7D, this.parentEntity.posZ - (double) (sinRotation * 0.1F));

		this.hitboxLeftLeg.updateAndSetPosition(this.parentEntity.posX + (double) (cosRotation * 0.1F), this.parentEntity.posY + 0.0D, this.parentEntity.posZ + (double) (sinRotation * 0.1F));
		this.hitboxRightLeg.updateAndSetPosition(this.parentEntity.posX - (double) (cosRotation * 0.1F), this.parentEntity.posY + 0.0D, this.parentEntity.posZ - (double) (sinRotation * 0.1F));
	}

	public EntityLivingBase getParentEntity() {
		return parentEntity;
	}

	public Hitbox getHitboxHead() {
		return hitboxHead;
	}

	public Hitbox getHitboxBody(int partIn) {
		return hitboxBody[partIn];
	}

	public Hitbox[] getHitboxBody() {
		return hitboxBody;
	}

	public Hitbox getHitboxLeftArm() {
		return hitboxLeftArm;
	}

	public Hitbox getHitboxRightArm() {
		return hitboxRightArm;
	}

	public Hitbox getHitboxLeftLeg() {
		return hitboxLeftLeg;
	}

	public Hitbox getHitboxRightLeg() {
		return hitboxRightLeg;
	}

	public Hitbox[] getHitboxes() {
		return new Hitbox[] { this.hitboxHead, this.hitboxBody[0], this.hitboxBody[1], this.hitboxLeftArm, this.hitboxRightArm, this.hitboxLeftLeg, this.hitboxRightLeg };
	}

	@Override
	public String toString() {
		return "BipedHitbox [parentEntity=" + parentEntity + ", hitboxHead=" + hitboxHead + ", hitboxBody=" + Arrays.toString(hitboxBody) + ", hitboxLeftArm=" + hitboxLeftArm + ", hitboxRightArm="
				+ hitboxRightArm + ", hitboxLeftLeg=" + hitboxLeftLeg + ", hitboxRightLeg=" + hitboxRightLeg + "]";
	}

}