package industries.spaceboy.basicComputing.block.entity

import industries.spaceboy.basicComputing.BasicComputing
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier


class BasicComputingBlockEntitiesTypes {
    private fun<T : BlockEntity?> register(
        path: String?,
        blockEntityType: BlockEntityType<T>
    ): BlockEntityType<T>? {
        return Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            Identifier.of(BasicComputing.MOD_ID, path),
            blockEntityType
        )
    }

    val COMPUTER_BLOCK: BlockEntityType<ComputerBlockEntity>? = register(
        "computer_block",  // For versions 1.21.2 and above,
        FabricBlockEntityTypeBuilder.create(
            ::ComputerBlockEntity,
            BasicComputing.BLOCKS.COMPUTER_BLOCK
        ).build()
    )

    fun initialize() {
    }
}