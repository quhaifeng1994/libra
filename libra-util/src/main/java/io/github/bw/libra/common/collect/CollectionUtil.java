package io.github.bw.libra.common.collect;


import java.util.Collection;
import java.util.List;
import java.util.Map;

public final class CollectionUtil {

  private CollectionUtil() {
  }

  public static boolean isNotEmpty(Collection coll) {
    return !isEmpty(coll);
  }

  public static boolean isNotEmpty(Map map) {
    return !isEmpty(map);
  }

  public static boolean isEmpty(Collection coll) {
    return coll == null || coll.isEmpty();
  }

  public static boolean isEmpty(Map map) {
    return map == null || map.isEmpty();
  }

  public static <T> T getFirstElseNull(List<T> params) {
    if (CollectionUtil.isEmpty(params)) {
      return null;
    }
    return params.get(0);
  }

  public static <T> T getLastElseNull(List<T> params) {
    if (CollectionUtil.isEmpty(params)) {
      return null;
    }
    return params.get(params.size() - 1);
  }

  static <T> Collection<T> cast(Iterable<T> iterable) {
    return (Collection<T>) iterable;
  }

  public static <T> boolean contains(T param, T... params) {
    for (T t : params) {
      if (param == t || param.equals(t)) {
        return true;
      }
    }
    return false;
  }
}
