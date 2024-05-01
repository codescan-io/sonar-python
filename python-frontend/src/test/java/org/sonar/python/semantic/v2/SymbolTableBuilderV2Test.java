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
package org.sonar.python.semantic.v2;

import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sonar.plugins.python.api.tree.AssignmentStatement;
import org.sonar.plugins.python.api.tree.FileInput;
import org.sonar.plugins.python.api.tree.FunctionDef;
import org.sonar.plugins.python.api.tree.ImportName;
import org.sonar.plugins.python.api.tree.Name;
import org.sonar.python.PythonTestUtils;
import org.sonar.python.tree.TreeUtils;

class SymbolTableBuilderV2Test {


  @Test
  void testSymbolTableModuleSymbols() {
    FileInput fileInput = PythonTestUtils.parse(
      """
        import lib
                
        v = lib.foo()
        a = lib.A()
        b = a.do_something()
        x = 42
                
        def script_do_something(param):
            c = 42
            return c
                
        script_do_something()
        """
    );

    var symbolTable = new SymbolTableBuilderV2(fileInput)
      .build();
    
    var moduleSymbols = symbolTable.getSymbolsByRootTree(fileInput);
    Assertions.assertThat(moduleSymbols)
      .hasSize(6)
      .extracting(SymbolV2::name)
      .contains("lib", "a", "b", "v", "x", "script_do_something");
  }

  @Test
  void testSymbolTableLocalSymbols() {
    FileInput fileInput = PythonTestUtils.parse(
      """
        import lib
        a = lib.A()
        def script_do_something(param):
            c = 42
            return c
        """
    );

    var symbolTable = new SymbolTableBuilderV2(fileInput)
      .build();

    var localSymbols = TreeUtils.firstChild(fileInput, FunctionDef.class::isInstance)
      .map(symbolTable::getSymbolsByRootTree)
      .orElseGet(Set::of);

    Assertions.assertThat(localSymbols)
      .hasSize(2)
      .extracting(SymbolV2::name)
      .contains("param", "c");
  }

  @Test
  void testSymbolTableGlobalSymbols() {
    FileInput fileInput = PythonTestUtils.parse(
      """
        global a
        def script_do_something(param):
            global b
       
        """
    );

    var symbolTable = new SymbolTableBuilderV2(fileInput)
      .build();

    var localSymbols = TreeUtils.firstChild(fileInput, FunctionDef.class::isInstance)
      .map(symbolTable::getSymbolsByRootTree)
      .orElseGet(Set::of);
    var moduleSymbols = symbolTable.getSymbolsByRootTree(fileInput);

    Assertions.assertThat(localSymbols)
      .hasSize(1)
      .extracting(SymbolV2::name)
      .contains("param");

    Assertions.assertThat(moduleSymbols)
      .hasSize(3)
      .extracting(SymbolV2::name)
      .contains("a", "b", "script_do_something");
  }

  @Test
  void testNameSymbols() {
    FileInput fileInput = PythonTestUtils.parse(
      """
        import lib
        
        v = lib.foo()
        a = lib.A()
        b = a.do_something()
        x = 42
        
        def script_do_something(param):
            return 42
        
        script_do_something()
        """
    );

    new SymbolTableBuilderV2(fileInput)
      .build();

    var statements = fileInput.statements().statements();

    {
      var importStatement = (ImportName) statements.get(0);
      var libName = importStatement.modules().get(0).dottedName().names().get(0);
      assertNameSymbol(libName, "lib", 3);
    }

    {
      var assignmentStatement = (AssignmentStatement) statements.get(1);
      var vName = (Name) assignmentStatement.children().get(0).children().get(0);
      assertNameSymbol(vName, "v", 1);

      var libName = (Name) assignmentStatement.children().get(2).children().get(0).children().get(0);
      assertNameSymbol(libName, "lib", 3);
    }

    {
      var assignmentStatement = (AssignmentStatement) statements.get(2);
      var aName = (Name) assignmentStatement.children().get(0).children().get(0);
      assertNameSymbol(aName, "a", 2);

      var libName = (Name) assignmentStatement.children().get(2).children().get(0).children().get(0);
      assertNameSymbol(libName, "lib", 3);
    }

    {
      var assignmentStatement = (AssignmentStatement) statements.get(3);
      var bName = (Name) assignmentStatement.children().get(0).children().get(0);
      assertNameSymbol(bName, "b", 1);

      var aName = (Name) assignmentStatement.children().get(2).children().get(0).children().get(0);
      assertNameSymbol(aName, "a", 2);
    }

    {
      var assignmentStatement = (AssignmentStatement) statements.get(4);
      var xName = (Name) assignmentStatement.children().get(0).children().get(0);
      assertNameSymbol(xName, "x", 1);
    }

    {
      var functionDef = (FunctionDef) statements.get(5);
      var scriptFunctionName = (Name) functionDef.name();
      assertNameSymbol(scriptFunctionName, "script_do_something", 2);
    }

    {
      var functionCallName = (Name) statements.get(6).children().get(0).children().get(0);
      assertNameSymbol(functionCallName, "script_do_something", 2);
    }

  }

  private static void assertNameSymbol(Name name, String expectedSymbolName, int expectedUsagesCount) {
    var symbol = name.symbolV2();
    Assertions.assertThat(symbol).isNotNull();
    Assertions.assertThat(symbol.name()).isEqualTo(expectedSymbolName);
    Assertions.assertThat(symbol.usages()).hasSize(expectedUsagesCount);
  }
}
