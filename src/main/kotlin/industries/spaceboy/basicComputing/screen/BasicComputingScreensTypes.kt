package industries.spaceboy.basicComputing.screen

import industries.spaceboy.basicComputing.BasicComputing
import industries.spaceboy.basicComputing.block.ComputerBlock
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.resource.featuretoggle.FeatureSet
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.util.Identifier

class BasicComputingScreensTypes {
    val TERMINAL_SCREEN_HANDLER = Registry.register(
        Registries.SCREEN_HANDLER,
        Identifier.of(BasicComputing.MOD_ID,ComputerBlock.ID),
        ScreenHandlerType(::TerminalScreenHandler, FeatureSet.empty())
    )
}