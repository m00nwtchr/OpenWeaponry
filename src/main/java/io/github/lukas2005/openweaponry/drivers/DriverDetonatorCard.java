package io.github.lukas2005.openweaponry.drivers;

import io.github.lukas2005.openweaponry.ModConfig;
import io.github.lukas2005.openweaponry.items.ModItems;
import li.cil.oc.api.Network;
import li.cil.oc.api.driver.item.Slot;
import li.cil.oc.api.internal.Tablet;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.EnvironmentHost;
import li.cil.oc.api.network.ManagedEnvironment;
import li.cil.oc.api.network.Visibility;
import li.cil.oc.api.prefab.AbstractManagedEnvironment;
import li.cil.oc.api.prefab.DriverItem;
import li.cil.oc.common.entity.Drone;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import scala.Option;

public class DriverDetonatorCard extends DriverItem {

    public DriverDetonatorCard() {
        super(new ItemStack(ModItems.DETONATOR_CARD1), new ItemStack(ModItems.DETONATOR_CARD2));
    }

    @Override
    public ManagedEnvironment createEnvironment(ItemStack stack, EnvironmentHost host) {
        return new Environment(host, stack.getItem() == ModItems.DETONATOR_CARD1 ? 0 : 1);
    }

    @Override
    public String slot(ItemStack itemStack) {
        return Slot.Card;
    }

    @Override
    public int tier(ItemStack stack) {
        return stack.getItem() == ModItems.DETONATOR_CARD1 ? 0 : 1;
    }

    public static class Environment extends AbstractManagedEnvironment {
        protected final EnvironmentHost host;
        protected final int tier;
        protected final int max;

        public Environment(EnvironmentHost host, int tier) {
            this.host = host;
            setNode(Network.newNode(this, Visibility.Neighbors)
                    .withComponent("detonator")
                    .create());
            this.tier = tier;
            this.max = tier == 0 ? ModConfig.MAX_TIER_1_VALUE : ModConfig.MAX_TIER_2_VALUE;
        }

        @Callback(direct = true, limit = 16)
        public Object[] explode(Context context, Arguments args) {
            int force = args.checkInteger(0);
            boolean silent = args.optBoolean(1, false);

            if (force > max) {
                if (silent) {
                    force = max;
                } else {
//                    throw new IllegalArgumentException("Value "+force+" exceeds the max of "+max+(tier == 0 ? ", you can upgrade your card for a bigger explosion" : ""));
                    throw new IllegalArgumentException("Value "+force+" exceeds the max of "+max);
                }
            }
            World world = host.world();

            final int f = force;
            FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(() -> {

                Entity entity = this.host instanceof Drone ? (Entity) this.host : this.host instanceof Tablet ? ((Tablet) this.host).player() : null;

                world.createExplosion(entity, host.xPosition(), host.yPosition(), host.zPosition(), f, true);

                if (entity instanceof Drone) {
                    Drone drone = (Drone) entity;

//                    boolean flag = false;
//                    for (Option<ManagedEnvironment> e : drone.components().components()) {
//                        ManagedEnvironment env = e.get();
//                    }

                    entity.setDead();
                }
            });
            return new Object[0];
        }
    }
}
