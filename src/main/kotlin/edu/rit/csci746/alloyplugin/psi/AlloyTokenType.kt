package edu.rit.csci746.alloyplugin.psi

import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.TokenSet
import edu.rit.csci746.alloyplugin.AlloyLanguage

class AlloyTokenType(debugName: String): IElementType(debugName, AlloyLanguage)

val ALLOY_KEYWORDS = tokenSetOf(
    AlloyTypes.ABSTRACT,
    AlloyTypes.ALL,
    AlloyTypes.ASSERT,
    AlloyTypes.AS,
    AlloyTypes.BUT,
    AlloyTypes.CHECK,
    AlloyTypes.ELSE,
    AlloyTypes.ENUM,
    AlloyTypes.EXACTLY,
    AlloyTypes.EXPECT,
    AlloyTypes.EXTENDS,
    AlloyTypes.FACT,
    AlloyTypes.FOR,
    AlloyTypes.FUN,
    AlloyTypes.IDEN,
    AlloyTypes.SIGINT,
    AlloyTypes.INT,
    AlloyTypes.IN,
    AlloyTypes.LET,
    AlloyTypes.LONE,
    AlloyTypes.MODULE,
    AlloyTypes.NONE,
    AlloyTypes.NO,
    AlloyTypes.ONE,
    AlloyTypes.OPEN,
    AlloyTypes.PRED,
    AlloyTypes.PRIVATE,
    AlloyTypes.RUN,
    AlloyTypes.SEQ,
    AlloyTypes.SET,
    AlloyTypes.SIG,
    AlloyTypes.SOME,
    AlloyTypes.STRING,
    AlloyTypes.SUM,
    AlloyTypes.THIS,
    AlloyTypes.UNIV,
    AlloyTypes.NUM
)

val ALLOY_OPERATORS = tokenSetOf(
    AlloyTypes.HASH,
    AlloyTypes.AMPERSAND,
    AlloyTypes.STAR,
    AlloyTypes.PLUSPLUS,
    AlloyTypes.PLUS,
    AlloyTypes.COMMA,
    AlloyTypes.ARROW,
    AlloyTypes.MINUS,
    AlloyTypes.SLASH,
    AlloyTypes.RANGE,
    AlloyTypes.COLON,
    AlloyTypes.DOMAIN,
    AlloyTypes.SHL,
    AlloyTypes.LT,
    AlloyTypes.EQUALS,
    AlloyTypes.SHR,
    AlloyTypes.SHA,
    AlloyTypes.GTE,
    AlloyTypes.GT,
    AlloyTypes.AT,
    AlloyTypes.CARET,
    AlloyTypes.BAR,
    AlloyTypes.TILDE
)

fun tokenSetOf(vararg tokens: IElementType) = TokenSet.create(*tokens)

