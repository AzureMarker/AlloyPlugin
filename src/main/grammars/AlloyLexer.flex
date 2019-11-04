package edu.rit.csci746.alloyplugin.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static edu.rit.csci746.alloyplugin.psi.AlloyTypes.*;

%%

%{
  public AlloyLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class AlloyLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

WHITE_SPACE=\s+

Comment = {TraditionalComment} | {EndOfLineComment}
TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment     = "//" .* [^\r\n]*

NOT=\!|not
AND=&&|and
DOT=\.|::
IFF=<=>|iff
LTE=<=|=<
IMPLIES==>|implies
OR=\|\||or
DISJ=disjoint|disj
EXH=exhaustive|exh
PART=partition|part
NUMBER=[0-9]+
ID=[a-zA-Z][a-zA-Z0-9_'\"]*

%%
<YYINITIAL> {
  {WHITE_SPACE}      { return WHITE_SPACE; }
  {Comment}          { return COMMENT; }

  "#"                { return HASH; }
  "&"                { return AMPERSAND; }
  "("                { return LPAREN; }
  ")"                { return RPAREN; }
  "*"                { return STAR; }
  "++"               { return PLUSPLUS; }
  "+"                { return PLUS; }
  ","                { return COMMA; }
  "->"               { return ARROW; }
  "-"                { return MINUS; }
  "/"                { return SLASH; }
  ":>"               { return RANGE; }
  ":"                { return COLON; }
  "<:"               { return DOMAIN; }
  "<<"               { return SHL; }
  "<"                { return LT; }
  "="                { return EQUALS; }
  ">>>"              { return SHR; }
  ">>"               { return SHA; }
  ">="               { return GTE; }
  ">"                { return GT; }
  "@"                { return AT; }
  "["                { return LBRACKET; }
  "]"                { return RBRACKET; }
  "^"                { return CARET; }
  "{"                { return LBRACE; }
  "|"                { return BAR; }
  "}"                { return RBRACE; }
  "~"                { return TILDE; }
  "abstract"         { return ABSTRACT; }
  "all"              { return ALL; }
  "assert"           { return ASSERT; }
  "as"               { return AS; }
  "but"              { return BUT; }
  "check"            { return CHECK; }
  "else"             { return ELSE; }
  "enum"             { return ENUM; }
  "exactly"          { return EXACTLY; }
  "expect"           { return EXPECT; }
  "extends"          { return EXTENDS; }
  "fact"             { return FACT; }
  "for"              { return FOR; }
  "fun"              { return FUN; }
  "iden"             { return IDEN; }
  "Int"              { return SIGINT; }
  "int"              { return INT; }
  "in"               { return IN; }
  "let"              { return LET; }
  "lone"             { return LONE; }
  "module"           { return MODULE; }
  "none"             { return NONE; }
  "no"               { return NO; }
  "one"              { return ONE; }
  "open"             { return OPEN; }
  "pred"             { return PRED; }
  "private"          { return PRIVATE; }
  "run"              { return RUN; }
  "seq"              { return SEQ; }
  "set"              { return SET; }
  "sig"              { return SIG; }
  "some"             { return SOME; }
  "String"           { return STRING; }
  "sum"              { return SUM; }
  "this"             { return THIS; }
  "univ"             { return UNIV; }
  "num"              { return NUM; }
  "number"           { return NUMBER; }

  {NOT}              { return NOT; }
  {AND}              { return AND; }
  {DOT}              { return DOT; }
  {IFF}              { return IFF; }
  {LTE}              { return LTE; }
  {IMPLIES}          { return IMPLIES; }
  {OR}               { return OR; }
  {DISJ}             { return DISJ; }
  {EXH}              { return EXH; }
  {PART}             { return PART; }
  {NUMBER}           { return NUMBER; }
  {ID}               { return ID; }
}

[^] { return BAD_CHARACTER; }
