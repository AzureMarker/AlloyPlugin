package edu.rit.csci746.alloyplugin.runconfig

import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.ConfigurationTypeBase
import com.intellij.execution.configurations.ConfigurationTypeUtil
import com.intellij.execution.configurations.RunConfiguration
import com.intellij.icons.AllIcons
import com.intellij.openapi.project.Project

class AlloyCommandConfigurationType : ConfigurationTypeBase(
    "AlloyExternalTaskConfigType",
    "Alloy",
    "Alloy run configuration",
    AllIcons.General.ExclMark!!
) {
    init {
        addFactory(object : ConfigurationFactory(this) {
            override fun createTemplateConfiguration(project: Project): RunConfiguration =
                AlloyCommandConfiguration(
                    project,
                    this,
                    "Alloy"
                )
        })
    }

    val factory: ConfigurationFactory get() = configurationFactories.single()

    companion object {
        fun getInstance(): AlloyCommandConfigurationType =
            ConfigurationTypeUtil.findConfigurationType(AlloyCommandConfigurationType::class.java)
    }
}
