package edu.rit.csci746.alloyplugin

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages

class HelloAction: AnAction("Hello") {
    override fun actionPerformed(e: AnActionEvent) {
        Messages.showMessageDialog(e.project, "Hello World!", "Greetings", Messages.getInformationIcon())
    }
}