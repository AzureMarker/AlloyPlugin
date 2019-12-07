package edu.rit.csci746.alloyplugin.psi.ext

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.ResolveState
import com.intellij.psi.scope.PsiScopeProcessor
import edu.rit.csci746.alloyplugin.psi.AlloyDecl

interface AlloyDeclList {
    val declList: List<AlloyDecl>
}

abstract class AlloyNamedDeclListMixin(node: ASTNode) : AlloyNamedElementMixin(node), AlloyDeclList {
    override fun processDeclarations(
        processor: PsiScopeProcessor,
        state: ResolveState,
        lastParent: PsiElement?,
        place: PsiElement
    ): Boolean {
        return declList.find { !processor.execute(it, state) } == null
    }
}