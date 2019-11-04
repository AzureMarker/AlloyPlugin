package edu.rit.csci746.alloyplugin

import com.intellij.lexer.FlexAdapter
import edu.rit.csci746.alloyplugin.lexer.AlloyLexer

class AlloyLexerAdapter: FlexAdapter(AlloyLexer(null))