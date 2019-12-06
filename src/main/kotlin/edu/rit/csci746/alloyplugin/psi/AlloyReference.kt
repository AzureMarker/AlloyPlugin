package edu.rit.csci746.alloyplugin.psi

import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReferenceBase
import edu.rit.csci746.alloyplugin.AlloyUtil

class AlloyReference(element: PsiElement, rangeInElement: TextRange?) :
    PsiReferenceBase<PsiElement>(element, rangeInElement) {

    override fun resolve(): PsiElement? {
        return AlloyUtil.findDeclaration(
            element.project,
            element.text
        )
    }
}