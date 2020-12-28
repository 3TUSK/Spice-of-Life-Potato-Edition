package com.kevun1.solpotato.item;

import com.kevun1.solpotato.client.gui.FoodBookScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

public final class FoodBookItem extends Item {
	public FoodBookItem() {
		super(new Properties().group(ItemGroup.MISC));
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		if (player.isUser()) {
			DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> FoodBookScreen.open(player));
		}
		
		return new ActionResult<>(ActionResultType.SUCCESS, player.getHeldItem(hand));
	}
}
