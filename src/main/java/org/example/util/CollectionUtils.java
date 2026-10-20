package org.example.util;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Generic helpers that work with a collection of ANY type T
 *
 * @author Karyna Litvinova
 */
public final class CollectionUtils {

    private CollectionUtils() {
        // utility class - no instances
    }

    public static <T> List<T> filter(Collection<T> items, Predicate<? super T> condition) {
        return items.stream().filter(condition).toList();
    }

    public static <T> List<T> sorted(Collection<T> items, Comparator<? super T> comparator) {
        return items.stream().sorted(comparator).toList();
    }
}
