package industries.spaceboy.basicComputing.client.screen

import industries.spaceboy.basicComputing.screen.TerminalScreenHandler
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.text.Text

class TerminalScreen(handler: TerminalScreenHandler, inventory: PlayerInventory, title: Text) : HandledScreen<TerminalScreenHandler>(handler, inventory, title){
    private var text = StringBuilder()
    private var cursorIndex = 0
    private var cursorLastBlink = 0.0
    private val cursorBlinkSpeed = 8.0
    private var cursorVisible = true

    // Split text into lines, and figure out which line the cursor is on
    private fun calcCursorPos(): Pair<Int, Int> {
        val lines = text.split("\n")
        var cursorY = 0
        var cursorX = cursorIndex
        for (line in lines) {
            if (cursorX <= line.length) {
                break
            }
            cursorX -= line.length + 1 // Move to the next line's index
            cursorY++
        }
        return Pair(cursorX, cursorY)
    }

    private fun insert(char: Char) {
        text.insert(cursorIndex, char)
        cursorIndex++
    }

    private fun delete() {
        if (cursorIndex > 0) {
            text.deleteCharAt(cursorIndex - 1)
            cursorIndex--
        }
    }

    private fun enter() {
        text.insert(cursorIndex, '\n')
        cursorIndex++
    }

    private fun moveCursorLeft() {
        if (cursorIndex > 0) {
            cursorIndex--
        }
    }

    private fun moveCursorRight() {
        if (cursorIndex < text.length) {
            cursorIndex++
        }
    }

    private fun moveCursorUp() {
        val (cursorX, cursorY) = calcCursorPos()
        val lines = text.split("\n")
        if (cursorY > 0) {
            var index = 0
            for (i in 0 until cursorY - 1) {
                index += lines[i].length + 1
            }
            cursorIndex = index + cursorX
        }
    }

    private fun moveCursorDown() {
        val (cursorX, cursorY) = calcCursorPos()
        val lines = text.split("\n")
        if (cursorY < lines.size - 1) {
            var index = 0
            for (i in 0 until cursorY + 1) {
                index += lines[i].length + 1
            }
            cursorIndex = index + cursorX
        }
    }

    override fun render(context: DrawContext?, mouseX: Int, mouseY: Int, delta: Float) {
        drawBackground(context, delta, mouseX, mouseY)
        updateCursor(delta)  // Update the cursor's blink state
        drawEditor(context, mouseX, mouseY, delta)
    }

    override fun drawBackground(context: DrawContext?, delta: Float, mouseX: Int, mouseY: Int) {
        // No background rendering logic yet
    }

    private fun updateCursor(deltaTime: Float) {
        cursorLastBlink += deltaTime
        if (cursorLastBlink > cursorBlinkSpeed) {
            cursorVisible = !cursorVisible
            cursorLastBlink = 0.0
        }
    }

    private fun drawEditor(context: DrawContext?, mouseX: Int, mouseY: Int, delta: Float) {
        val lines = text.split("\n")
        val (cursorX, cursorY) = calcCursorPos()

        // Render the lines of text
        for ((i, line) in lines.withIndex()) {
            val y: Int = i * (textRenderer.fontHeight * 1.1).toInt() // Adjust line height spacing
            // Draw the text for the line
            context!!.drawText(textRenderer, line, 0, y, 0xFFFFFF, false)

            // Only draw the cursor if we are on the correct line and the cursor is visible
            if (cursorVisible && i == cursorY) {
                // Get the position where the cursor should be drawn
                val cursorXPosition = textRenderer.getWidth(line.substring(0, cursorX))

                // Draw the cursor (a vertical line) at the calculated position
                context.drawText(textRenderer, "|", cursorXPosition, y, 0xFFFFFF, false)
            }
        }
    }

    override fun keyPressed(keyCode: Int, scanCode: Int, modifiers: Int): Boolean {
        val isShiftPressed = hasShiftDown()

        // Handle other special keys
        when (keyCode) {
            32 -> insert(' ')  // Space
            259 -> {  // Backspace
                delete()
                return true
            }
            257 -> {  // Enter
                enter()
                return true
            }
            263 -> {  // Left arrow
                moveCursorLeft()
                return true
            }
            262 -> {  // Right arrow
                moveCursorRight()
                return true
            }
            265 -> {  // Up arrow
                moveCursorUp()
                return true
            }
            264 -> {  // Down arrow
                moveCursorDown()
                return true
            }
            256 -> {
                client!!.currentScreen!!.close()
                return true
            }
            340 -> return true  // Shift
            344 -> return true  // Ctrl
        }

        var char = keyCode.toChar()

        // Shifted key mapping
        val keyMapping = mapOf(
            '1' to '!',   // 1 -> !
            '2' to '@',   // 2 -> @
            '3' to '#',   // 3 -> #
            '4' to '$',   // 4 -> $
            '5' to '%',   // 5 -> %
            '6' to '^',   // 6 -> ^
            '7' to '&',   // 7 -> &
            '8' to '*',   // 8 -> *
            '9' to '(',   // 9 -> (
            '0' to ')',   // 0 -> )
            '-' to '_',   // - -> _
            '=' to '+',   // = -> +
            '[' to '{',   // [ -> {
            ']' to '}',   // ] -> }
            '\\' to '|',  // \ -> |
            ';' to ':',   // ; -> :
            '\'' to '"',  // ' -> "
            ',' to '<',   // , -> <
            '.' to '>',   // . -> >
            '/' to '?',   // / -> ?
            '`' to '~'    // ` -> ~
        )

        if (isShiftPressed) {
            char = keyMapping[char] ?: char
        }

        val validCharacterNumbersSymbolsRegex = Regex("[0-9a-zA-Z!@#\$%^&*()_+\\-=\\[\\]{};:'\",<.>/?`~\\\\|]")

        // Handle other key presses
        if (validCharacterNumbersSymbolsRegex.matches(char.toString())) {
            insert(char)
        }

        return true
    }
}
