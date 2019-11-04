package edu.rit.csci746.alloyplugin

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import edu.rit.csci746.alloyplugin.parser.AlloyParser
import edu.rit.csci746.alloyplugin.psi.AlloyFile
import edu.rit.csci746.alloyplugin.psi.AlloyTypes

object AlloyParserDefinition: ParserDefinition {
    private val COMMENTS = TokenSet.create(AlloyTypes.COMMENT)
    private val FILE = IFileElementType(AlloyLanguage)

    override fun createLexer(project: Project?): Lexer = AlloyLexerAdapter()

    override fun createParser(project: Project?): PsiParser = AlloyParser()

    override fun createFile(viewProvider: FileViewProvider): PsiFile {
        return AlloyFile(viewProvider)
    }

    override fun getStringLiteralElements(): TokenSet = TokenSet.EMPTY

    override fun getFileNodeType(): IFileElementType = FILE

    override fun createElement(node: ASTNode?): PsiElement {
        return AlloyTypes.Factory.createElement(node)
    }

    override fun getCommentTokens(): TokenSet = COMMENTS
}