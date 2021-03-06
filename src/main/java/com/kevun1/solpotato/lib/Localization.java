package com.kevun1.solpotato.lib;

import com.kevun1.solpotato.SOLPotato;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.IForgeRegistryEntry;

public final class Localization {
	public static String keyString(String domain, IForgeRegistryEntry<?> entry, String path) {
		final ResourceLocation location = entry.getRegistryName();
		assert location != null;
		return keyString(domain, location.getPath() + "." + path);
	}
	
	/** e.g. keyString("tooltip", "eaten_status.not_eaten_1") -> "tooltip.solpotato.eatenStatus.not_eaten_1") */
	public static String keyString(String domain, String path) {
		return domain + "." + SOLPotato.MOD_ID + "." + path;
	}
	
	@OnlyIn(Dist.CLIENT)
	public static String localized(String domain, IForgeRegistryEntry<?> entry, String path, Object... args) {
		return I18n.format(keyString(domain, entry, path), args);
	}
	
	public static IFormattableTextComponent localizedComponent(String domain, IForgeRegistryEntry<?> entry, String path, Object... args) {
		return new TranslationTextComponent(keyString(domain, entry, path), args);
	}
	
	@OnlyIn(Dist.CLIENT)
	public static String localized(String domain, String path, Object... args) {
		return I18n.format(keyString(domain, path), args);
	}
	
	public static IFormattableTextComponent localizedComponent(String domain, String path, Object... args) {
		return new TranslationTextComponent(keyString(domain, path), args);
	}
	
	@OnlyIn(Dist.CLIENT)
	public static String localizedQuantity(String domain, String path, int number) {
		return number == 1
			? I18n.format(keyString(domain, path + ".singular"))
			: I18n.format(keyString(domain, path + ".plural"), number);
	}
	
	public static IFormattableTextComponent localizedQuantityComponent(String domain, String path, int number) {
		return number == 1
			? new TranslationTextComponent(keyString(domain, path + ".singular"))
			: new TranslationTextComponent(keyString(domain, path + ".plural"), number);
	}
	
	public static String formatBigNumber(int number) {
		if (number < 1000) {
			return "" + number;
		} else if (number < 10_000) {
			return Math.round(number / 1000F) + "." + Math.round((number % 1000) / 100F) + "k";
		} else {
			return Math.round(number / 1000F) + "k";
		}
	}
	
	private Localization() {}
}
