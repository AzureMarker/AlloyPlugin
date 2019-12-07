package edu.rit.csci746.alloyplugin.psi.ext

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiReference
import edu.rit.csci746.alloyplugin.resolve.AlloyReference

abstract class AlloyNameRuleMixin(node: ASTNode) : ASTWrapperPsiElement(node) {
    override fun getReference(): PsiReference? {
        return AlloyReference(
            this,
            TextRange.from(0, this.textLength)
        )
    }
}