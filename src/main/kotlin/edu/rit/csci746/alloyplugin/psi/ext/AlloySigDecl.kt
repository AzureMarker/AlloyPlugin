package edu.rit.csci746.alloyplugin.psi.ext

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import edu.rit.csci746.alloyplugin.psi.AlloyElementFactory
import edu.rit.csci746.alloyplugin.psi.AlloyNameRule
import edu.rit.csci746.alloyplugin.psi.AlloyNamedElementImpl

abstract class AlloySigDeclMixin(node: ASTNode) : AlloyNamedElementImpl(node) {
    override fun getName(): String? = nameIdentifier?.text

    override fun setName(name: String): PsiElement? {
        nameIdentifier?.replace(AlloyElementFactory.createName(project, name))
        return this
    }

    override fun getNameIdentifier(): PsiElement? = PsiTreeUtil.findChildOfType(this, AlloyNameRule::class.java)

    override fun getTextOffset(): Int = nameIdentifier?.textOffset ?: super.getTextOffset()
}
