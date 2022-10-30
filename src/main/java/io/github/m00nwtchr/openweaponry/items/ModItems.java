package io.github.m00nwtchr.openweaponry.items;

import io.github.m00nwtchr.openweaponry.Reference;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class ModItems {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);

	public static final RegistryObject<DetonatorCardItem> DETONATOR_CARD = ITEMS.register("detonator_card", DetonatorCardItem::new);
}
