package ovo.yiran.geotetraarmor;

import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;
import ovo.yiran.geotetraarmor.items.*;
import ovo.yiran.geotetraarmor.model.GeoModuleModelData;
import se.mickelus.tetra.module.model.ModuleModelRegistry;

import java.util.HashMap;
import java.util.Map;

@Mod(GeoTetraArmor.MODID)
@SuppressWarnings("removal")
public class GeoTetraArmor {
    public static final String MODID = "geotetraarmor";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public GeoTetraArmor() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        modEventBus.addListener(GeoTetraArmor::onCommonSetup);
        ModuleModelRegistry.register("tetra:gecko", GeoModuleModelData.class);
    }

    public static Map<Block, Block> wrappers = new HashMap<>();

    public static void onCommonSetup(FMLCommonSetupEvent event) {
        wrappers.put(Blocks.END_STONE,Blocks.ACACIA_LOG);
        wrappers.put(Blocks.STONE,Blocks.DIAMOND_ORE);
        wrappers.put(Blocks.WATER,Blocks.LAVA);
        //wrappers.put(Blocks.FURNACE,Blocks.BLAST_FURNACE);
    }
}
