package industries.spaceboy.basicComputing.block

import com.mojang.serialization.MapCodec
import industries.spaceboy.basicComputing.block.entity.ComputerBlockEntity
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

class ComputerBlock(settings: Settings): BlockWithEntity(settings) {
    override fun onUse(
        state: BlockState?,
        world: World?,
        pos: BlockPos?,
        player: PlayerEntity?,
        hit: BlockHitResult?
    ): ActionResult {
        if (world!!.isClient) {
            return ActionResult.SUCCESS
        }

        val screenHandlerFactory = state!!.createScreenHandlerFactory(world, pos)

        if (screenHandlerFactory != null) {
            player!!.openHandledScreen(screenHandlerFactory)
        } else {
            System.err.println("WARNING: failed to create screen handler factory")
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
