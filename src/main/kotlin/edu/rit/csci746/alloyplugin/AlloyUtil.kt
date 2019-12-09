package edu.rit.csci746.alloyplugin

import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiUtilCore
import edu.rit.csci746.alloyplugin.psi.AlloyTypes

object AlloyUtil {
    fun isAlloyCommand(element: PsiElement): Boolean {
        val elementType = PsiUtilCore.getElementType(element)
        return elementType == AlloyTypes.RUN || elementType == AlloyTypes.CHECK
    }
}
