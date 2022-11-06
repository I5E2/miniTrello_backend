package com.I5E2.todo.jpa;


import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * entity 필드 값과 데이터베이스 Column 값을 매칭시키는 클래스
 */
public class CustomPhysicalNamingStrategy extends PhysicalNamingStrategyStandardImpl {
    final private Map<String,String> abbreviateIdentifier = Stream.of(new String[][]{
            {"ADDRESS","ADDR"},
            {"ATTACH","ATCH"},
            {"CATEGORY","CTGR"},
            {"CODE","CD"},
            {"COMMON","CMMN"},
            {"CONTENTS","CN"},
            {"DATETIME","DT"},
            {"EMAIL","EML"},
            {"EXTENSION","EXTN"},
            {"LOGIN","LGN"},
            {"NAME","NM"},
            {"ORIGINAL","ORGNL"},
            {"PASSWORD","PSWD"},
            {"PROGRAM","PRGM"},
            {"PROGRESS","PRGRSRT"},
            {"STORAGE","STRG"},
            {"SERIALNUMBER","SN"},
            {"UPPER","UP"}


    }).collect(Collectors.toMap(data->data[0],data->data[1]));


    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return convertEntityName(name);
    }


    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return convertEntityName(name);
    }

    private Identifier convertEntityName(final Identifier identifier){
        if(identifier.getText().contains("_")){
            return identifier;
        }
        final String regex = "([a-z])([A-Z])";
        final String replacement = "$1_$2";
        final String newName = identifier.getText().replaceAll(regex,replacement).toUpperCase(Locale.ROOT);

        return Identifier.toIdentifier(abbreviate(newName));
    }

    private String abbreviate(String str){
        for (Map.Entry<String,String> word: abbreviateIdentifier.entrySet()) {
            if(str.contains(word.getKey())){
                str = str.replace(word.getKey(), word.getValue());
            }
        }
        return str;
    }


    public static String convertEntityName(final String string){
        if(string.contains("_")){
            return string;
        }
        final String regex = "([a-z])([A-Z])";
        final String replacement = "$1_$2";

        return string.replaceAll(regex,replacement).toUpperCase(Locale.ROOT);
    }

}
