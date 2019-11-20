package edu.rit.csci746.alloyplugin.psi.ext

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import edu.rit.csci746.alloyplugin.psi.AlloyElementFactory
import edu.rit.csci746.alloyplugin.psi.AlloyNameRule
import edu.rit.csci746.alloyplugin.psi.AlloyNamedElementImpl

abstract class AlloySigDeclMixin(node: ASTNode) : AlloyNamedElementImpl(node) {
    override fun getName(): String? {
        return nameIdentifier?.text?.replace("\\\\ ", "")
    }

    override fun setName(name: String): PsiElement {
        val nameElement = nameIdentifier ?: return this
        val newNameNode = AlloyElementFactory.createName(project, name).firstChild.node

        nameElement.parent.node.replaceChild(nameElement.node, newNameNode)
        return this
    }

    override fun getNameIdentifier(): PsiElement? {
        return PsiTreeUtil.findChildOfType(this, AlloyNameRule::class.java)
    }
}
