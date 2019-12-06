package edu.rit.csci746.alloyplugin

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiManager
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.PsiTreeUtil
import edu.rit.csci746.alloyplugin.psi.*

object AlloyUtil {
    fun findDeclaration(project: Project, name: String): AlloyNamedElement? {
        return findDeclarations(project).firstOrNull { it.name == name }
    }

    fun findDeclarations(project: Project): List<AlloyNamedElement> {
        val virtualFiles = FileTypeIndex.getFiles(AlloyFileType, GlobalSearchScope.allScope(project));
        val psiManager = PsiManager.getInstance(project)

        return virtualFiles
            .mapNotNull { psiManager.findFile(it) as AlloyFile? }
            .flatMap {
                val sigs = PsiTreeUtil.findChildrenOfType(it, AlloySigDecl::class.java) as Collection<AlloyNamedElement>
                val facts = PsiTreeUtil.findChildrenOfType(it, AlloyFactDecl::class.java) as Collection<AlloyNamedElement>
                val asserts = PsiTreeUtil.findChildrenOfType(it, AlloyAssertDecl::class.java) as Collection<AlloyNamedElement>
                val functions = PsiTreeUtil.findChildrenOfType(it, AlloyFunDecl::class.java) as Collection<AlloyNamedElement>
                val commands = PsiTreeUtil.findChildrenOfType(it, AlloyCmdDecl::class.java) as Collection<AlloyNamedElement>
                val enums = PsiTreeUtil.findChildrenOfType(it, AlloyEnumDecl::class.java) as Collection<AlloyNamedElement>
                val decls = PsiTreeUtil.findChildrenOfType(it, AlloyDecl::class.java) as Collection<AlloyNamedElement>

                sigs + facts + asserts + functions + commands + enums + decls
            }
    }
}