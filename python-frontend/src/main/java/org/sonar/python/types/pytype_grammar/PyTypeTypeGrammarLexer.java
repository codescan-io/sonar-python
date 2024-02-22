/*
 * SonarQube Python Plugin
 * Copyright (C) 2011-2023 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
// Generated from PyTypeTypeGrammar.g4 by ANTLR 4.13.1

package org.sonar.python.types.pytype_grammar;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class PyTypeTypeGrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, UNION_PREFIX=5, CLASS_PREFIX=6, GENERIC_CALLABLE_PREFIX=7, 
		ANYTHING_TYPE=8, BUILTINS_PREFIX=9, NONE_TYPE=10, STR=11, BOOL=12, INT=13, 
		FLOAT=14, COMPLEX=15, TUPLE=16, LIST=17, SET=18, DICT=19, PARAMETERS=20, 
		STRING=21, SKIPS=22;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "UNION_PREFIX", "CLASS_PREFIX", "GENERIC_CALLABLE_PREFIX", 
			"ANYTHING_TYPE", "BUILTINS_PREFIX", "NONE_TYPE", "STR", "BOOL", "INT", 
			"FLOAT", "COMPLEX", "TUPLE", "LIST", "SET", "DICT", "PARAMETERS", "STRING", 
			"SKIPS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "')'", "'.'", "','", "'('", "'UnionType(type_list='", "'ClassType('", 
			"'GenericType(base_type=ClassType(typing.Callable)'", "'AnythingType()'", 
			"'builtins'", "'NoneType'", "'str'", "'bool'", "'int'", "'float'", "'complex'", 
			"'tuple'", "'list'", "'set'", "'dict'", "'parameters='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, "UNION_PREFIX", "CLASS_PREFIX", "GENERIC_CALLABLE_PREFIX", 
			"ANYTHING_TYPE", "BUILTINS_PREFIX", "NONE_TYPE", "STR", "BOOL", "INT", 
			"FLOAT", "COMPLEX", "TUPLE", "LIST", "SET", "DICT", "PARAMETERS", "STRING", 
			"SKIPS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public PyTypeTypeGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "PyTypeTypeGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u0016\u00ee\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0014"+
		"\u0004\u0014\u00e4\b\u0014\u000b\u0014\f\u0014\u00e5\u0001\u0015\u0004"+
		"\u0015\u00e9\b\u0015\u000b\u0015\f\u0015\u00ea\u0001\u0015\u0001\u0015"+
		"\u0000\u0000\u0016\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005"+
		"\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019"+
		"\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014)\u0015"+
		"+\u0016\u0001\u0000\u0002\u0002\u0000AZaz\u0003\u0000\t\n\r\r  \u00ef"+
		"\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000"+
		"\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000"+
		"\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000"+
		"\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011"+
		"\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015"+
		"\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019"+
		"\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d"+
		"\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001"+
		"\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000"+
		"\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000"+
		"\u0000+\u0001\u0000\u0000\u0000\u0001-\u0001\u0000\u0000\u0000\u0003/"+
		"\u0001\u0000\u0000\u0000\u00051\u0001\u0000\u0000\u0000\u00073\u0001\u0000"+
		"\u0000\u0000\t5\u0001\u0000\u0000\u0000\u000bJ\u0001\u0000\u0000\u0000"+
		"\rU\u0001\u0000\u0000\u0000\u000f\u0086\u0001\u0000\u0000\u0000\u0011"+
		"\u0095\u0001\u0000\u0000\u0000\u0013\u009e\u0001\u0000\u0000\u0000\u0015"+
		"\u00a7\u0001\u0000\u0000\u0000\u0017\u00ab\u0001\u0000\u0000\u0000\u0019"+
		"\u00b0\u0001\u0000\u0000\u0000\u001b\u00b4\u0001\u0000\u0000\u0000\u001d"+
		"\u00ba\u0001\u0000\u0000\u0000\u001f\u00c2\u0001\u0000\u0000\u0000!\u00c8"+
		"\u0001\u0000\u0000\u0000#\u00cd\u0001\u0000\u0000\u0000%\u00d1\u0001\u0000"+
		"\u0000\u0000\'\u00d6\u0001\u0000\u0000\u0000)\u00e3\u0001\u0000\u0000"+
		"\u0000+\u00e8\u0001\u0000\u0000\u0000-.\u0005)\u0000\u0000.\u0002\u0001"+
		"\u0000\u0000\u0000/0\u0005.\u0000\u00000\u0004\u0001\u0000\u0000\u0000"+
		"12\u0005,\u0000\u00002\u0006\u0001\u0000\u0000\u000034\u0005(\u0000\u0000"+
		"4\b\u0001\u0000\u0000\u000056\u0005U\u0000\u000067\u0005n\u0000\u0000"+
		"78\u0005i\u0000\u000089\u0005o\u0000\u00009:\u0005n\u0000\u0000:;\u0005"+
		"T\u0000\u0000;<\u0005y\u0000\u0000<=\u0005p\u0000\u0000=>\u0005e\u0000"+
		"\u0000>?\u0005(\u0000\u0000?@\u0005t\u0000\u0000@A\u0005y\u0000\u0000"+
		"AB\u0005p\u0000\u0000BC\u0005e\u0000\u0000CD\u0005_\u0000\u0000DE\u0005"+
		"l\u0000\u0000EF\u0005i\u0000\u0000FG\u0005s\u0000\u0000GH\u0005t\u0000"+
		"\u0000HI\u0005=\u0000\u0000I\n\u0001\u0000\u0000\u0000JK\u0005C\u0000"+
		"\u0000KL\u0005l\u0000\u0000LM\u0005a\u0000\u0000MN\u0005s\u0000\u0000"+
		"NO\u0005s\u0000\u0000OP\u0005T\u0000\u0000PQ\u0005y\u0000\u0000QR\u0005"+
		"p\u0000\u0000RS\u0005e\u0000\u0000ST\u0005(\u0000\u0000T\f\u0001\u0000"+
		"\u0000\u0000UV\u0005G\u0000\u0000VW\u0005e\u0000\u0000WX\u0005n\u0000"+
		"\u0000XY\u0005e\u0000\u0000YZ\u0005r\u0000\u0000Z[\u0005i\u0000\u0000"+
		"[\\\u0005c\u0000\u0000\\]\u0005T\u0000\u0000]^\u0005y\u0000\u0000^_\u0005"+
		"p\u0000\u0000_`\u0005e\u0000\u0000`a\u0005(\u0000\u0000ab\u0005b\u0000"+
		"\u0000bc\u0005a\u0000\u0000cd\u0005s\u0000\u0000de\u0005e\u0000\u0000"+
		"ef\u0005_\u0000\u0000fg\u0005t\u0000\u0000gh\u0005y\u0000\u0000hi\u0005"+
		"p\u0000\u0000ij\u0005e\u0000\u0000jk\u0005=\u0000\u0000kl\u0005C\u0000"+
		"\u0000lm\u0005l\u0000\u0000mn\u0005a\u0000\u0000no\u0005s\u0000\u0000"+
		"op\u0005s\u0000\u0000pq\u0005T\u0000\u0000qr\u0005y\u0000\u0000rs\u0005"+
		"p\u0000\u0000st\u0005e\u0000\u0000tu\u0005(\u0000\u0000uv\u0005t\u0000"+
		"\u0000vw\u0005y\u0000\u0000wx\u0005p\u0000\u0000xy\u0005i\u0000\u0000"+
		"yz\u0005n\u0000\u0000z{\u0005g\u0000\u0000{|\u0005.\u0000\u0000|}\u0005"+
		"C\u0000\u0000}~\u0005a\u0000\u0000~\u007f\u0005l\u0000\u0000\u007f\u0080"+
		"\u0005l\u0000\u0000\u0080\u0081\u0005a\u0000\u0000\u0081\u0082\u0005b"+
		"\u0000\u0000\u0082\u0083\u0005l\u0000\u0000\u0083\u0084\u0005e\u0000\u0000"+
		"\u0084\u0085\u0005)\u0000\u0000\u0085\u000e\u0001\u0000\u0000\u0000\u0086"+
		"\u0087\u0005A\u0000\u0000\u0087\u0088\u0005n\u0000\u0000\u0088\u0089\u0005"+
		"y\u0000\u0000\u0089\u008a\u0005t\u0000\u0000\u008a\u008b\u0005h\u0000"+
		"\u0000\u008b\u008c\u0005i\u0000\u0000\u008c\u008d\u0005n\u0000\u0000\u008d"+
		"\u008e\u0005g\u0000\u0000\u008e\u008f\u0005T\u0000\u0000\u008f\u0090\u0005"+
		"y\u0000\u0000\u0090\u0091\u0005p\u0000\u0000\u0091\u0092\u0005e\u0000"+
		"\u0000\u0092\u0093\u0005(\u0000\u0000\u0093\u0094\u0005)\u0000\u0000\u0094"+
		"\u0010\u0001\u0000\u0000\u0000\u0095\u0096\u0005b\u0000\u0000\u0096\u0097"+
		"\u0005u\u0000\u0000\u0097\u0098\u0005i\u0000\u0000\u0098\u0099\u0005l"+
		"\u0000\u0000\u0099\u009a\u0005t\u0000\u0000\u009a\u009b\u0005i\u0000\u0000"+
		"\u009b\u009c\u0005n\u0000\u0000\u009c\u009d\u0005s\u0000\u0000\u009d\u0012"+
		"\u0001\u0000\u0000\u0000\u009e\u009f\u0005N\u0000\u0000\u009f\u00a0\u0005"+
		"o\u0000\u0000\u00a0\u00a1\u0005n\u0000\u0000\u00a1\u00a2\u0005e\u0000"+
		"\u0000\u00a2\u00a3\u0005T\u0000\u0000\u00a3\u00a4\u0005y\u0000\u0000\u00a4"+
		"\u00a5\u0005p\u0000\u0000\u00a5\u00a6\u0005e\u0000\u0000\u00a6\u0014\u0001"+
		"\u0000\u0000\u0000\u00a7\u00a8\u0005s\u0000\u0000\u00a8\u00a9\u0005t\u0000"+
		"\u0000\u00a9\u00aa\u0005r\u0000\u0000\u00aa\u0016\u0001\u0000\u0000\u0000"+
		"\u00ab\u00ac\u0005b\u0000\u0000\u00ac\u00ad\u0005o\u0000\u0000\u00ad\u00ae"+
		"\u0005o\u0000\u0000\u00ae\u00af\u0005l\u0000\u0000\u00af\u0018\u0001\u0000"+
		"\u0000\u0000\u00b0\u00b1\u0005i\u0000\u0000\u00b1\u00b2\u0005n\u0000\u0000"+
		"\u00b2\u00b3\u0005t\u0000\u0000\u00b3\u001a\u0001\u0000\u0000\u0000\u00b4"+
		"\u00b5\u0005f\u0000\u0000\u00b5\u00b6\u0005l\u0000\u0000\u00b6\u00b7\u0005"+
		"o\u0000\u0000\u00b7\u00b8\u0005a\u0000\u0000\u00b8\u00b9\u0005t\u0000"+
		"\u0000\u00b9\u001c\u0001\u0000\u0000\u0000\u00ba\u00bb\u0005c\u0000\u0000"+
		"\u00bb\u00bc\u0005o\u0000\u0000\u00bc\u00bd\u0005m\u0000\u0000\u00bd\u00be"+
		"\u0005p\u0000\u0000\u00be\u00bf\u0005l\u0000\u0000\u00bf\u00c0\u0005e"+
		"\u0000\u0000\u00c0\u00c1\u0005x\u0000\u0000\u00c1\u001e\u0001\u0000\u0000"+
		"\u0000\u00c2\u00c3\u0005t\u0000\u0000\u00c3\u00c4\u0005u\u0000\u0000\u00c4"+
		"\u00c5\u0005p\u0000\u0000\u00c5\u00c6\u0005l\u0000\u0000\u00c6\u00c7\u0005"+
		"e\u0000\u0000\u00c7 \u0001\u0000\u0000\u0000\u00c8\u00c9\u0005l\u0000"+
		"\u0000\u00c9\u00ca\u0005i\u0000\u0000\u00ca\u00cb\u0005s\u0000\u0000\u00cb"+
		"\u00cc\u0005t\u0000\u0000\u00cc\"\u0001\u0000\u0000\u0000\u00cd\u00ce"+
		"\u0005s\u0000\u0000\u00ce\u00cf\u0005e\u0000\u0000\u00cf\u00d0\u0005t"+
		"\u0000\u0000\u00d0$\u0001\u0000\u0000\u0000\u00d1\u00d2\u0005d\u0000\u0000"+
		"\u00d2\u00d3\u0005i\u0000\u0000\u00d3\u00d4\u0005c\u0000\u0000\u00d4\u00d5"+
		"\u0005t\u0000\u0000\u00d5&\u0001\u0000\u0000\u0000\u00d6\u00d7\u0005p"+
		"\u0000\u0000\u00d7\u00d8\u0005a\u0000\u0000\u00d8\u00d9\u0005r\u0000\u0000"+
		"\u00d9\u00da\u0005a\u0000\u0000\u00da\u00db\u0005m\u0000\u0000\u00db\u00dc"+
		"\u0005e\u0000\u0000\u00dc\u00dd\u0005t\u0000\u0000\u00dd\u00de\u0005e"+
		"\u0000\u0000\u00de\u00df\u0005r\u0000\u0000\u00df\u00e0\u0005s\u0000\u0000"+
		"\u00e0\u00e1\u0005=\u0000\u0000\u00e1(\u0001\u0000\u0000\u0000\u00e2\u00e4"+
		"\u0007\u0000\u0000\u0000\u00e3\u00e2\u0001\u0000\u0000\u0000\u00e4\u00e5"+
		"\u0001\u0000\u0000\u0000\u00e5\u00e3\u0001\u0000\u0000\u0000\u00e5\u00e6"+
		"\u0001\u0000\u0000\u0000\u00e6*\u0001\u0000\u0000\u0000\u00e7\u00e9\u0007"+
		"\u0001\u0000\u0000\u00e8\u00e7\u0001\u0000\u0000\u0000\u00e9\u00ea\u0001"+
		"\u0000\u0000\u0000\u00ea\u00e8\u0001\u0000\u0000\u0000\u00ea\u00eb\u0001"+
		"\u0000\u0000\u0000\u00eb\u00ec\u0001\u0000\u0000\u0000\u00ec\u00ed\u0006"+
		"\u0015\u0000\u0000\u00ed,\u0001\u0000\u0000\u0000\u0003\u0000\u00e5\u00ea"+
		"\u0001\u0000\u0001\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}