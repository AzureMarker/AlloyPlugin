package edu.rit.csci746.alloyplugin

import com.intellij.icons.AllIcons
import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

class AlloyFileType : LanguageFileType(AlloyLanguage) {
    override fun getIcon(): Icon? = AllIcons.FileTypes.Custom

    override fun getName(): String = language.displayName

    override fun getDefaultExtension(): String = "als"

    override fun getDescription(): String = language.displayName
}