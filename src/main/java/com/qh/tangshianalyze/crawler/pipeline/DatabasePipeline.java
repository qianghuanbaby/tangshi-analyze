package com.qh.tangshianalyze.crawler.pipeline;

import com.qh.tangshianalyze.crawler.common.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Author:qh
 * Created:2019/8/4
 */

public class DatabasePipeline implements Pipeline {
    private final Logger logger = LoggerFactory.getLogger(DatabasePipeline.class);
    private final DataSource dataSource;

    public DatabasePipeline(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void pipeline(Page page) {

        //System.out.println("存储到数据库："+poetryInfo);
        String dynasty = (String) page.getDataSet().getData("dynasty");
        String author = (String) page.getDataSet().getData("author");
        String title = (String) page.getDataSet().getData("title");
        String content = (String) page.getDataSet().getData("content");

        //修改对象

        String sql = "insert into poetry_info(title, dynasty, author, content)values (?,?,?,?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ){
            statement.setString(1,title);
            statement.setString(2,dynasty);
            statement.setString(3,author);
            statement.setString(4,content);
            statement.executeUpdate();

        }catch (SQLException e){
            logger.error("Database insert occur exception {} .",e.getMessage());
        }
    }
}

