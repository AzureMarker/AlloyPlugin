package edu.rit.csci746.alloyplugin.runconfig

import com.intellij.execution.lineMarker.ExecutorAction
import com.intellij.execution.lineMarker.RunLineMarkerContributor
import com.intellij.icons.AllIcons
import com.intellij.psi.PsiElement
import com.intellij.util.Function
import edu.rit.csci746.alloyplugin.AlloyUtil

class AlloyCommandRunLineMarkerContributor : RunLineMarkerContributor() {
    override fun getInfo(element: PsiElement): Info? {
        if (!AlloyUtil.isAlloyCommand(element)) return null
        val actions = ExecutorAction.getActions(0)

        return Info(
            AllIcons.RunConfigurations.TestState.Run,
            Function<PsiElement, String> { "Run" },
            *actions
        )
    }
}