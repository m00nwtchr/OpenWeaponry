package io.github.m00nwtchr.openweaponry;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class ModConfig {

	public static final Pair<ModConfig, ForgeConfigSpec> CONFIG = new ForgeConfigSpec.Builder()
			.configure(ModConfig::new);

	public final ConfigValue<Integer> maxForce;

	public ModConfig(ForgeConfigSpec.Builder builder) {
		this.maxForce = builder.comment("Maximum force value for the `explode` method.").define("max_force", 12);
	}

}
