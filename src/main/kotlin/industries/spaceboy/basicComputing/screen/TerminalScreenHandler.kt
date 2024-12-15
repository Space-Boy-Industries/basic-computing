package industries.spaceboy.basicComputing.screen

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.ScreenHandler

class TerminalScreenHandler(syncId: Int, inventory: PlayerInventory) : ScreenHandler(ModScreens.TERMINAL_SCREEN_HANDLER, syncId) {
    override fun quickMove(player: PlayerEntity?, slot: Int): ItemStack {
        TODO("Not yet implemented")
    }

    override fun canUse(player: PlayerEntity): Boolean {
        return true  // You can add your custom logic here (e.g. check if the player is close enough to the block)
    }
}
