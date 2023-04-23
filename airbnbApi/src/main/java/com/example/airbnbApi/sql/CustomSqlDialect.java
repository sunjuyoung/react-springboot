package com.example.airbnbApi.sql;

import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.spi.MetadataBuilderContributor;
import org.hibernate.dialect.PostgreSQL94Dialect;
import org.hibernate.dialect.PostgreSQLDialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.query.spi.QueryEngine;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.context.annotation.Configuration;


public class CustomSqlDialect extends PostgreSQL94Dialect  {



}
