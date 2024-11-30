package industries.spaceboy.basicComputing

import net.fabricmc.api.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class BasicComputing : ModInitializer {
    companion object {
        const val MOD_ID: String = "basic-computing"
        val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)
        val BLOCKS = BasicComputingBlocks()
        val BLOCK_ENTITIES = BasicComputingBlockEntitiesTypes()
    }

    override fun onInitialize() {
        LOGGER.info("Initializing Basic Computing")
    }
}
