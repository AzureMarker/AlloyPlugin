package edu.rit.csci746.alloyplugin.psi.ext

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.ResolveState
import com.intellij.psi.scope.PsiScopeProcessor
import edu.rit.csci746.alloyplugin.psi.AlloyTerminalExpr

abstract class AlloyTerminalExprMixin(node: ASTNode) : ASTWrapperPsiElement(node), AlloyTerminalExpr {
    override fun processDeclarations(
        processor: PsiScopeProcessor,
        state: ResolveState,
        lastParent: PsiElement?,
        place: PsiElement
    ): Boolean {
        return declList.find { !processor.execute(it, state) } == null
    }
}