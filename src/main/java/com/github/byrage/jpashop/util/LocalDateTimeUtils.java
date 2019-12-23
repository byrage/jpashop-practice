package com.github.byrage.jpashop.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LocalDateTimeUtils {

    private static Clock clock = Clock.systemDefaultZone();

    public static LocalDateTime now() {
        return LocalDateTime.now(clock);
    }

    public static void fixClockForTest(LocalDateTime ldt) {
        clock = Clock.fixed(ldt.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
    }

    public static void restoreClock() {
        clock = Clock.systemDefaultZone();
    }
}
