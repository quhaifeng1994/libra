package io.github.bw.libra.common.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public final class ReflectUtil {

  private ReflectUtil() {
  }

  public static Object getFieldValue(Object object, String fieldName) {
    Field field = getDeclaredField(object, fieldName);
    if (field == null) {
      throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
    }
    makeAccessible(field);
    Object result = null;
    try {
      result = field.get(object);
    } catch (IllegalAccessException e) {
    }
    return result;
  }

  public static Field getDeclaredField(Object object, String filedName) {

    for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
      try {
        return superClass.getDeclaredField(filedName);
      } catch (NoSuchFieldException e) {
        //Field 不在当前类定义, 继续向上转型
      }
    }
    return null;
  }

  public static void makeAccessible(Field field) {
    if (!Modifier.isPublic(field.getModifiers()) && !field.isAccessible()) {
      field.setAccessible(true);
    }
  }
}
