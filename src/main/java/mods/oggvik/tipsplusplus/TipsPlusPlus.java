package mods.oggvik.tipsplusplus;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mods.oggvik.tipsplusplus.data.tip.SimpleTip;
import mods.oggvik.tipsplusplus.data.tip.TipReloadListener;
import mods.oggvik.tipsplusplus.gui.TipsListScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.IReloadableResourceManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.fml.network.FMLNetworkConstants;

@Mod(TipsPlusPlus.MOD_ID)
public class TipsPlusPlus {
    
    public static final String MOD_ID = "tips";
    public static final String MOD_NAME = "Tips++";
    public static final Logger LOG = LogManager.getLogger(MOD_NAME);
    
    public static final TipsAPI API = new TipsAPI();
    public static final Configuration CFG = new Configuration();
    
    public TipsPlusPlus() {
        
        final ModLoadingContext ctx = ModLoadingContext.get();
        ctx.registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.of( () -> FMLNetworkConstants.IGNORESERVERONLY, (a, b) -> true));
        
        if (FMLEnvironment.dist.isClient()) {
            
            ctx.registerConfig(Type.CLIENT, CFG.getSpec());
            ctx.registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, () -> TipsListScreen::factory);
            TipsPlusPlus.initClient();
        }
    }
    
    @OnlyIn(Dist.CLIENT)
    public static void initClient () {
        
        API.registerTipSerializer(SimpleTip.TYPE_ID, SimpleTip.SERIALIZER);
        ((IReloadableResourceManager) Minecraft.getInstance().getResourceManager()).addReloadListener(new TipReloadListener());
    }
}
