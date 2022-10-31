package io.github.m00nwtchr.openweaponry.devices;

import io.github.m00nwtchr.openweaponry.Reference;
import li.cil.oc2.api.bus.device.provider.ItemDeviceProvider;
import li.cil.oc2.api.util.Registries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModDevices {
	public static final DeferredRegister<ItemDeviceProvider> ITEM_DEVICES = DeferredRegister.create(Registries.ITEM_DEVICE_PROVIDER, Reference.MOD_ID);

	public static final RegistryObject<DetonatorModuleItemDevice.Provider> DETONATOR_MODULE_DEVICE = ITEM_DEVICES.register("detonator", DetonatorModuleItemDevice.Provider::new);
}
