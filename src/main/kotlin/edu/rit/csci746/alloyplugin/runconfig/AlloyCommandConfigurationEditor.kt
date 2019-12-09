package edu.rit.csci746.alloyplugin.runconfig

import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.options.SettingsEditor
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.ComboBox
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.intellij.ui.components.Label
import com.intellij.ui.layout.LayoutBuilder
import com.intellij.ui.layout.Row
import com.intellij.ui.layout.panel
import edu.mit.csail.sdg.parser.CompUtil
import edu.rit.csci746.alloyplugin.AlloyFileType
import javax.swing.JComponent

class AlloyCommandConfigurationEditor(private val project: Project) : SettingsEditor<AlloyCommandConfiguration>() {
    private val fileBrowser =
        TextFieldWithBrowseButton { updateComboBox() }.also {
            val fileChooser =
                FileChooserDescriptorFactory.createSingleFileNoJarsDescriptor().also { fileChooserDescriptor ->
                    fileChooserDescriptor.withFileFilter { file -> file.fileType.name == AlloyFileType.name }
                }

            it.addBrowseFolderListener(null, null, project, fileChooser)
        }

    private val commandSelection = ComboBox<String>()

    override fun createEditor(): JComponent = panel {
        labeledRow("File:", fileBrowser) {
            fileBrowser()
        }

        labeledRow("Command:", commandSelection) {
            commandSelection()
        }
    }

    private fun updateComboBox() {
        val path = fileBrowser.text
        if (path.isEmpty()) {
            return
        }

        commandSelection.removeAllItems()

        val world = CompUtil.parseEverything_fromFile(null, null, path)
        val commands = world.allCommands

        commands
            .map { command -> command.toString() }
            .forEach { commandSelection.addItem(it) }
    }

    override fun applyEditorTo(s: AlloyCommandConfiguration) {
        val commandName = commandSelection.selectedItem as? String ?: return

        s.applyParams(
            AlloyCommandConfiguration.RunParams(
                filePath = fileBrowser.text,
                commandName = commandName
            )
        )
    }

    override fun resetEditorFrom(s: AlloyCommandConfiguration) {
        s.runParams.let {
            fileBrowser.text = it.filePath
            updateComboBox()
            commandSelection.selectedItem = it.commandName
        }
    }

    private fun LayoutBuilder.labeledRow(labelText: String, component: JComponent, init: Row.() -> Unit) {
        val label = Label(labelText)
        label.labelFor = component
        row(label) { init() }
    }
}
