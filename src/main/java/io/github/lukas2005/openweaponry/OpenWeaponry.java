package io.github.lukas2005.openweaponry;

import io.github.lukas2005.openweaponry.drivers.DriverDetonatorCard;
import li.cil.oc.api.Driver;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.github.lukas2005.openweaponry.OpenWeaponry.*;

@Mod(modid = MODID, name=NAME, version=VERSION, dependencies = "required-after:opencomputers")
public class OpenWeaponry {

    public static final Logger LOGGER = LogManager.getLogger();

    public static final String MODID = "openweaponry";
    public static final String NAME = "OpenWeaponry";
    public static final String VERSION = "1.0.0";

    @Mod.EventHandler
    public static void onInit(FMLInitializationEvent e) {
        Driver.add(new DriverDetonatorCard());
    }

}
