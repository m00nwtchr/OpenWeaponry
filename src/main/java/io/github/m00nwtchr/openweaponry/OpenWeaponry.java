package io.github.m00nwtchr.openweaponry;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import io.github.m00nwtchr.openweaponry.devices.ModDevices;
import io.github.m00nwtchr.openweaponry.items.ModItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraft.world.item.CreativeModeTab;

@Mod(Reference.MOD_ID)
public class OpenWeaponry {
	private static final Logger LOGGER = LogUtils.getLogger();

	public static final CreativeModeTab TAB = new CreativeModeTab(Reference.MOD_ID) {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ModItems.DETONATOR_MODULE.get());
		}
	};

	public OpenWeaponry() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

		ModLoadingContext.get().registerConfig(Type.COMMON, ModConfig.CONFIG.getValue());

		eventBus.addListener(this::setup);

		ModItems.ITEMS.register(eventBus);
		ModDevices.ITEM_DEVICES.register(eventBus);
	}

	private void setup(final FMLCommonSetupEvent event) {
		LOGGER.info("HELLO FROM PREINIT");
		LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
	}
}
