package edu.rit.csci746.alloyplugin

import edu.mit.csail.sdg.ast.Command
import edu.mit.csail.sdg.parser.CompUtil

object AlloyUtil {
    fun getCommandsFromFile(path: String): List<Command> {
        val world = CompUtil.parseEverything_fromFile(null, null, path)
        return world.allCommands
    }
}
