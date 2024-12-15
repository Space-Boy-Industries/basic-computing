package industries.spaceboy.basicComputing.screen

import industries.spaceboy.basicComputing.block.ModBlocks
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.resource.featuretoggle.FeatureSet
import net.minecraft.screen.ScreenHandlerType

class ModScreens {
    companion object {
        val TERMINAL_SCREEN_HANDLER: ScreenHandlerType<TerminalScreenHandler> = Registry.register(
            Registries.SCREEN_HANDLER,
            ModBlocks.COMPUTER_BLOCK_ID,
            ScreenHandlerType(::TerminalScreenHandler, FeatureSet.empty())
        )
    }
}