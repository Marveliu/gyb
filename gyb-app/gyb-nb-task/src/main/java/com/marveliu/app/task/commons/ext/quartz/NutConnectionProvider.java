package com.marveliu.app.task.commons.ext.quartz;

import org.nutz.boot.AppContext;
import org.nutz.ioc.Ioc;
import org.quartz.utils.ConnectionProvider;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class NutConnectionProvider implements ConnectionProvider {

    protected DataSource dataSource;
    protected String iocname = "dataSource";

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public void shutdown() throws SQLException {
    }

    @SuppressWarnings("deprecation")
    @Override
    public void initialize() throws SQLException {
        if (dataSource != null)
            return;
        Ioc ioc = AppContext.getDefault().getIoc();
        dataSource = ioc.get(DataSource.class, iocname);
    }

}
