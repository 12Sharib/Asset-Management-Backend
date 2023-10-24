package com.assetmanagement;

import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.ext.ScriptUtils;

public class CustomMySqlContainer extends MySQLContainer<CustomMySqlContainer> {
    private static final String DB_IMAGE = "mysql:8.0.33";


    public CustomMySqlContainer() {
        super(DB_IMAGE);
    }


    @Override
    protected void runInitScriptIfRequired() {
        ScriptUtils.runInitScript(getDatabaseDelegate(), "sql/test-ddl.sql");
      //  ScriptUtils.runInitScript(getDatabaseDelegate(), "sql/test-dml.sql");
    }
}
