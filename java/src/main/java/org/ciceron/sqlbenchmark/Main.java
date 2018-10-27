/*
 * Main SQL Benchmark
 * Copyright (C) 2018 Stephane Carrez
 * Written by Stephane Carrez (Stephane.Carrez@gmail.com)
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.ciceron.sqlbenchmark;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        String driver = "mysql";

        // Load the configuration file.
        try {
            Benchmark.loadConfiguration("sqlbench.properties");
        } catch (IOException ex) {
            System.err.println("Cannot load sqlbench.properties file");
            System.exit(1);
        }

        try {
            if (!Benchmark.setDatabase(driver)) {
                System.err.println("Cannot configure the database for driver: " + driver);
                System.exit(1);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            System.exit(1);
        }
        try {
            Benchmark[] tests = Simple.Create();

            for (Benchmark t : tests) {
                try {
                    t.run();
                } catch (Exception ex) {

                }
            }

        } catch (Exception e) {
            System.err.println(e);
        }

        Benchmark.printReport();
    }
}
