package industries.spaceboy.basicComputing

import industries.spaceboy.basicComputing.block.BasicComputingBlocks
import industries.spaceboy.basicComputing.block.entity.BasicComputingBlockEntitiesTypes
import industries.spaceboy.basicComputing.screen.BasicComputingScreensTypes
import net.fabricmc.api.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class BasicComputing : ModInitializer {
    companion object {
        const val MOD_ID: String = "basic-computing"
        val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)
        val BLOCKS = BasicComputingBlocks()
        val BLOCK_ENTITIES = BasicComputingBlockEntitiesTypes()
        val SCREEN_TYPES = BasicComputingScreensTypes()
    }

    override fun onInitialize() {
        LOGGER.info("Initializing Basic Computing")
    }
}
