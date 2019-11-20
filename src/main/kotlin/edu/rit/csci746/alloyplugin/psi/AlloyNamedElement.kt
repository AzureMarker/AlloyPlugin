package edu.rit.csci746.alloyplugin.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiNameIdentifierOwner

interface AlloyNamedElement: PsiNameIdentifierOwner

abstract class AlloyNamedElementImpl(node: ASTNode) : ASTWrapperPsiElement(node), AlloyNamedElement