package com.caseyjbrooks.clog.parsers;

/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.Objects;

/**
 * Container to ease passing around a tuple of two objects. This object provides a sensible
 * implementation of equals(), returning true if equals() is true on each of the contained
 * objects. This class was copied from the AOSP Pair class
 * (https://github.com/android/platform_frameworks_base/blob/master/core/java/android/util/Pair.java)
 * because this class is used extensively in the Parseltongue parser, but this library is built for
 * plain Java.
 */
public class ParseltonguePair<F, S> {
    public final F first;
    public final S second;

    /**
     * Constructor for a ParseltonguePair.
     *
     * @param first the first object in the ParseltonguePair
     * @param second the second object in the ParseltonguePair
     */
    public ParseltonguePair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Checks the two objects for equality by delegating to their respective
     * {@link Object#equals(Object)} methods.
     *
     * @param o the {@link ParseltonguePair} to which this one is to be checked for equality
     * @return true if the underlying objects of the ParseltonguePair are both considered
     *         equal
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ParseltonguePair)) {
            return false;
        }
        ParseltonguePair<?, ?> p = (ParseltonguePair<?, ?>) o;
        return Objects.equals(p.first, first) && Objects.equals(p.second, second);
    }

    /**
     * Compute a hash code using the hash codes of the underlying objects
     *
     * @return a hashcode of the ParseltonguePair
     */
    @Override
    public int hashCode() {
        return (first == null ? 0 : first.hashCode()) ^ (second == null ? 0 : second.hashCode());
    }

    @Override
    public String toString() {
        return "ParseltonguePair{" + String.valueOf(first) + " " + String.valueOf(second) + "}";
    }

    /**
     * Convenience method for creating an appropriately typed ParseltonguePair.
     * @param a the first object in the ParseltonguePair
     * @param b the second object in the ParseltonguePair
     * @return a ParseltonguePair that is templatized with the types of a and b
     */
    public static <A, B> ParseltonguePair <A, B> create(A a, B b) {
        return new ParseltonguePair<A, B>(a, b);
    }
}
