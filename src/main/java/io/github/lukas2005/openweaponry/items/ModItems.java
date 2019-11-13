package io.github.lukas2005.openweaponry.items;

import io.github.lukas2005.openweaponry.OpenWeaponry;
import li.cil.oc.api.CreativeTab;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@Mod.EventBusSubscriber
@ObjectHolder(OpenWeaponry.MODID)
public class ModItems {

    @ObjectHolder("detonator_card")
    public static ItemDetonatorCard DETONATOR_CARD1 = null;

    @ObjectHolder("detonator_card2")
    public static ItemDetonatorCard DETONATOR_CARD2 = null;

    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> e) {
        e.getRegistry().registerAll(
                new ItemDetonatorCard().setRegistryName("detonator_card").setUnlocalizedName(OpenWeaponry.MODID+".detonator_card").setCreativeTab(CreativeTab.instance)
//                new ItemDetonatorCard().setRegistryName("detonator_card2").setUnlocalizedName(ExplosiveOC.MODID+".detonator_card2").setCreativeTab(CreativeTab.instance)
        );
    }

    @SubscribeEvent
    public static void onRegisterModels(ModelRegistryEvent e) {
        ModelLoader.setCustomModelResourceLocation(DETONATOR_CARD1, 0, new ModelResourceLocation(DETONATOR_CARD1.getRegistryName(), "inventory"));
    }

}
