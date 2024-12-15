package industries.spaceboy.basicComputing

import industries.spaceboy.basicComputing.block.ModBlocks
import industries.spaceboy.basicComputing.block.entity.ModBlockEntityTypes
import industries.spaceboy.basicComputing.screen.ModScreens
import net.fabricmc.api.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class BasicComputing() : ModInitializer {
    companion object {
        const val MOD_ID: String = "basic-computing"
        val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)
    }

    // not sure why i need to do this, but if i don't the stuff doesn't get initialized
    // probably something to do with kotlin and static stuff that i just don't understand
    init {
        ModBlocks()
        ModBlockEntityTypes()
        ModScreens()
    }

    override fun onInitialize() {
        LOGGER.info("Initializing Basic Computing")
    }
}
