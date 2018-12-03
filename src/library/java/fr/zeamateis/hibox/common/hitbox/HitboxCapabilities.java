package fr.zeamateis.hibox.common.hitbox;

import java.util.concurrent.Callable;

import fr.zeamateis.hibox.common.HitboxAPI;
import fr.zeamateis.hibox.common.entity.base.Hitbox;
import fr.zeamateis.hibox.common.entity.base.IHitbox;
import fr.zeamateis.hitboxtest.common.HitboxTestMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = HitboxTestMod.MODID)
public class HitboxCapabilities implements ICapabilityProvider, INBTSerializable<NBTTagCompound>, IHitbox{

	/**
	 * Player Hitbox Capabilitie Class <br>
	 * 
	 * @author Wind_Blade
	 * @version 0.1
	 */
	
	private final EntityPlayer player;
	private NonNullList<Hitbox> hitbox;
	
	public HitboxCapabilities(EntityPlayer player) {
		this(player, NonNullList.create());
	}
	
	public HitboxCapabilities(EntityPlayer player, NonNullList<Hitbox> hitboxes) {
		this.player = player;
		this.setHitbox(hitboxes);
	}
	
	public void addHitbox(Hitbox... hitbox) {
		for(Hitbox box : hitbox) {
			this.hitbox.add(box);
		}
	}
	
	public NonNullList<Hitbox> getHitbox(){
		return this.hitbox;
	}
	
	public void setHitbox(NonNullList<Hitbox> list) {
		this.hitbox = list;
	}
	
	public void clearHitbox() {
		this.hitbox = NonNullList.create();
	}

	@Override
	public World getParentEntityWorld() {
		return this.player.getEntityWorld();
	}

	@Override
	public Entity[] getParts() {
		return this.hitbox.toArray(new Hitbox[this.hitbox.size()]);
	}

	@Override
	public boolean attackEntityFromHitbox(Hitbox hitboxPart, DamageSource source, float damage) {
		if (hitboxPart != null)
			if (!hitboxPart.getEntityWorld().isRemote) {
				if (source.getTrueSource() instanceof EntityPlayer) {
					EntityPlayer player = (EntityPlayer) source.getTrueSource();
					if (hitboxPart.getPotionEffect() != null)
						for (PotionEffect potionEffect : hitboxPart.getPotionEffect()) {
							this.player.addPotionEffect(potionEffect);
						}
					this.player.attackEntityFrom(source, hitboxPart.getHitboxDamage());
					return true;
				}
			}
		return false;
	}
	
	@SubscribeEvent
	public static void updateHitbox(LivingUpdateEvent event) {
		if(event.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			
			float rotation = player.getRotationYawHead() * 0.017453292F;
			float sinRotation = MathHelper.sin(rotation);
			float cosRotation = MathHelper.cos(rotation);
			
			player.getCapability(HitboxAPI.HITBOX_CAP, null).hitbox.forEach(box -> box.updateAndSetPosition(player.posX, player.posY, player.posZ));
			player.getCapability(HitboxAPI.HITBOX_CAP, null).hitbox.forEach(box -> System.out.println(box.hitboxName + " " + box.getPosition()));
		}
	}
	
	@Override
	public NBTTagCompound serializeNBT() {
		return null;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {

	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return HitboxAPI.HITBOX_CAP != null && capability == HitboxAPI.HITBOX_CAP; 
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return HitboxAPI.HITBOX_CAP != null && capability == HitboxAPI.HITBOX_CAP ? (T)this : null; 
	}
	
	public static class Storage implements Capability.IStorage<HitboxCapabilities> {

	    @Override
	    public NBTBase writeNBT(Capability<HitboxCapabilities> capability, HitboxCapabilities instance, EnumFacing side)
	    {
	    	return null;
	    }
	    
	    @Override
	    public void readNBT(Capability<HitboxCapabilities> capability, HitboxCapabilities instance, EnumFacing side, NBTBase nbt){}}

		public static class Factory implements Callable<HitboxCapabilities> {

	    @Override
	    public HitboxCapabilities call() throws Exception
	    {
	        return null;
	    }
	}
}
