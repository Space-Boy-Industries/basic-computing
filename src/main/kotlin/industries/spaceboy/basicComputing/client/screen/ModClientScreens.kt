package industries.spaceboy.basicComputing.client.screen

import industries.spaceboy.basicComputing.BasicComputing
import industries.spaceboy.basicComputing.screen.ModScreens
import net.minecraft.client.gui.screen.ingame.HandledScreens

class ModClientScreens {
    companion object {
        fun registerClientScreenHandlers() {
            BasicComputing.LOGGER.info("Registering Client Screen Handlers")
            HandledScreens.register(ModScreens.TERMINAL_SCREEN_HANDLER, ::TerminalScreen)
        }
    }
}