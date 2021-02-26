package com.github.charlyb01.sihywtcamc.init;

import com.github.charlyb01.sihywtcamc.config.ModConfig;
import com.github.charlyb01.sihywtcamc.init.attributes.*;

public class AttributesInit {
    private AttributesInit() {
    }

    public static void init() {
        if (ModConfig.get().toolsConfig.newAttributesValues) {
            SwordInit.init();
            AxeInit.init();
            PickaxeInit.init();
            ShovelInit.init();
            HoeInit.init();
        }
    }
}
