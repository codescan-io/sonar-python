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
package org.sonar.python.checks;

import java.util.Optional;
import java.util.Set;
import org.sonar.check.Rule;
import org.sonar.plugins.python.api.symbols.Symbol;
import org.sonar.plugins.python.api.tree.Expression;
import org.sonar.plugins.python.api.tree.QualifiedExpression;
import org.sonar.plugins.python.api.tree.Tree;

@Rule(key = "S6779")
public class FlaskHardCodedSecretKeyCheck extends FlaskHardCodedSecret {

  private static final String SECRET_KEY_KEYWORD = "SECRET_KEY";
  private static final String SECRET_KEY_TYPE = "\"Flask\"";
  private static final Set<String> FLASK_SECRET_KEY_FQNS = Set.of(
    "flask.app.Flask.secret_key",
    "flask.globals.current_app.secret_key"
  );

  @Override
  protected String getSecretKeyKeyword() {
    return SECRET_KEY_KEYWORD;
  }

  @Override
  protected String getSecretKeyType() {
    return SECRET_KEY_TYPE;
  }

  @Override
  protected boolean isSensitiveProperty(Expression expression) {
    if (expression.is(Tree.Kind.QUALIFIED_EXPR)) {
      return Optional.of((QualifiedExpression) expression)
        .map(QualifiedExpression::symbol)
        .map(Symbol::fullyQualifiedName)
        .filter(FLASK_SECRET_KEY_FQNS::contains)
        .isPresent();
    }
    return super.isSensitiveProperty(expression);
  }

}
