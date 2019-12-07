package edu.rit.csci746.alloyplugin

import com.intellij.execution.Executor
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.RunConfiguration
import com.intellij.execution.configurations.RunConfigurationBase
import com.intellij.execution.configurations.RunProfileState
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.options.SettingsEditor
import com.intellij.openapi.project.Project
import edu.mit.csail.sdg.alloy4viz.VizGUI
import edu.mit.csail.sdg.parser.CompUtil
import edu.mit.csail.sdg.translator.A4Options
import edu.mit.csail.sdg.translator.TranslateAlloyToKodkod
import org.jdom.Element

class AlloyCommandConfiguration(
    project: Project,
    factory: ConfigurationFactory?,
    name: String
) : RunConfigurationBase<Any>(project, factory, name) {
    var runParams: RunParams? = null

    override fun getConfigurationEditor(): SettingsEditor<out RunConfiguration> =
        AlloyCommandConfigurationEditor(project)

    override fun getState(executor: Executor, environment: ExecutionEnvironment): RunProfileState? =
        RunProfileState { _, _ ->
            val runParams = runParams ?: return@RunProfileState null
            val world = CompUtil.parseEverything_fromFile(
                null,
                null,
                runParams.filePath
            )

            val command =
                world.allCommands.find {
                    it.toString() == runParams.commandName
                } ?: return@RunProfileState null

            val solution = TranslateAlloyToKodkod.execute_command(
                null,
                world.allReachableSigs,
                command,
                A4Options()
            )

            if (!solution.satisfiable()) {
                return@RunProfileState null
            }

            val tempFile = createTempFile(suffix = ".xml").path
            solution.writeXML(tempFile)

            VizGUI(false, tempFile, null)

            null
        }

    override fun writeExternal(element: Element) {
        super.writeExternal(element)
        element.writeString("path", runParams?.filePath)
        element.writeString("command", runParams?.commandName)
    }

    override fun readExternal(element: Element) {
        super.readExternal(element)
        runParams = RunParams(
            filePath = element.readString("path") ?: return,
            commandName = element.readString("command") ?: return
        )
    }

    data class RunParams(
        val filePath: String,
        val commandName: String
    )
}

private fun Element.writeString(name: String, value: String?) {
    val opt = Element("option")
    opt.setAttribute("name", name)
    opt.setAttribute("value", value)
    addContent(opt)
}

private fun Element.readString(name: String): String? =
    children
        .find { it.name == "option" && it.getAttributeValue("name") == name }
        ?.getAttributeValue("value")

