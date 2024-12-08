package industries.spaceboy.basicComputing.blockEntities

import industries.spaceboy.basicComputing.BasicComputing
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.RegistryWrapper
import net.minecraft.util.math.BlockPos

class ComputerBlockEntity(pos: BlockPos, state: BlockState): BlockEntity(BasicComputing.BLOCK_ENTITIES.COMPUTER_BLOCK, pos, state) {
    private var rom = """
        LET X = 5
        LET Y = 2
        LET Z = X + Y
        IF Z == 7 GOTO dothing
        IF Z == 6 GOTO dontdothing

        LABEL dothing
        PRINT "Z is 7"
        GOTO exit

        LABEL dontdothing
        PRINT "Z is not 7"
        GOTO exit

        LABEL exit
        PRINT "Done"
    """.trimIndent()

    override fun writeNbt(nbt: NbtCompound?, registries: RegistryWrapper.WrapperLookup?) {
        nbt?.putString("rom", rom)
        super.writeNbt(nbt, registries)
    }

    override fun readNbt(nbt: NbtCompound?, registries: RegistryWrapper.WrapperLookup?) {
        rom = nbt?.getString("rom") ?: ""
        super.readNbt(nbt, registries)
    }

    fun getRom(): String {
        return rom
    }
}