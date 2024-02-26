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

import org.junit.jupiter.api.Test;
import org.sonar.python.checks.quickfix.PythonQuickFixVerifier;
import org.sonar.python.checks.utils.PythonCheckVerifier;

class BooleanExpressionInExceptCheckTest {

  @Test
  void test() {
    PythonCheckVerifier.verify("src/test/resources/checks/booleanExpressionInExcept.py", new BooleanExpressionInExceptCheck());
  }
  @Test
  void quickFixTest() {
    var before = "try:\n" +
      "    foo()\n" +
      "except ValueError or TypeError and SomeError:\n" +
      "    pass";

    var after = "try:\n" +
      "    foo()\n" +
      "except (ValueError, TypeError, SomeError):\n" +
      "    pass";
    verifyQuickFix(before, after);
  }

  @Test
  void wrappedInParenthesisQuickFixTest() {
    var before = "try:\n" +
      "    foo()\n" +
      "except (ValueError or TypeError and SomeError):\n" +
      "    pass";

    var after = "try:\n" +
      "    foo()\n" +
      "except (ValueError, TypeError, SomeError):\n" +
      "    pass";
    verifyQuickFix(before, after);
  }

  @Test
  void nestedQuickFixTest() {
    var before = "try:\n" +
      "    foo()\n" +
      "except ((ValueError or TypeError) and pkg.cstm.SomeError):\n" +
      "    pass";

    var after = "try:\n" +
      "    foo()\n" +
      "except (ValueError, TypeError, pkg.cstm.SomeError):\n" +
      "    pass";
    verifyQuickFix(before, after);
  }

  @Test
  void noQuickFixTest() {
    var before = "try:\n" +
      "    foo()\n" +
      "except (ValueError or pkg.cstm.SomeError()):\n" +
      "    pass";

    PythonQuickFixVerifier.verifyNoQuickFixes(new BooleanExpressionInExceptCheck(), before);
  }

  private void verifyQuickFix(String before, String after) {
    var check = new BooleanExpressionInExceptCheck();
    PythonQuickFixVerifier.verify(check, before, after);
    PythonQuickFixVerifier.verifyQuickFixMessages(check, before, BooleanExpressionInExceptCheck.QUICK_FIX_MESSAGE);
  }

}
