/*
 * SonarQube Python Plugin
 * Copyright (C) 2011-2024 SonarSource SA
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
package org.sonar.plugins.python;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.highlighting.NewHighlighting;
import org.sonar.api.batch.sensor.highlighting.TypeOfText;
import org.sonar.plugins.python.api.PythonSubscriptionCheck;
import org.sonar.plugins.python.api.PythonVisitorContext;
import org.sonar.python.SubscriptionVisitor;
import org.sonar.python.TokenLocation;
import org.sonar.python.api.PythonKeyword;
import org.sonar.python.api.PythonTokenType;
import org.sonar.plugins.python.api.tree.ClassDef;
import org.sonar.plugins.python.api.tree.FileInput;
import org.sonar.plugins.python.api.tree.FunctionDef;
import org.sonar.plugins.python.api.tree.StringLiteral;
import org.sonar.plugins.python.api.tree.Token;
import org.sonar.plugins.python.api.tree.Tree;
import org.sonar.plugins.python.api.tree.Trivia;

import static com.sonar.sslr.api.GenericTokenType.IDENTIFIER;

/**
 * Colors Python code. Currently colors:
 * <ul>
 *   <li>
 *     String literals. Examples:
 *     <pre>
 *       "hello"
 *
 *       'hello'
 *
 *       """ hello
 *           hello again
 *       """
 *     </pre>
 *   </li>
 *   <li>
 *     Keywords. Example:
 *     <pre>
 *       def
 *     </pre>
 *   </li>
 *   <li>
 *     Numbers. Example:
 *     <pre>
 *        123
 *        123L
 *        123.45
 *        123.45e-10
 *        123+88.99J
 *     </pre>
 *     For a negative number, the "minus" sign is not colored.
 *   </li>
 *   <li>
 *     Comments. Example:
 *     <pre>
 *        # some comment
 *     </pre>
 *   </li>
 * </ul>
 * Docstrings are handled (i.e., colored) as structured comments, not as normal string literals.
 * "Attribute docstrings" and "additional docstrings" (see PEP 258) are handled as normal string literals.
 * Reminder: a docstring is a string literal that occurs as the first statement in a module,
 * function, class, or method definition.
 */
public class PythonHighlighter extends PythonSubscriptionCheck {

  private NewHighlighting newHighlighting;

  private Set<Token> docStringTokens;

  InputFile currentFile;

  public PythonHighlighter(SensorContext context, InputFile inputFile) {
    docStringTokens = new HashSet<>();
    newHighlighting = context.newHighlighting();
    if (inputFile instanceof GeneratedIPythonFile generatedIPythonFile) {
      newHighlighting.onFile(generatedIPythonFile.originalFile);
    } else {
      newHighlighting.onFile(inputFile);
    }
    currentFile = inputFile;
  }

  @Override
  public void scanFile(PythonVisitorContext visitorContext) {
    SubscriptionVisitor.analyze(Collections.singletonList(this), visitorContext);
  }

  @Override
  public void initialize(Context context) {
    context.registerSyntaxNodeConsumer(Tree.Kind.FILE_INPUT, ctx -> checkFirstStatement(((FileInput) ctx.syntaxNode()).docstring()));
    context.registerSyntaxNodeConsumer(Tree.Kind.FUNCDEF, ctx -> checkFirstStatement(((FunctionDef) ctx.syntaxNode()).docstring()));
    context.registerSyntaxNodeConsumer(Tree.Kind.CLASSDEF, ctx -> checkFirstStatement(((ClassDef) ctx.syntaxNode()).docstring()));
    context.registerSyntaxNodeConsumer(Tree.Kind.TOKEN, ctx -> visitToken(((Token) ctx.syntaxNode())));
  }

  private void checkFirstStatement(@Nullable StringLiteral docString) {
    if (docString == null) {
      return;
    }
    for (Tree stringElement : docString.children()) {
      highlight(stringElement.firstToken(), TypeOfText.STRUCTURED_COMMENT);
      docStringTokens.add(stringElement.firstToken());
    }
  }

  private void visitToken(Token token) {
    if (token.type().equals(PythonTokenType.NUMBER)) {
      highlight(token, TypeOfText.CONSTANT);

    } else if (token.type() instanceof PythonKeyword) {
      highlight(token, TypeOfText.KEYWORD);

    } else if (token.type().equals(PythonTokenType.STRING) && !docStringTokens.contains(token)) {
      highlight(token, TypeOfText.STRING);

    } else if (token.type().equals(IDENTIFIER) && isPython3Keyword(token.value())) {
      // async and await are keywords starting python 3.5, however, for compatibility with previous versions, we cannot consider them as real keywords
      highlight(token, TypeOfText.KEYWORD);

    }

    for (Trivia trivia : token.trivia()) {
      highlight(trivia.token(), TypeOfText.COMMENT);
    }
  }

  private static boolean isPython3Keyword(String value) {
    return "await".equals(value) || "async".equals(value) || "match".equals(value) || "case".equals(value);
  }

  @Override
  public void leaveFile() {
    newHighlighting.save();
  }

  private void highlight(Token token, TypeOfText typeOfText) {
    TokenLocation tokenLocation = new TokenLocation(token);
    if (currentFile instanceof GeneratedIPythonFile generatedIPythonFile) {
      Map<Integer, GeneratedIPythonFile.Offset> notebookOffsetMap = generatedIPythonFile.offsetMap();
      var startOffset = notebookOffsetMap.get(tokenLocation.startLine());
      var endOffset = notebookOffsetMap.get(tokenLocation.endLine());
      newHighlighting.highlight(startOffset.line(),
        tokenLocation.startLineOffset() + startOffset.column(), endOffset.line(),
        tokenLocation.endLineOffset() + endOffset.column(), typeOfText);
    } else {
      newHighlighting.highlight(tokenLocation.startLine(), tokenLocation.startLineOffset(), tokenLocation.endLine(), tokenLocation.endLineOffset(), typeOfText);
    }
  }

}
