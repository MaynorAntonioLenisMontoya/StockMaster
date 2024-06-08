package co.com.brilla.sale.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This class provides generic methods to build objects of a given type from JSON or object arrays.
 *
 * @param <T> The type of object to build.
 */
@Slf4j
public class GenericBuild<T> {

    private final Class<T> objectType;

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    private final Gson gson ;

    /**
     * Constructor for GenericBuild.
     *
     * @param objectType The class of the object type to build.
     */
    public GenericBuild(Class<T> objectType) {
        this.objectType = objectType;
        this.gson = createGson();
    }

    /**
     * Builds an object of type T from a JSON representation.
     *
     * @param object The object to convert to JSON.
     * @return The object of type T.
     */
    public T build(Object object) {
        if (object==null) {
            return null;
        }
        List<Object> list = Arrays.asList((Object[]) object);
        JsonObject jsonObject = new JsonObject();
        Field[] fields = objectType.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            String fieldName = field.getName();
            if (i < list.size()) {
                Object fieldValue = list.get(i);
                jsonObject.add(fieldName, gson.toJsonTree(fieldValue));
            } else {
                jsonObject.add(fieldName, JsonNull.INSTANCE);
            }
        }
        return gson.fromJson(jsonObject, objectType);
    }


    /**
     * Builds a list of objects of type T from a list of object arrays.
     *
     * @param objects The list of object arrays.
     * @return The list of objects of type T.
     */
    public List<T> buildList(List<Object[]> objects) {
        List<T> result = new ArrayList<>();
        for (Object[] objArray : objects) {
            T obj = buildObjectArray(objArray, true);
            result.add(obj);
        }
        return result;
    }

    /**
     * Builds an object of type T from an object array.
     *
     * @param objArray The object array containing field values for the object.
     * @return The object of type T.
     */
    private T buildObjectArray(Object[] objArray, boolean withFormat) {
        Map<String, Object> map = new HashMap<>();
        Field[] fields = objectType.getDeclaredFields();
        int objArrayLength = Math.min(objArray.length, fields.length); // Obtener el tamaño mínimo entre objArray y fields

        for (int i = 0; i < objArrayLength; i++) {
            String fieldName = fields[i].getName();
            Object fieldValue = objArray[i];
            log.info("fileName -> {} ", fieldName);
            log.info("objArray[i] -> {} ", objArray[i]);

            if (withFormat) {

                if (fieldValue instanceof Date) {
                    // Si el campo es una instancia de Date, formatear la fecha antes de agregarla al mapa
                    Date dateValue = (Date) fieldValue;
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                    String formattedDate = dateFormat.format(dateValue);
                    map.put(fieldName, formattedDate);
                } else {
                    map.put(fieldName, fieldValue);
                }
            } else {
                map.put(fieldName, fieldValue);
            }
        }

        String json = gson.toJson(map);
        return gson.fromJson(json, objectType);
    }

    /**
     * Creates a custom Gson object with a field naming strategy based on the SerializedName annotation.
     *
     * @return The custom Gson object.
     */
    private Gson createGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingStrategy((field) -> {
            SerializedName serializedName = field.getAnnotation(SerializedName.class);
            if (serializedName != null) {
                String[] alternateNames = serializedName.alternate();
                for (String alternateName : alternateNames) {
                    if (alternateName.equalsIgnoreCase(field.getName())) {
                        return field.getName();
                    }
                }
                return alternateNames[0];
            }
            return field.getName();
        });
        return gsonBuilder
                .setDateFormat(DATE_FORMAT)
                .create();
    }

    /**
     * Get the value of a field from an object.
     *
     * @param field  The field to get the value from.
     * @param object The object containing the field.
     * @return The value of the field.
     */
    private Object getFieldValue(Field field, Object object) {
        try {
            field.setAccessible(true);
            Object value = field.get(object);

            // If the field value is an array, extract the first element
            if (value instanceof Object[]) {
                Object[] valueArray = (Object[]) value;
                if (valueArray.length > 0) {
                    value = valueArray[0];
                } else {
                    value = null;
                }
            }

            // Convert the value to the expected type (Long for productId)
            if (field.getType().equals(Long.class) && value != null) {
                if (value instanceof Number) {
                    value = ((Number) value).longValue();
                } else {
                    value = Long.parseLong(value.toString());
                }
            }

            return value;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<T> buildListWithoutFormatDate(List<Object[]> objects) {
        List<T> result = new ArrayList<>();
        for (Object[] objArray : objects) {
            T obj = buildObjectArray(objArray, false);
            log.info("obj -> {} ", obj);
            result.add(obj);
        }
        return result;
    }

}
