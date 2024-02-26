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
package org.sonar.python.checks.tests;

import org.junit.jupiter.api.Test;
import org.sonar.python.checks.quickfix.PythonQuickFixVerifier;
import org.sonar.python.checks.utils.PythonCheckVerifier;

class AssertAfterRaiseCheckTest {
  @Test
  void test() {
    PythonCheckVerifier.verify("src/test/resources/checks/tests/assertAfterRaise.py", new AssertAfterRaiseCheck());
  }

  @Test
  void testWithWrapper() {
    PythonCheckVerifier.verify("src/test/resources/checks/tests/assertAfterRaiseWithWrapper.py", new AssertAfterRaiseCheck());
  }

  @Test
  void testWithAnotherLibraryUnittest() {
    PythonCheckVerifier.verify("src/test/resources/checks/tests/assertAfterRaiseAnotherLibraryUnittest.py", new AssertAfterRaiseCheck());
  }

  @Test
  void testImportPytestAs() {
    PythonCheckVerifier.verify("src/test/resources/checks/tests/assertAfterRaiseImportPytestAs.py", new AssertAfterRaiseCheck());
  }

  @Test
  void quickFixTest() {
    var before = "import pytest\n" +
      "def test_base_case_multiple_statement():\n" +
      "    with pytest.raises(ZeroDivisionError):\n" +
      "        foo()\n" +
      "        assert bar() == 42 ";
    var after = "import pytest\n" +
      "def test_base_case_multiple_statement():\n" +
      "    with pytest.raises(ZeroDivisionError):\n" +
      "        foo()\n" +
      "    assert bar() == 42 ";

    var check = new AssertAfterRaiseCheck();
    PythonQuickFixVerifier.verify(check, before, after);
    PythonQuickFixVerifier.verifyQuickFixMessages(check, before, AssertAfterRaiseCheck.QUICK_FIX_MESSAGE);
  }

  @Test
  void inlineQuickFixTest() {
    var before = "import pytest\n" +
      "def test_base_case_multiple_statement():\n" +
      "    with pytest.raises(ZeroDivisionError): foo(); assert bar() == 42 ";
    var after = "import pytest\n" +
      "def test_base_case_multiple_statement():\n" +
      "    with pytest.raises(ZeroDivisionError): foo(); \n" +
      "    assert bar() == 42 ";

    var check = new AssertAfterRaiseCheck();
    PythonQuickFixVerifier.verify(check, before, after);
    PythonQuickFixVerifier.verifyQuickFixMessages(check, before, AssertAfterRaiseCheck.QUICK_FIX_MESSAGE);
  }

  @Test
  void noQuickFixTest() {
    var before = "import pytest\n" +
      "def test_base_case_multiple_statement():\n" +
      "    with pytest.raises(ZeroDivisionError):\n" +
      "        assert bar() == 42 ";

    PythonQuickFixVerifier.verifyNoQuickFixes(new AssertAfterRaiseCheck(), before);
  }

  @Test
  void inlineNoQuickFixTest() {
    var before = "import pytest\n" +
      "def test_base_case_multiple_statement():\n" +
      "    with pytest.raises(ZeroDivisionError): assert bar() == 42 ";

    PythonQuickFixVerifier.verifyNoQuickFixes(new AssertAfterRaiseCheck(), before);
  }
}
