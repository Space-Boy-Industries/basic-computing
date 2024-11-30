package industries.spaceboy.basicComputing

import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Identifier

class BasicComputingBlocks {
    val COMPUTER = register(
        AbstractBlock.Settings.create().sounds(BlockSoundGroup.COPPER),
        "computer"
    )

    private fun register(blockSettings: AbstractBlock.Settings, name: String, shouldRegisterItem: Boolean = true): Block {
        val id: Identifier = Identifier.of(BasicComputing.MOD_ID, name)
        blockSettings.registryKey(RegistryKey.of(RegistryKeys.BLOCK, id))
        val block = Block(blockSettings)

        if (shouldRegisterItem) {
            val itemSettings = Item.Settings()
                .registryKey(RegistryKey.of(RegistryKeys.ITEM, id))
            val blockItem = BlockItem(block, itemSettings)
            Registry.register(Registries.ITEM, id, blockItem)!!
        }

        return Registry.register(Registries.BLOCK, id, block)!!
    }
}
