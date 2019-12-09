package edu.rit.csci746.alloyplugin.structure

import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.structureView.StructureViewModel
import com.intellij.ide.structureView.StructureViewModelBase
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.TreeElement
import com.intellij.navigation.ItemPresentation
import com.intellij.openapi.editor.Editor
import com.intellij.pom.Navigatable
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil
import edu.rit.csci746.alloyplugin.psi.AlloyFactDecl
import edu.rit.csci746.alloyplugin.psi.AlloyFile
import edu.rit.csci746.alloyplugin.psi.AlloySigDecl

class AlloyStructureViewModel(file: PsiFile, editor: Editor?) :
    StructureViewModelBase(file, editor, AlloyStructureViewElement(file)),
    StructureViewModel.ElementInfoProvider {

    override fun isAlwaysShowsPlus(element: StructureViewTreeElement): Boolean =
        element.value is AlloyFile

    override fun isAlwaysLeaf(element: StructureViewTreeElement): Boolean =
        when (element.value) {
            is AlloySigDecl,
            is AlloyFactDecl -> true
            else -> false
        }
}

class AlloyStructureViewElement(val psi: PsiElement) : StructureViewTreeElement,
    Navigatable by (psi as NavigatablePsiElement) {
    override fun getValue(): Any =
        psi

    override fun getPresentation(): ItemPresentation =
        getPresentationForStructure(psi)

    override fun getChildren(): Array<out TreeElement> =
        childElements.map(::AlloyStructureViewElement).toTypedArray()

    private val childElements: List<PsiElement>
        get() = when (psi) {
            is PsiFile ->
                PsiTreeUtil.findChildrenOfType(psi, AlloySigDecl::class.java)
                    .toList()
            else -> emptyList()
        }

    private fun getPresentationForStructure(psi: PsiElement): ItemPresentation {
        if (psi is PsiFile) {
            val presentation = psi.presentation
            if (presentation != null) {
                return presentation
            }
        }

        val icon = psi.getIcon(0)
        val presentableText = buildString {
            when (psi) {
                is AlloySigDecl -> append("sig ${psi.name}")
                is AlloyFactDecl -> append("fact ${psi.name}")
            }
        }

        return PresentationData(
            presentableText,
            null,
            icon,
            null
        )
    }
}
