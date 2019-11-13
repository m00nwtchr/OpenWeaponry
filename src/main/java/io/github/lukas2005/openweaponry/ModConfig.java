package io.github.lukas2005.openweaponry;

import net.minecraftforge.common.config.Config;

@Config(modid = OpenWeaponry.MODID)
public class ModConfig {

    @Config.Name("maxTier1Value")
    @Config.Comment("Maximum force value for the `explode` method for tier 1 cards")
    public static int MAX_TIER_1_VALUE = 10;

    @Config.Name("maxTier2Value")
    @Config.Comment("Maximum force value for the `explode` method for tier 2 cards")
    @Config.Ignore
    public static int MAX_TIER_2_VALUE = 15;
}
