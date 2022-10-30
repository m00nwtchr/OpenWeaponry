package io.github.m00nwtchr.openweaponry;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import io.github.m00nwtchr.openweaponry.items.ModItems;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Reference.MOD_ID)
public class OpenWeaponry {
	private static final Logger LOGGER = LogUtils.getLogger();

	public OpenWeaponry() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

		eventBus.addListener(this::setup);

		ModItems.ITEMS.register(eventBus);
	}

	private void setup(final FMLCommonSetupEvent event) {
		LOGGER.info("HELLO FROM PREINIT");
		LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
	}
}
