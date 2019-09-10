/*
 * SonarQube Python Plugin
 * Copyright (C) 2011-2019 SonarSource SA
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
package org.sonar.python.tree;

import com.sonar.sslr.api.AstNode;
import com.sonar.sslr.api.Token;
import java.util.Arrays;
import java.util.List;
import javax.annotation.CheckForNull;
import org.sonar.python.api.tree.PyArgListTree;
import org.sonar.python.api.tree.PyClassDefTree;
import org.sonar.python.api.tree.PyDecoratorTree;
import org.sonar.python.api.tree.PyNameTree;
import org.sonar.python.api.tree.PyStatementListTree;
import org.sonar.python.api.tree.PyTreeVisitor;
import org.sonar.python.api.tree.Tree;

public class PyClassDefTreeImpl extends PyTree implements PyClassDefTree {

  private final List<PyDecoratorTree> decorators;
  private final Token classKeyword;
  private final PyNameTree name;
  private final Token leftPar;
  private final PyArgListTree args;
  private final Token rightPar;
  private final Token colon;
  private final PyStatementListTree body;
  private final Token docstring;

  public PyClassDefTreeImpl(AstNode astNode, List<PyDecoratorTree> decorators, Token classKeyword, PyNameTree name,
                            Token leftPar, PyArgListTree args, Token rightPar, Token colon, PyStatementListTree body, Token docstring) {
    super(astNode);
    this.decorators = decorators;
    this.classKeyword = classKeyword;
    this.name = name;
    this.leftPar = leftPar;
    this.args = args;
    this.rightPar = rightPar;
    this.colon = colon;
    this.body = body;
    this.docstring = docstring;
  }

  @Override
  public Kind getKind() {
    return Kind.CLASSDEF;
  }

  @Override
  public void accept(PyTreeVisitor visitor) {
    visitor.visitClassDef(this);
  }

  @Override
  public List<PyDecoratorTree> decorators() {
    return decorators;
  }

  @Override
  public Token classKeyword() {
    return classKeyword;
  }

  @Override
  public PyNameTree name() {
    return name;
  }

  @CheckForNull
  @Override
  public Token leftPar() {
    return leftPar;
  }

  @CheckForNull
  @Override
  public PyArgListTree args() {
    return args;
  }

  @CheckForNull
  @Override
  public Token rightPar() {
    return rightPar;
  }

  @Override
  public Token colon() {
    return colon;
  }

  @Override
  public PyStatementListTree body() {
    return body;
  }

  @CheckForNull
  @Override
  public Token docstring() {
    return docstring;
  }

  @Override
  public List<Tree> children() {
    return Arrays.asList(name, args, body);
  }
}
