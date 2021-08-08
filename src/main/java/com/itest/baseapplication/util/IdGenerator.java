package com.itest.baseapplication.util;



import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;

public class IdGenerator implements IdentifierGenerator, Configurable {

    private String prefix;

    @Override
    public Serializable generate( SharedSessionContractImplementor  session, Object table)
            throws HibernateException {

        String query = String.format("select count(*) from Admin", table.getClass().getSimpleName());

         Object num= session.createQuery(query).list().get(0);

        return prefix+num.toString();
    }

    @Override
    public void configure( Type type, Properties properties,
                           ServiceRegistry serviceRegistry) throws MappingException {
        prefix = properties.getProperty("prefix");
    }
}