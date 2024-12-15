package industries.spaceboy.basicComputing.networking

import industries.spaceboy.basicComputing.BasicComputing

class ModPackets {
    companion object {

        fun registerC2SPacketsHandlers() {
            BasicComputing.LOGGER.info("Registering C2S Packets Handlers")
        }

        fun registerS2CPacketsHandlers() {
            BasicComputing.LOGGER.info("Registering S2C Packet Handlers")
        }
    }
}