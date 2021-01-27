package com.evshou.admin.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class MybatisUtils {
    private static SqlSessionFactory ssf;
    static{
        try{
            InputStream in= Resources.getResourceAsStream("mybatis-config.xml");
            ssf=new SqlSessionFactoryBuilder().build(in);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static SqlSession getSqlSession(){
        return ssf.openSession();
    }
}
