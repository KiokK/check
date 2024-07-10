package ru.clevertec.check.util.reader;

import ru.clevertec.check.exception.InternalServerException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader<T> {

    private static final char FIELDS_WORD_SEPARATOR = '_';
    private static final String CSV_COLUMN_SEPARATOR = ";";

    public List<T> read(String filePath, Class<?> clazz) throws InternalServerException {
        List<T> objects = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String fileLine = null;
            T readedObj = null;
            boolean isModifyAccessible;

            if ((fileLine = reader.readLine()) == null) {
                throw new InternalServerException();
            }
            List<String> fields = toFieldsFormat(fileLine.split(CSV_COLUMN_SEPARATOR));

            while ((fileLine = reader.readLine()) != null) {
                try {
                    readedObj = (T) clazz.newInstance();
                    String[] readObjValues = fileLine.split(CSV_COLUMN_SEPARATOR);

                    for (int i = 0; i < readObjValues.length; i++) {
                        Field field = clazz.getDeclaredField(fields.get(i));

                        isModifyAccessible = field.trySetAccessible();
                        Object value = field.getType().getDeclaredConstructor(String.class).newInstance(readObjValues[i]);
                        field.set(readedObj, value);

                        if (isModifyAccessible) {
                            field.setAccessible(false);
                        }
                    }
                    objects.add(readedObj);
                } catch (IllegalAccessException | InstantiationException | NoSuchFieldException |
                         NoSuchMethodException | InvocationTargetException reflectiveException) {
                    throw new InternalServerException();
                }
            }
        } catch (IOException e) {
            throw new InternalServerException();
        }

        return objects;
    }

    private List<String> toFieldsFormat(String[] fields) {
        StringBuilder updateFieldName = new StringBuilder();
        List<String> updateFieldsNames = new ArrayList<>();

        for (String field : fields) {
            for (int i = 0; i < field.length(); i++) {
                if (field.charAt(i) == FIELDS_WORD_SEPARATOR) {
                    if (++i >= field.length()) {
                        break;
                    }
                    updateFieldName.append(Character.toUpperCase(field.charAt(i)));
                } else {
                    updateFieldName.append(field.charAt(i));
                }
            }
            updateFieldsNames.add(updateFieldName.toString());
            updateFieldName = new StringBuilder();
        }

        return updateFieldsNames;
    }
}
