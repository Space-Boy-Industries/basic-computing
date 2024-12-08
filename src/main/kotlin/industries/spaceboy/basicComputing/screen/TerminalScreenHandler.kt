package industries.spaceboy.basicComputing.screen

import industries.spaceboy.basicComputing.BasicComputing
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.ScreenHandler

class TerminalScreenHandler(syncId: Int, playerInventory: PlayerInventory) : ScreenHandler(BasicComputing.SCREEN_TYPES.TERMINAL_SCREEN_HANDLER, syncId) {
    override fun quickMove(player: PlayerEntity?, slot: Int): ItemStack {
        TODO("Not yet implemented")
    }

    override fun canUse(player: PlayerEntity): Boolean {
        return true  // You can add your custom logic here (e.g. check if the player is close enough to the block)
    }
}
