package edu.rit.csci746.alloyplugin

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.tree.IElementType
import edu.rit.csci746.alloyplugin.psi.ALLOY_KEYWORDS
import edu.rit.csci746.alloyplugin.psi.ALLOY_OPERATORS
import edu.rit.csci746.alloyplugin.psi.AlloyTypes
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors as Default

enum class AlloyColor(default: TextAttributesKey) {
    LINE_COMMENT(Default.LINE_COMMENT),
    BRACES(Default.BRACES),
    BRACKETS(Default.BRACKETS),
    PARENTHESIS(Default.PARENTHESES),
    OPERATION_SIGN(Default.OPERATION_SIGN),
    KEYWORD(Default.KEYWORD),
    NUMBER(Default.NUMBER),
    IDENTIFIER(Default.IDENTIFIER);

    val textAttributesKey = TextAttributesKey.createTextAttributesKey(name, default)
}

class AlloySyntaxHighlighter : SyntaxHighlighterBase() {
    companion object {
        fun map(tokenType: IElementType?): AlloyColor? =
            when (tokenType) {
                AlloyTypes.ID -> AlloyColor.IDENTIFIER
                AlloyTypes.COMMENT -> AlloyColor.LINE_COMMENT
                AlloyTypes.LBRACE, AlloyTypes.RBRACE -> AlloyColor.BRACES
                AlloyTypes.LBRACKET, AlloyTypes.RBRACKET -> AlloyColor.BRACKETS
                AlloyTypes.LPAREN, AlloyTypes.RPAREN -> AlloyColor.PARENTHESIS
                AlloyTypes.NUMBER -> AlloyColor.NUMBER
                in ALLOY_OPERATORS -> AlloyColor.OPERATION_SIGN
                in ALLOY_KEYWORDS -> AlloyColor.KEYWORD
                else -> null
            }
    }

    override fun getTokenHighlights(tokenType: IElementType?): Array<TextAttributesKey> =
        pack(map(tokenType)?.textAttributesKey)

    override fun getHighlightingLexer(): Lexer =
        AlloyLexerAdapter()
}

class AlloySyntaxHighlighterFactory: SyntaxHighlighterFactory() {
    override fun getSyntaxHighlighter(project: Project?, virtualFile: VirtualFile?): SyntaxHighlighter =
        AlloySyntaxHighlighter()
}
