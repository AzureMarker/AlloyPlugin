package edu.rit.csci746.alloyplugin

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiManager
import com.intellij.psi.ResolveState
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.PsiTreeUtil
import edu.rit.csci746.alloyplugin.psi.*

object AlloyUtil {
    fun findDeclaration(project: Project, referenceElement: PsiElement, name: String): AlloyNamedElement? {
        return findDeclarations(project, referenceElement).firstOrNull { it.name == name }
    }

    fun findDeclarations(project: Project, referenceElement: PsiElement): List<AlloyNamedElement> {
        val virtualFiles = FileTypeIndex.getFiles(AlloyFileType, GlobalSearchScope.allScope(project));
        val psiManager = PsiManager.getInstance(project)

        return virtualFiles
            .mapNotNull { psiManager.findFile(it) as AlloyFile? }
            .flatMap { file ->
                val decls = mutableListOf<AlloyNamedElement>()

                PsiTreeUtil.treeWalkUp(
                    { psiElement: PsiElement, _: ResolveState ->
                        if (psiElement is AlloyDecl) decls += psiElement

                        true
                    },
                    referenceElement,
                    file,
                    ResolveState()
                )

                val sigs = PsiTreeUtil.findChildrenOfType(file, AlloySigDecl::class.java)
                val facts = PsiTreeUtil.findChildrenOfType(file, AlloyFactDecl::class.java)
                val asserts = PsiTreeUtil.findChildrenOfType(file, AlloyAssertDecl::class.java)
                val functions = PsiTreeUtil.findChildrenOfType(file, AlloyFunDecl::class.java)
                val commands = PsiTreeUtil.findChildrenOfType(file, AlloyCmdDecl::class.java)
                val enums = PsiTreeUtil.findChildrenOfType(file, AlloyEnumDecl::class.java)

                decls + sigs + facts + asserts + functions + commands + enums
            }
    }
}
