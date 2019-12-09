package edu.rit.csci746.alloyplugin.runconfig

import com.intellij.execution.actions.ConfigurationContext
import com.intellij.execution.actions.LazyRunConfigurationProducer
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Ref
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiElement
import edu.mit.csail.sdg.parser.CompUtil
import edu.rit.csci746.alloyplugin.AlloyUtil

class AlloyCommandRunConfigurationProducer : LazyRunConfigurationProducer<AlloyCommandConfiguration>() {
    private fun runParamsFromContext(
        context: ConfigurationContext
    ): AlloyCommandConfiguration.RunParams? {
        val location = context.location ?: return null
        if (!AlloyUtil.isAlloyCommand(location.psiElement)) return null
        val path = location.virtualFile?.path ?: return null

        val world = CompUtil.parseEverything_fromFile(
            null,
            null,
            path
        )

        val commands = world.allCommands

        val psiLocation = context.psiLocation ?: return null
        val lineNumber = lineNumberForElement(psiLocation, context.project) ?: return null
        val commandAtPosition = commands.find { command -> command.pos.y == lineNumber + 1 } ?: return null

        return AlloyCommandConfiguration.RunParams(
            filePath = path,
            commandName = commandAtPosition.toString()
        )
    }

    override fun getConfigurationFactory(): ConfigurationFactory =
        AlloyCommandConfigurationType.getInstance().factory

    override fun setupConfigurationFromContext(
        configuration: AlloyCommandConfiguration,
        context: ConfigurationContext,
        sourceElement: Ref<PsiElement>
    ): Boolean {
        configuration.initializeWithParams(
            runParamsFromContext(
                context
            ) ?: return false
        )

        return true
    }

    override fun isConfigurationFromContext(
        configuration: AlloyCommandConfiguration,
        context: ConfigurationContext
    ): Boolean {
        val fromContext = runParamsFromContext(context)
        return fromContext == configuration.runParams
    }
}

private fun lineNumberForElement(
    psiElement: PsiElement,
    project: Project
): Int? {
    val documentManager = PsiDocumentManager.getInstance(project) ?: return null
    val document = documentManager.getDocument(psiElement.containingFile) ?: return null
    return document.getLineNumber(psiElement.textOffset)
}
