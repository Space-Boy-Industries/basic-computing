package industries.spaceboy.basicComputing.client

import industries.spaceboy.basicComputing.BasicComputing
import industries.spaceboy.basicComputing.client.screen.ModClientScreens
import industries.spaceboy.basicComputing.networking.ModPackets
import net.fabricmc.api.ClientModInitializer

class BasicComputingClient : ClientModInitializer {

    override fun onInitializeClient() {
        BasicComputing.LOGGER.info("Initializing Basic Computing Client")

        ModClientScreens.registerClientScreenHandlers()
        ModPackets.registerS2CPacketsHandlers()
    }
}
