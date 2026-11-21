package com.ae2qol;

import com.ae2qol.client.ClientSetup;
import com.ae2qol.net.QolNetwork;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(Ae2Qol.MODID)
public final class Ae2Qol {
    public static final String MODID = "ae2qol";
    public static final Logger LOG = LoggerFactory.getLogger(MODID);

    public Ae2Qol(IEventBus modBus, ModContainer container) {
        modBus.addListener(QolNetwork::register);
        NeoForge.EVENT_BUS.register(new ServerEvents());

        if (FMLEnvironment.dist == Dist.CLIENT) {
            ClientSetup.init(modBus);
        }
    }
}
