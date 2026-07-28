package mods.oggvik.tipsplusplus;

import mods.oggvik.tipsplusplus.client.TipRenderer;
import mods.oggvik.tipsplusplus.data.tip.ITip;
import net.minecraft.client.gui.screen.ConnectingScreen;
import net.minecraft.client.gui.screen.DirtMessageScreen;
import net.minecraft.client.gui.screen.DisconnectedScreen;
import net.minecraft.client.gui.screen.IngameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.WorkingScreen;
import net.minecraft.client.gui.screen.WorldLoadProgressScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiScreenEvent.DrawScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = TipsPlusPlus.MOD_ID, value = Dist.CLIENT)
public class TipRenderHandler {
    
    private static long initTime = System.currentTimeMillis();
    private static ITip tip;
    
    @SubscribeEvent
    public static void onGuiDraw (DrawScreenEvent event) {
        
        final Screen screen = event.getGui();
        
        if (screen instanceof DirtMessageScreen || screen instanceof ConnectingScreen || screen instanceof DisconnectedScreen || screen instanceof WorldLoadProgressScreen || screen instanceof WorkingScreen || screen instanceof IngameMenuScreen) {
            
            final long currentTime = System.currentTimeMillis();
            final int currentCycleTime = tip != null ? tip.getCycleTime() : TipsPlusPlus.CFG.getCycleTime();
            
            if (currentTime - initTime > currentCycleTime) {
                
                tip = TipsPlusPlus.API.getRandomTip();
                initTime = currentTime;
                
                if (tip != null) {
                    
                    TipsPlusPlus.LOG.debug("Displaying tip {} on screen {}.", tip.getId(), screen.getClass().getSimpleName());
                }
            }
            
            if (tip != null) {
                
                TipRenderer.render(event.getMatrixStack(), screen.width, screen.height, tip, TipsPlusPlus.CFG.getTipCorner());
            }
        }
    }
}
