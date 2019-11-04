package edu.rit.csci746.alloyplugin.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import edu.rit.csci746.alloyplugin.AlloyFileType
import edu.rit.csci746.alloyplugin.AlloyLanguage

class AlloyFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, AlloyLanguage) {
    override fun getFileType(): FileType = AlloyFileType

    override fun toString(): String = "Alloy File"
}