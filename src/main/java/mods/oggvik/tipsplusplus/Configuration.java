package mods.oggvik.tipsplusplus;

import java.util.ArrayList;
import java.util.List;

import mods.oggvik.tipsplusplus.client.TipCorner;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.common.ForgeConfigSpec.EnumValue;

public class Configuration {
    
    private final ForgeConfigSpec spec;
    
    private final ConfigValue<Integer> cycleTime;
    private final EnumValue<TipCorner> tipCorner;
    private final ConfigValue<List<? extends String>> removedTips;
    private final ConfigValue<List<? extends String>> removedNamespaces;
    
    public Configuration() {
        
        final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        
        builder.comment("The amount of time to wait before cycling the displayed tip. This is in miliseconds. 1000ms = 1s");
        this.cycleTime = builder.define("cycleTime", 5 * 1000);
        
        builder.comment("The corner of the screen where tips are displayed.");
        this.tipCorner = builder.defineEnum("tipCorner", TipCorner.BOTTOM_LEFT);

        builder.comment("A list of tip IDs to remove from the list. Restart is required for changes to take effect.");
        this.removedTips = builder.defineList("removedTips", new ArrayList<String>(), s -> ResourceLocation.isResouceNameValid((String) s));
        
        builder.comment("A list of tip namespaces to remove from the list. Restart is requird for changes to take effect.");
        this.removedNamespaces = builder.defineList("removedNamespaces", new ArrayList<String>(), s -> ResourceLocation.isResouceNameValid((String) s));
        
        this.spec = builder.build();
    }
    
    public ForgeConfigSpec getSpec () {
        
        return this.spec;
    }
    
    public int getCycleTime () {
        
        return this.cycleTime.get();
    }
    
    public TipCorner getTipCorner () {

        return this.tipCorner.get();
    }

    public boolean canLoadTip (ResourceLocation tipId) {
        
        return !this.removedNamespaces.get().contains(tipId.getNamespace()) && !this.removedTips.get().contains(tipId.toString());
    }
}
