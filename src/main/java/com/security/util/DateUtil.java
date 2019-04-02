package com.security.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Component//mapeada pelo componente scan
//@Repository // especialização do component para trabalhar com Dao
//@Service    // especialização do component para trabalhar com camada de servico
public class DateUtil {
    public String formatLocalDatabaseSyle(LocalDateTime locaDatetime){
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(locaDatetime);
    }
}