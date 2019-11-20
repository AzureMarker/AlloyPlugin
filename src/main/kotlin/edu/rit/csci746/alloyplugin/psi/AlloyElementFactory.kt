package edu.rit.csci746.alloyplugin.psi

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFileFactory
import edu.rit.csci746.alloyplugin.AlloyFileType

object AlloyElementFactory {
    fun createName(project: Project, name: String): AlloyNameRule {
        return createFile(project, name).firstChild as AlloyNameRule
    }

    private fun createFile(project: Project, text: String): AlloyFile {
        return PsiFileFactory.getInstance(project).createFileFromText(
            "DUMMY.als",
            AlloyFileType,
            text
        ) as AlloyFile
    }
}