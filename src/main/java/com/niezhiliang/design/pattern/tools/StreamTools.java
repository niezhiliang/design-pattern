package com.niezhiliang.design.pattern.tools;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author niezhiliang
 * @version v
 * @date 2022/5/31 10:49
 */
public class StreamTools {

    /**
     * 筛选集合元素中单个元素组成集合
     * 
     * @param collection 集合数据
     * @param valueGetter 结果集合的元素值
     * @param <T>
     * @param <V>
     * @return
     */
    public static <T, V> List<V> mapToList(Collection<T> collection, Function<T, V> valueGetter) {
        if (isEmpty(collection)) {
            return new ArrayList<>(10);
        }
        return collection.stream().map(valueGetter).collect(Collectors.toList());
    }

    /**
     * 筛选集合元素中单个元素组成集合
     *
     * @param collection 集合数据
     * @param valueGetter 结果集合的元素值
     * @param <T>
     * @param <V>
     * @return
     */
    public static <T, V> Set<V> mapToSet(Collection<T> collection, Function<T, V> valueGetter) {
        if (isEmpty(collection)) {
            return new HashSet<>(10);
        }
        return collection.stream().map(valueGetter).collect(Collectors.toSet());
    }

    /**
     * 集合数据转Map，key = keyGetter.apply() value = Function.identity()
     * 
     * @param collection 集合数据
     * @param keyGetter key值
     * @param <K>
     * @param <T>
     * @return
     */
    public static <K, T> Map<K, T> collectToMap(Collection<T> collection, Function<T, K> keyGetter) {
        if (isEmpty(collection)) {
            return new HashMap<>(16);
        }
        return collection.stream().collect(Collectors.toMap(keyGetter, Function.identity(), (k1, k2) -> k2));
    }

    /**
     * 集合数据转Map key = keyGetter.apply() value = valGetter.apply()
     *
     * @param collection 集合数据
     * @param keyGetter key值
     * @param valGetter value值
     * @param <K>
     * @param <T>
     * @return
     */
    public static <K, T, V> Map<K, V> collectToMap(Collection<T> collection, Function<T, K> keyGetter,
        Function<T, V> valGetter) {
        if (isEmpty(collection)) {
            return new HashMap<>(16);
        }
        return collection.stream().collect(LinkedHashMap::new, (m, v) -> m.put(keyGetter.apply(v), valGetter.apply(v)),
            LinkedHashMap::putAll);
    }

    /**
     * 集合按属性分组 key = keyGetter.apply() value = List<?>
     * 
     * @param collection 集合数据
     * @param keyGetter key值
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T, K> Map<K, List<T>> groupingBy(Collection<T> collection, Function<T, K> keyGetter) {
        if (isEmpty(collection)) {
            return new HashMap<>(16);
        }
        return collection.stream().collect(Collectors.groupingBy(keyGetter));
    }

    /**
     * 集合数据条件过滤
     * 
     * @param collection 集合数据
     * @param predicate 判断条件
     * @param <T>
     * @return
     */
    public static <T> List<T> filterToList(Collection<T> collection, Predicate<? super T> predicate) {
        if (isEmpty(collection)) {
            return new ArrayList<>(10);
        }
        return collection.stream().filter(predicate).collect(Collectors.toList());
    }

    /**
     * 集合数据条件过滤
     *
     * @param collection 集合数据
     * @param predicate 判断条件
     * @param <T>
     * @return
     */
    public static <T> Set<T> filterToSet(Collection<T> collection, Predicate<? super T> predicate) {
        if (isEmpty(collection)) {
            return new HashSet<>(10);
        }
        return collection.stream().filter(predicate).collect(Collectors.toSet());
    }

    /**
     * 集合数据条件过滤 如果属性中有空值 comparator请这样写 Comparator.comparing(Object::field, *
     * Comparator.nullsFirst(Comparator.naturalOrder())))
     *
     * @param collection 集合数据
     * @return
     */
    public static <T> void sortAsc(List<T> collection, Comparator comparator) {
        if (notEmpty(collection)) {
            collection.sort(comparator);
        }
    }

    /**
     * 集合数据条件过滤, 如果属性中有空值comparator请这样写 Comparator.comparing(Object::field,
     * Comparator.nullsLast(Comparator.naturalOrder())))
     *
     * @param collection 集合数据
     * @return
     */
    public static <T> void sortDesc(List<T> collection, Comparator comparator) {
        if (notEmpty(collection)) {
            collection.sort(comparator.reversed());
        }
    }

    /**
     * 集合某个属性求和 (过滤掉null值)
     *
     * @param collection 集合数据
     * @param valueGetter 待相加属性
     * @param <T>
     * @return
     */
    public static <T> Integer sumInt(Collection<T> collection, Function<T, Integer> valueGetter) {
        if (isEmpty(collection)) {
            return null;
        }
        return collection.stream().filter(e -> Objects.nonNull(valueGetter.apply(e)))
            .mapToInt(e -> valueGetter.apply(e)).sum();
    }

    /**
     * 集合某个属性求和 (过滤掉null值)
     *
     * @param collection 集合数据
     * @param valueGetter 待相加属性
     * @param <T>
     * @return
     */
    public static <T> Double sumDouble(Collection<T> collection, Function<T, Integer> valueGetter) {
        if (isEmpty(collection)) {
            return null;
        }
        return collection.stream().filter(e -> Objects.nonNull(valueGetter.apply(e)))
            .mapToDouble(e -> valueGetter.apply(e)).sum();
    }

    /**
     * 集合某个属性求和 (过滤掉null值)
     *
     * @param collection 集合数据
     * @param valueGetter 待相加属性
     * @param <T>
     * @return
     */
    public static <T> Long sumLong(Collection<T> collection, Function<T, Long> valueGetter) {
        if (isEmpty(collection)) {
            return null;
        }
        return collection.stream().filter(e -> Objects.nonNull(valueGetter.apply(e)))
            .mapToLong(e -> valueGetter.apply(e)).sum();
    }

    /**
     * 集合某个属性求和 (过滤掉null值)
     *
     * @param collection 集合数据
     * @param valueGetter 待相加属性
     * @param <T>
     * @return
     */
    public static <T> BigDecimal sumDecimal(Collection<T> collection, Function<T, BigDecimal> valueGetter) {
        if (isEmpty(collection)) {
            return null;
        }
        return collection.stream().filter(e -> Objects.nonNull(valueGetter.apply(e))).map(e -> valueGetter.apply(e))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * 集合某个属性非空数量
     * 
     * @param collection 集合数据
     * @param valueGetter 属性值
     * @param <T>
     * @return
     */
    public static <T, R> long nonNullCount(Collection<T> collection, Function<T, R> valueGetter) {
        if (isEmpty(collection)) {
            return 0;
        }
        return collection.stream().filter(e -> Objects.nonNull(valueGetter.apply(e))).count();
    }

    /**
     * 多个集合求并集
     * 
     * @param collections 集合数组
     * @param <T>
     * @return
     */
    public static <T> List<T> flatMap(Collection<T>... collections) {
        if (Objects.isNull(collections)) {
            return new ArrayList<>();
        }
        return Arrays.stream(collections).flatMap(Collection::stream).collect(Collectors.toList());
    }

    /**
     * 集合是否为空
     * 
     * @param collection 集合数据
     * @return
     */
    public static boolean isEmpty(Collection<?> collection) {
        return (collection == null || collection.isEmpty());
    }

    /**
     * 集合是否不为空
     * 
     * @param collection 集合数据
     * @return
     */
    public static boolean notEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }
}
