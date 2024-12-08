package industries.spaceboy.basicComputing.block

import industries.spaceboy.basicComputing.BasicComputing
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
    val COMPUTER_BLOCK = register(
        ComputerBlock(
            AbstractBlock.Settings.create()
                .sounds(BlockSoundGroup.COPPER)
                .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(BasicComputing.MOD_ID, ComputerBlock.ID)))
        ),
        ComputerBlock.ID
    )

    private fun register(block: Block, name: String, shouldRegisterItem: Boolean = true): Block {
        val id: Identifier = Identifier.of(BasicComputing.MOD_ID, name)

        if (shouldRegisterItem) {
            val itemSettings = Item.Settings()
                .registryKey(RegistryKey.of(RegistryKeys.ITEM, id))
            val blockItem = BlockItem(block, itemSettings)
            Registry.register(Registries.ITEM, id, blockItem)!!
        }

        return Registry.register(Registries.BLOCK, id, block)!!
    }
}
