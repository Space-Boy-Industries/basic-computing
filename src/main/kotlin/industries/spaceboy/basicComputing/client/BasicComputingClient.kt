package industries.spaceboy.basicComputing.client

import industries.spaceboy.basicComputing.BasicComputing
import industries.spaceboy.basicComputing.client.screen.TerminalScreen
import net.fabricmc.api.ClientModInitializer
import net.minecraft.client.gui.screen.ingame.HandledScreens

class BasicComputingClient : ClientModInitializer {

    override fun onInitializeClient() {
        HandledScreens.register(BasicComputing.SCREEN_TYPES.TERMINAL_SCREEN_HANDLER, ::TerminalScreen)
    }
}
