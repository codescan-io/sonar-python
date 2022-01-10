/*
 * SonarQube Python Plugin
 * Copyright (C) 2011-2022 SonarSource SA
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
package org.sonar.plugins.python.flake8;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.config.Configuration;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;
import org.sonar.plugins.python.ExternalIssuesSensor;
import org.sonar.plugins.python.TextReportReader;
import org.sonar.plugins.python.TextReportReader.Issue;

public class Flake8Sensor extends ExternalIssuesSensor {

  private static final Logger LOG = Loggers.get(Flake8Sensor.class);

  public static final String LINTER_NAME = "Flake8";
  public static final String LINTER_KEY = "flake8";
  public static final String REPORT_PATH_KEY = "sonar.python.flake8.reportPaths";

  @Override
  protected void importReport(File reportPath, SensorContext context, Set<String> unresolvedInputFiles) throws IOException {
    List<Issue> issues = new TextReportReader(TextReportReader.COLUMN_ONE_BASED).parse(reportPath, context.fileSystem());
    issues.forEach(i -> saveIssue(context, i, unresolvedInputFiles, LINTER_KEY));
  }

  @Override
  protected boolean shouldExecute(Configuration conf) {
    return conf.hasKey(REPORT_PATH_KEY);
  }

  @Override
  protected String linterName() {
    return LINTER_NAME;
  }

  @Override
  protected String reportPathKey() {
    return REPORT_PATH_KEY;
  }

  @Override
  protected Logger logger() {
    return LOG;
  }

}
