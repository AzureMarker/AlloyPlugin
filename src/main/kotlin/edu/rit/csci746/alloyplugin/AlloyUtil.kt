package edu.rit.csci746.alloyplugin

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiManager
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.PsiTreeUtil
import edu.rit.csci746.alloyplugin.psi.AlloyFile
import edu.rit.csci746.alloyplugin.psi.AlloySigDecl

object AlloyUtil {
    fun findSigDeclaration(project: Project, name: String): AlloySigDecl? {
        return findSigDeclarations(project).filter { it.name == name }.firstOrNull()
    }

    fun findSigDeclarations(project: Project): List<AlloySigDecl> {
        val virtualFiles = FileTypeIndex.getFiles(AlloyFileType, GlobalSearchScope.allScope(project));
        val psiManager = PsiManager.getInstance(project)

        return virtualFiles
            .mapNotNull { psiManager.findFile(it) as AlloyFile? }
            .flatMap { PsiTreeUtil.findChildrenOfType(it, AlloySigDecl::class.java) }
    }
}