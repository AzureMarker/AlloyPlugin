package edu.rit.csci746.alloyplugin.resolve

import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReferenceBase

class AlloyReference(element: PsiElement, rangeInElement: TextRange?) :
    PsiReferenceBase<PsiElement>(element, rangeInElement) {

    override fun resolve(): PsiElement? {
        return findDeclaration(
            element.project,
            element,
            element.text
        )
    }
}