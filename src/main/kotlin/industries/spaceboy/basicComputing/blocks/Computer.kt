package industries.spaceboy.basicComputing.blocks

import industries.spaceboy.basicComputing.lib.basic.BasicInterpreter
import industries.spaceboy.basicComputing.lib.basic.ExecutionContext
import industries.spaceboy.basicComputing.lib.basic.Parser
import industries.spaceboy.basicComputing.lib.basic.Tokenizer
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

// i feel like function points (or callback functions have potential for great evil.. maybe reconsider design)
class ComputerExecutionContext(
    private val printCallback: (value: Any) -> Unit
): ExecutionContext() {
    // eventually variables needs to be nbt data
    private val variables = mutableMapOf<String, Any>()

    override fun print(value: Any) {
        printCallback(value)
    }

    override fun assign(variable: String, value: Any) {
        variables[variable] = value
    }

    override fun getVariable(variable: String): Any {
        return variables[variable] ?: throw IllegalArgumentException("Variable not found: $variable")
    }
}

class Computer(settings: Settings): Block(settings) {
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

        if (!world.isClient) {
            try {
                val src = """
                    PRINT "HELLO WORLD"
                    LET X = 5
                    LET Y = 2
                    PRINT X + Y
                """.trimIndent();

                val program = Parser(src).parseProgram()
                val ctx = ComputerExecutionContext({ value -> sendMessageToPlayer(player, value.toString())})
                val interpreter = BasicInterpreter(ctx, program)
                interpreter.executeAll()
            } catch (e: Exception) {
                sendMessageToPlayer(player, "Error: ${e.message}")
            }
        }

        return ActionResult.SUCCESS
    }

    // Function to send the chat message to a player
    private fun sendMessageToPlayer(player: PlayerEntity, message: String) {
        if (player is ServerPlayerEntity) {
            val textMessage = Text.literal(message)  // Create a text message
            player.sendMessage(textMessage, false)  // Send the message to the player
        }
    }
}
