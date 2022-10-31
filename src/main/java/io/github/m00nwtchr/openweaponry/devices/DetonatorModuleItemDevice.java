package io.github.m00nwtchr.openweaponry.devices;

import java.util.Optional;

import io.github.m00nwtchr.openweaponry.ModConfig;
import io.github.m00nwtchr.openweaponry.items.ModItems;
import li.cil.oc2.api.bus.device.ItemDevice;
import li.cil.oc2.api.bus.device.object.Callback;
import li.cil.oc2.api.bus.device.provider.ItemDeviceQuery;
import li.cil.oc2.common.bus.device.provider.util.AbstractItemDeviceProvider;
import li.cil.oc2.common.bus.device.rpc.item.AbstractItemRPCDevice;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Entity.RemovalReason;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion.BlockInteraction;
import net.minecraft.world.level.Level;

public final class DetonatorModuleItemDevice extends AbstractItemRPCDevice {

	private final Optional<Entity> entity;

	protected DetonatorModuleItemDevice(ItemStack identity, Optional<Entity> entity) {
		super(identity, "detonator");
		this.entity = entity;
	}

	@Callback
	public void explode(int force) {
		int max = ModConfig.CONFIG.getKey().maxForce.get();

		if (force > max) {
			force = max;
		}

		final int f = force;

		Level level = entity.isPresent() ? entity.get().level : null;

		if (entity.isPresent() && level instanceof ServerLevel) {
			double x = entity.get().getX();
			double y = entity.get().getY();
			double z = entity.get().getZ();

			((ServerLevel) level).getServer().submit(() -> {
				level.explode(entity.get(), x, y, z, f, BlockInteraction.BREAK);

				entity.get().setRemoved(RemovalReason.KILLED);
			});
		}
	}

	public static final class Provider extends AbstractItemDeviceProvider {
		public Provider() {
			super(ModItems.DETONATOR_MODULE);
		}

		///////////////////////////////////////////////////////////////////

		@Override
		protected Optional<ItemDevice> getItemDevice(final ItemDeviceQuery query) {
			return Optional.of(new DetonatorModuleItemDevice(query.getItemStack(), query.getContainerEntity()));
		}

		@Override
		protected int getItemDeviceEnergyConsumption(final ItemDeviceQuery query) {
			// return Config.soundCardEnergyPerTick;
			return 0;
		}
	}
}