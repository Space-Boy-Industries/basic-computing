package industries.spaceboy.basicComputing.blocks

import com.mojang.serialization.MapCodec
import industries.spaceboy.basicComputing.blockEntities.ComputerBlockEntity
import industries.spaceboy.basicComputing.lib.basic.BasicInterpreter
import industries.spaceboy.basicComputing.lib.basic.ExecutionContext
import industries.spaceboy.basicComputing.lib.basic.Parser
import industries.spaceboy.basicComputing.lib.basic.Program
import net.minecraft.block.BlockRenderType
import net.minecraft.block.BlockState
import net.minecraft.block.BlockWithEntity
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World


// i feel like function points (or callback functions have potential for great evil.. maybe reconsider design)
class ComputerExecutionContext(
    private val printCallback: (value: Any) -> Unit,
    program: Program
): ExecutionContext(program) {
    override fun print(value: Any) {
        printCallback(value)
    }
}

class ComputerBlock(settings: Settings): BlockWithEntity(settings) {
    companion object {
        const val ID: String = "computer"
    }

    override fun onUse(
        state: BlockState?,
        world: World?,
        pos: BlockPos?,
        player: PlayerEntity?,
        hit: BlockHitResult?
    ): ActionResult {
        if (player == null) {
            return ActionResult.FAIL
        }

        if (world == null) {
            return ActionResult.FAIL
        }

        if (world.isClient) {
            return ActionResult.SUCCESS
        }

        val blockEntity = world.getBlockEntity(pos) as ComputerBlockEntity


        try {
            val src = blockEntity.getRom()
            val program = Parser(src).parseProgram()
            val ctx = ComputerExecutionContext(
                { value -> sendMessageToPlayer(player, value.toString())},
                program
            )
            val interpreter = BasicInterpreter(ctx)
            interpreter.executeAll()
        } catch (e: Exception) {
            sendMessageToPlayer(player, "Error: ${e.message}")
        }

        return ActionResult.SUCCESS
    }

    override fun createBlockEntity(pos: BlockPos?, state: BlockState?): BlockEntity? {
        if (pos == null || state == null) { // explode into a million pieces
            return null
        }

        return ComputerBlockEntity(pos, state)
    }

    override fun getCodec(): MapCodec<out BlockWithEntity> {
        return createCodec(::ComputerBlock)
    }

    override fun getRenderType(state: BlockState?): BlockRenderType {
        return BlockRenderType.MODEL;
    }

    // Function to send the chat message to a player
    private fun sendMessageToPlayer(player: PlayerEntity, message: String) {
        if (player is ServerPlayerEntity) {
            val textMessage = Text.literal(message)  // Create a text message
            player.sendMessage(textMessage, false)  // Send the message to the player
        }
    }
}
