package fr.zeamateis.hitboxtest.client.render.entity;

import fr.zeamateis.hitboxtest.common.entity.EntityTest;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderEntityTest<T extends EntityTest> extends RenderBiped<T> {

	public static final Factory FACTORY = new Factory();
	private static final ResourceLocation ZOMBIE_TEXTURES = new ResourceLocation("textures/entity/zombie/zombie.png");

	// RenderEntity
	public RenderEntityTest(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelZombie(), 0.5f);
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless
	 * you call Render.bindEntityTexture.
	 */
	protected ResourceLocation getEntityTexture(T entity) {
		return ZOMBIE_TEXTURES;
	}

	@SideOnly(Side.CLIENT)
	static class ModelTest extends ModelBiped {
		public ModelTest() {
			this(0.0F, false);
		}

		public ModelTest(float modelSize, boolean p_i1168_2_) {
			super(modelSize, 0.0F, 64, p_i1168_2_ ? 32 : 64);
		}

		/**
		 * Sets the model's various rotation angles. For bipeds, par1 and par2 are used
		 * for animating the movement of arms and legs, where par1 represents the
		 * time(so that arms and legs swing back and forth) and par2 represents how
		 * "far" arms and legs can swing at most.
		 */
		public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
			super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
			float f = MathHelper.sin(this.swingProgress * (float) Math.PI);
			float f1 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float) Math.PI);
			this.bipedRightArm.rotateAngleZ = 0.0F;
			this.bipedLeftArm.rotateAngleZ = 0.0F;
			this.bipedRightArm.rotateAngleY = -(0.1F - f * 0.6F);
			this.bipedLeftArm.rotateAngleY = 0.1F - f * 0.6F;
			// float f2 = -(float)Math.PI / (flag ? 1.5F : 2.25F);
			// this.bipedRightArm.rotateAngleX = f2;
			// this.bipedLeftArm.rotateAngleX = f2;
			this.bipedRightArm.rotateAngleX += f * 1.2F - f1 * 0.4F;
			this.bipedLeftArm.rotateAngleX += f * 1.2F - f1 * 0.4F;
			this.bipedRightArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
			this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
			this.bipedRightArm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
			this.bipedLeftArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
		}
	}

	static class Factory<T extends EntityTest> implements IRenderFactory<T> {
		@Override
		public Render<? super T> createRenderFor(RenderManager manager) {
			return new RenderEntityTest(manager);
		}

	}
}
