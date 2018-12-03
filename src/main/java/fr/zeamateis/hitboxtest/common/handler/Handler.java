package fr.zeamateis.hitboxtest.common.handler;

import fr.zeamateis.hibox.common.HitboxAPI;
import fr.zeamateis.hibox.common.entity.base.Hitbox;
import fr.zeamateis.hibox.common.hitbox.HitboxCapabilities;
import fr.zeamateis.hitboxtest.common.HitboxTestMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import scala.actors.threadpool.Arrays;

@EventBusSubscriber(modid = HitboxTestMod.MODID)
public class Handler {
	
	/**
	 * @author Wind_Blade
	 * @version 0.1
	 */
	
	@SubscribeEvent
	public static void onPlayerChat(ServerChatEvent event) {
		if(event.getMessage().equals("hitbox")) {
			EntityPlayerMP player = event.getPlayer();
			HitboxCapabilities cap = player.getCapability(HitboxAPI.HITBOX_CAP, null);
			NonNullList<Hitbox> list = NonNullList.create();			
			list.addAll(Arrays.asList(new Hitbox[] {new Hitbox(cap, "head", 0.5F), new Hitbox(cap, "body_1", 0.25F, 0.8F), new  Hitbox(cap, "body_2", 0.25F, 0.8F), new Hitbox(cap, "leftArm", 0.25F, 0.8F), new Hitbox(cap, "rightArm", 0.25F, 0.8F), new Hitbox(cap, "leftLeg", 0.25F, 0.7F), new Hitbox(cap, "rightLeg", 0.25F, 0.7F)}));
			player.getCapability(HitboxAPI.HITBOX_CAP, null).setHitbox(list);
		}
	}
	
	@SubscribeEvent
	public static void onAttachCapability(AttachCapabilitiesEvent<Entity> event)
	{
	    if(event.getObject() instanceof EntityPlayer)
	    {
			event.addCapability(new ResourceLocation(HitboxAPI.MODID + ":Hitbox_CAP"), new HitboxCapabilities((EntityPlayer) event.getObject()));    
	    }	    
	}
}