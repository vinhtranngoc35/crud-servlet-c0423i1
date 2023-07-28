package com.example.c0423i1module3.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;

public class AppUtil {
    private static ObjectMapper mapper;
    static {
        mapper = new ObjectMapper();
    }
    public static Object getObject(HttpServletRequest request, Class clazz) {
        // Get all request parameter names
        Object object;
        try {
            object = clazz.newInstance();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

        java.util.Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();

            if(AppConstant.ACTION.equals(paramName)){
                continue;
            }
            // Use reflection to set the parameter value to the corresponding field in the User class
            try {
                String paramValue = mapper.writeValueAsString(request.getParameter(paramName));
                Field field = clazz.getDeclaredField(paramName);
                field.setAccessible(true); // Set accessible, as the fields may be private
                Class<?> fieldType = field.getType();

                var value = mapper.readValue(paramValue,fieldType);
                field.set(object, value);
                // Add more type conversions as needed for other field types (e.g., boolean, double, etc.)
            } catch (NoSuchFieldException | IllegalAccessException | NumberFormatException e) {
                // Handle exceptions as needed
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

        }
        return object;
    }
}
