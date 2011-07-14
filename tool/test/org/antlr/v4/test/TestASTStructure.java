package org.antlr.v4.test;

import org.antlr.runtime.RuleReturnScope;
import org.antlr.runtime.tree.Tree;
import org.junit.*;

import static org.junit.Assert.assertEquals;

public class TestASTStructure extends org.antlr.v4.gunit.gUnitBase {
	@Before public void setup() {
	    lexerClassName = "org.antlr.v4.parse.ANTLRLexer";
	    parserClassName = "org.antlr.v4.parse.ANTLRParser";
	    adaptorClassName = "org.antlr.v4.parse.GrammarASTAdaptor";	}
	@Test public void test_grammarSpec1() throws Exception {
		// gunit test on line 15
		RuleReturnScope rstruct = (RuleReturnScope)execParser("grammarSpec", "parser grammar P; a : A;", 15);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(PARSER_GRAMMAR P (RULES (RULE a (BLOCK (ALT A)))))";
		assertEquals("testing rule grammarSpec", expecting, actual);
	}

	@Test public void test_grammarSpec2() throws Exception {
		// gunit test on line 18
		RuleReturnScope rstruct = (RuleReturnScope)execParser("grammarSpec", "\n    parser grammar P;\n    options {k=2; output=AST;}\n    scope S {int x}\n    tokens { A; B='33'; }\n    @header {foo}\n    a : A;\n    ", 18);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(PARSER_GRAMMAR P (OPTIONS (= k 2) (= output AST)) (scope S {int x}) (tokens { A (= B '33')) (@ header {foo}) (RULES (RULE a (BLOCK (ALT A)))))";
		assertEquals("testing rule grammarSpec", expecting, actual);
	}

	@Test public void test_grammarSpec3() throws Exception {
		// gunit test on line 34
		RuleReturnScope rstruct = (RuleReturnScope)execParser("grammarSpec", "\n    parser grammar P;\n    @header {foo}\n    tokens { A; B='33'; }\n    options {k=2; ASTLabel=a.b.c; output=AST;}\n    scope S {int x}\n    a : A;\n    ", 34);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(PARSER_GRAMMAR P (@ header {foo}) (tokens { A (= B '33')) (OPTIONS (= k 2) (= ASTLabel a.b.c) (= output AST)) (scope S {int x}) (RULES (RULE a (BLOCK (ALT A)))))";
		assertEquals("testing rule grammarSpec", expecting, actual);
	}

	@Test public void test_grammarSpec4() throws Exception {
		// gunit test on line 50
		RuleReturnScope rstruct = (RuleReturnScope)execParser("grammarSpec", "\n    parser grammar P;\n    import A=B, C;\n    a : A;\n    ", 50);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(PARSER_GRAMMAR P (import (= A B) C) (RULES (RULE a (BLOCK (ALT A)))))";
		assertEquals("testing rule grammarSpec", expecting, actual);
	} @Test public void test_delegateGrammars1() throws Exception {
		// gunit test on line 61
		RuleReturnScope rstruct = (RuleReturnScope)execParser("delegateGrammars", "import A;", 61);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(import A)";
		assertEquals("testing rule delegateGrammars", expecting, actual);
	} @Test public void test_rule1() throws Exception {
		// gunit test on line 64
		RuleReturnScope rstruct = (RuleReturnScope)execParser("rule", "a : A<X,Y=a.b.c>;", 64);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(RULE a (BLOCK (ALT (A (ELEMENT_OPTIONS X (= Y a.b.c))))))";
		assertEquals("testing rule rule", expecting, actual);
	}

	@Test public void test_rule2() throws Exception {
		// gunit test on line 66
		RuleReturnScope rstruct = (RuleReturnScope)execParser("rule", "A : B+;", 66);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(RULE A (BLOCK (ALT (+ (BLOCK (ALT B))))))";
		assertEquals("testing rule rule", expecting, actual);
	}

	@Test public void test_rule3() throws Exception {
		// gunit test on line 68
		RuleReturnScope rstruct = (RuleReturnScope)execParser("rule", "\n    public a[int i] returns [int y]\n    options {backtrack=true;}\n    scope {int ss;}\n    scope S,T;\n    @init {blort}\n      : ID ;\n    ", 68);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(RULE a (RULEMODIFIERS public) int i (returns int y) (OPTIONS (= backtrack true)) (scope {int ss;}) (scope S T) (@ init {blort}) (BLOCK (ALT ID)))";
		assertEquals("testing rule rule", expecting, actual);
	}

	@Test public void test_rule4() throws Exception {
		// gunit test on line 87
		RuleReturnScope rstruct = (RuleReturnScope)execParser("rule", "\n    a[int i] returns [int y]\n    @init {blort}\n    scope {int ss;}\n    options {backtrack=true;}\n    scope S,T;\n      : ID;\n    ", 87);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(RULE a int i (returns int y) (@ init {blort}) (scope {int ss;}) (OPTIONS (= backtrack true)) (scope S T) (BLOCK (ALT ID)))";
		assertEquals("testing rule rule", expecting, actual);
	}

	@Test public void test_rule5() throws Exception {
		// gunit test on line 104
		RuleReturnScope rstruct = (RuleReturnScope)execParser("rule", "\n    a : ID ;\n      catch[A b] {foo}\n      finally {bar}\n    ", 104);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(RULE a (BLOCK (ALT ID)) (catch A b {foo}) (finally {bar}))";
		assertEquals("testing rule rule", expecting, actual);
	}

	@Test public void test_rule6() throws Exception {
		// gunit test on line 113
		RuleReturnScope rstruct = (RuleReturnScope)execParser("rule", "\n    a : ID ;\n      catch[A a] {foo}\n      catch[B b] {fu}\n      finally {bar}\n    ", 113);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(RULE a (BLOCK (ALT ID)) (catch A a {foo}) (catch B b {fu}) (finally {bar}))";
		assertEquals("testing rule rule", expecting, actual);
	} @Test public void test_block1() throws Exception {
		// gunit test on line 124
		RuleReturnScope rstruct = (RuleReturnScope)execParser("block", "( ^(A B) | ^(b C) )", 124);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(BLOCK (ALT (^( A B)) (ALT (^( b C)))";
		assertEquals("testing rule block", expecting, actual);
	} @Test public void test_ebnf1() throws Exception {
		// gunit test on line 127
		RuleReturnScope rstruct = (RuleReturnScope)execParser("ebnf", "(A|B)", 127);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(SET A B)";
		assertEquals("testing rule ebnf", expecting, actual);
	}

	@Test public void test_ebnf2() throws Exception {
		// gunit test on line 128
		RuleReturnScope rstruct = (RuleReturnScope)execParser("ebnf", "(A|B)?", 128);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(? (BLOCK (ALT (SET A B))))";
		assertEquals("testing rule ebnf", expecting, actual);
	}

	@Test public void test_ebnf3() throws Exception {
		// gunit test on line 129
		RuleReturnScope rstruct = (RuleReturnScope)execParser("ebnf", "(A|B)*", 129);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(* (BLOCK (ALT (SET A B))))";
		assertEquals("testing rule ebnf", expecting, actual);
	}

	@Test public void test_ebnf4() throws Exception {
		// gunit test on line 130
		RuleReturnScope rstruct = (RuleReturnScope)execParser("ebnf", "(A|B)+", 130);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(+ (BLOCK (ALT (SET A B))))";
		assertEquals("testing rule ebnf", expecting, actual);
	} @Test public void test_alternative1() throws Exception {
		// gunit test on line 133
		RuleReturnScope rstruct = (RuleReturnScope)execParser("alternative", "x+=ID* -> $x*", 133);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(ALT_REWRITE (ALT (* (BLOCK (ALT (+= x ID))))) (-> (ALT (* (REWRITE_BLOCK (ALT x))))))";
		assertEquals("testing rule alternative", expecting, actual);
	}

	@Test public void test_alternative2() throws Exception {
		// gunit test on line 138
		RuleReturnScope rstruct = (RuleReturnScope)execParser("alternative", "A -> ...", 138);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(ALT_REWRITE (ALT A) (-> ...))";
		assertEquals("testing rule alternative", expecting, actual);
	}

	@Test public void test_alternative3() throws Exception {
		// gunit test on line 139
		RuleReturnScope rstruct = (RuleReturnScope)execParser("alternative", "A -> ", 139);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(ALT_REWRITE (ALT A) (-> EPSILON))";
		assertEquals("testing rule alternative", expecting, actual);
	}

	@Test public void test_alternative4() throws Exception {
		// gunit test on line 141
		RuleReturnScope rstruct = (RuleReturnScope)execParser("alternative", "A -> foo(a={x}, b={y})", 141);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(ALT_REWRITE (ALT A) (-> (TEMPLATE foo (ARGLIST (= a {x}) (= b {y})))))";
		assertEquals("testing rule alternative", expecting, actual);
	}

	@Test public void test_alternative5() throws Exception {
		// gunit test on line 146
		RuleReturnScope rstruct = (RuleReturnScope)execParser("alternative", "A -> template(a={x}, b={y}) <<ick>>", 146);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(ALT_REWRITE (ALT A) (-> (TEMPLATE (ARGLIST (= a {x}) (= b {y})) <<ick>>)))";
		assertEquals("testing rule alternative", expecting, actual);
	}

	@Test public void test_alternative6() throws Exception {
		// gunit test on line 151
		RuleReturnScope rstruct = (RuleReturnScope)execParser("alternative", "A -> ({name})()", 151);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(ALT_REWRITE (ALT A) (-> (TEMPLATE {name})))";
		assertEquals("testing rule alternative", expecting, actual);
	}

	@Test public void test_alternative7() throws Exception {
		// gunit test on line 153
		RuleReturnScope rstruct = (RuleReturnScope)execParser("alternative", "A -> {expr}", 153);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(ALT_REWRITE (ALT A) (-> (ALT {expr})))";
		assertEquals("testing rule alternative", expecting, actual);
	}

	@Test public void test_alternative8() throws Exception {
		// gunit test on line 155
		RuleReturnScope rstruct = (RuleReturnScope)execParser("alternative", "\n    A -> {p1}? {e1}\n    -> {e2}\n    ->\n    ", 155);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(ALT_REWRITE (ALT A) (-> {p1}? (ALT {e1})) (-> (ALT {e2})))";
		assertEquals("testing rule alternative", expecting, actual);
	}

	@Test public void test_alternative9() throws Exception {
		// gunit test on line 167
		RuleReturnScope rstruct = (RuleReturnScope)execParser("alternative", "A -> A", 167);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(ALT_REWRITE (ALT A) (-> (ALT A)))";
		assertEquals("testing rule alternative", expecting, actual);
	}

	@Test public void test_alternative10() throws Exception {
		// gunit test on line 169
		RuleReturnScope rstruct = (RuleReturnScope)execParser("alternative", "a -> a", 169);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(ALT_REWRITE (ALT a) (-> (ALT a)))";
		assertEquals("testing rule alternative", expecting, actual);
	}

	@Test public void test_alternative11() throws Exception {
		// gunit test on line 171
		RuleReturnScope rstruct = (RuleReturnScope)execParser("alternative", "a A X? Y* -> A a ^(TOP X)? Y*", 171);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(ALT_REWRITE (ALT a A (? (BLOCK (ALT X))) (* (BLOCK (ALT Y)))) (-> (ALT A a (? (REWRITE_BLOCK (ALT (^( TOP X)))) (* (REWRITE_BLOCK (ALT Y))))))";
		assertEquals("testing rule alternative", expecting, actual);
	}

	@Test public void test_alternative12() throws Exception {
		// gunit test on line 179
		RuleReturnScope rstruct = (RuleReturnScope)execParser("alternative", "A -> A[33]", 179);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(ALT_REWRITE (ALT A) (-> (ALT (A 33))))";
		assertEquals("testing rule alternative", expecting, actual);
	}

	@Test public void test_alternative13() throws Exception {
		// gunit test on line 181
		RuleReturnScope rstruct = (RuleReturnScope)execParser("alternative", "A -> 'int' ^(A A)*", 181);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(ALT_REWRITE (ALT A) (-> (ALT 'int' (* (REWRITE_BLOCK (ALT (^( A A)))))))";
		assertEquals("testing rule alternative", expecting, actual);
	}

	@Test public void test_alternative14() throws Exception {
		// gunit test on line 186
		RuleReturnScope rstruct = (RuleReturnScope)execParser("alternative", "\n    A -> {p1}? A\n      -> {p2}? B\n      ->\n    ", 186);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(ALT_REWRITE (ALT A) (-> {p1}? (ALT A)) (-> {p2}? (ALT B)) (-> EPSILON))";
		assertEquals("testing rule alternative", expecting, actual);
	} @Test public void test_element1() throws Exception {
		// gunit test on line 198
		RuleReturnScope rstruct = (RuleReturnScope)execParser("element", "~A", 198);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(~ (SET A))";
		assertEquals("testing rule element", expecting, actual);
	}

	@Test public void test_element2() throws Exception {
		// gunit test on line 199
		RuleReturnScope rstruct = (RuleReturnScope)execParser("element", "b+", 199);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(+ (BLOCK (ALT b)))";
		assertEquals("testing rule element", expecting, actual);
	}

	@Test public void test_element3() throws Exception {
		// gunit test on line 200
		RuleReturnScope rstruct = (RuleReturnScope)execParser("element", "(b)+", 200);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(+ (BLOCK (ALT b)))";
		assertEquals("testing rule element", expecting, actual);
	}

	@Test public void test_element4() throws Exception {
		// gunit test on line 201
		RuleReturnScope rstruct = (RuleReturnScope)execParser("element", "b?", 201);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(? (BLOCK (ALT b)))";
		assertEquals("testing rule element", expecting, actual);
	}

	@Test public void test_element5() throws Exception {
		// gunit test on line 202
		RuleReturnScope rstruct = (RuleReturnScope)execParser("element", "(b)?", 202);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(? (BLOCK (ALT b)))";
		assertEquals("testing rule element", expecting, actual);
	}

	@Test public void test_element6() throws Exception {
		// gunit test on line 203
		RuleReturnScope rstruct = (RuleReturnScope)execParser("element", "(b)*", 203);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(* (BLOCK (ALT b)))";
		assertEquals("testing rule element", expecting, actual);
	}

	@Test public void test_element7() throws Exception {
		// gunit test on line 204
		RuleReturnScope rstruct = (RuleReturnScope)execParser("element", "b*", 204);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(* (BLOCK (ALT b)))";
		assertEquals("testing rule element", expecting, actual);
	}

	@Test public void test_element8() throws Exception {
		// gunit test on line 205
		RuleReturnScope rstruct = (RuleReturnScope)execParser("element", "'while'*", 205);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(* (BLOCK (ALT 'while')))";
		assertEquals("testing rule element", expecting, actual);
	}

	@Test public void test_element9() throws Exception {
		// gunit test on line 206
		RuleReturnScope rstruct = (RuleReturnScope)execParser("element", "'a'+", 206);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(+ (BLOCK (ALT 'a')))";
		assertEquals("testing rule element", expecting, actual);
	}

	@Test public void test_element10() throws Exception {
		// gunit test on line 207
		RuleReturnScope rstruct = (RuleReturnScope)execParser("element", "a[3]", 207);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(a 3)";
		assertEquals("testing rule element", expecting, actual);
	}

	@Test public void test_element11() throws Exception {
		// gunit test on line 208
		RuleReturnScope rstruct = (RuleReturnScope)execParser("element", "'a'..'z'+", 208);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(+ (BLOCK (ALT (.. 'a' 'z'))))";
		assertEquals("testing rule element", expecting, actual);
	}

	@Test public void test_element12() throws Exception {
		// gunit test on line 209
		RuleReturnScope rstruct = (RuleReturnScope)execParser("element", "x=ID", 209);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(= x ID)";
		assertEquals("testing rule element", expecting, actual);
	}

	@Test public void test_element13() throws Exception {
		// gunit test on line 210
		RuleReturnScope rstruct = (RuleReturnScope)execParser("element", "x=ID?", 210);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(? (BLOCK (ALT (= x ID))))";
		assertEquals("testing rule element", expecting, actual);
	}

	@Test public void test_element14() throws Exception {
		// gunit test on line 211
		RuleReturnScope rstruct = (RuleReturnScope)execParser("element", "x=ID*", 211);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(* (BLOCK (ALT (= x ID))))";
		assertEquals("testing rule element", expecting, actual);
	}

	@Test public void test_element15() throws Exception {
		// gunit test on line 212
		RuleReturnScope rstruct = (RuleReturnScope)execParser("element", "x=b", 212);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(= x b)";
		assertEquals("testing rule element", expecting, actual);
	}

	@Test public void test_element16() throws Exception {
		// gunit test on line 213
		RuleReturnScope rstruct = (RuleReturnScope)execParser("element", "x=(A|B)", 213);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(= x (SET A B))";
		assertEquals("testing rule element", expecting, actual);
	}

	@Test public void test_element17() throws Exception {
		// gunit test on line 214
		RuleReturnScope rstruct = (RuleReturnScope)execParser("element", "x=(A|B)^", 214);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(= x (^ (SET A B)))";
		assertEquals("testing rule element", expecting, actual);
	}

	@Test public void test_element18() throws Exception {
		// gunit test on line 215
		RuleReturnScope rstruct = (RuleReturnScope)execParser("element", "x=~(A|B)", 215);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(= x (~ (SET A B)))";
		assertEquals("testing rule element", expecting, actual);
	}

	@Test public void test_element19() throws Exception {
		// gunit test on line 216
		RuleReturnScope rstruct = (RuleReturnScope)execParser("element", "x+=~(A|B)", 216);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(+= x (~ (SET A B)))";
		assertEquals("testing rule element", expecting, actual);
	}

	@Test public void test_element20() throws Exception {
		// gunit test on line 217
		RuleReturnScope rstruct = (RuleReturnScope)execParser("element", "x+=~(A|B)+", 217);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(+ (BLOCK (ALT (+= x (~ (BLOCK (ALT (SET A B))))))))";
		assertEquals("testing rule element", expecting, actual);
	}

	@Test public void test_element21() throws Exception {
		// gunit test on line 218
		RuleReturnScope rstruct = (RuleReturnScope)execParser("element", "x=b+", 218);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(+ (BLOCK (ALT (= x b))))";
		assertEquals("testing rule element", expecting, actual);
	}

	@Test public void test_element22() throws Exception {
		// gunit test on line 219
		RuleReturnScope rstruct = (RuleReturnScope)execParser("element", "x+=ID*", 219);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(* (BLOCK (ALT (+= x ID))))";
		assertEquals("testing rule element", expecting, actual);
	}

	@Test public void test_element23() throws Exception {
		// gunit test on line 220
		RuleReturnScope rstruct = (RuleReturnScope)execParser("element", "x+='int'*", 220);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(* (BLOCK (ALT (+= x 'int'))))";
		assertEquals("testing rule element", expecting, actual);
	}

	@Test public void test_element24() throws Exception {
		// gunit test on line 221
		RuleReturnScope rstruct = (RuleReturnScope)execParser("element", "x+=b+", 221);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(+ (BLOCK (ALT (+= x b))))";
		assertEquals("testing rule element", expecting, actual);
	}

	@Test public void test_element25() throws Exception {
		// gunit test on line 222
		RuleReturnScope rstruct = (RuleReturnScope)execParser("element", "('*'^)*", 222);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(* (BLOCK (ALT (^ '*'))))";
		assertEquals("testing rule element", expecting, actual);
	}

	@Test public void test_element26() throws Exception {
		// gunit test on line 223
		RuleReturnScope rstruct = (RuleReturnScope)execParser("element", "({blort} 'x')*", 223);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(* (BLOCK (ALT {blort} 'x')))";
		assertEquals("testing rule element", expecting, actual);
	}

	@Test public void test_element27() throws Exception {
		// gunit test on line 224
		RuleReturnScope rstruct = (RuleReturnScope)execParser("element", "A!", 224);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(! A)";
		assertEquals("testing rule element", expecting, actual);
	}

	@Test public void test_element28() throws Exception {
		// gunit test on line 225
		RuleReturnScope rstruct = (RuleReturnScope)execParser("element", "A^", 225);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(^ A)";
		assertEquals("testing rule element", expecting, actual);
	}

	@Test public void test_element29() throws Exception {
		// gunit test on line 226
		RuleReturnScope rstruct = (RuleReturnScope)execParser("element", "x=A^", 226);
		Object actual = ((Tree)rstruct.getTree()).toStringTree();
		Object expecting = "(= x (^ A))";
		assertEquals("testing rule element", expecting, actual);
	}
}