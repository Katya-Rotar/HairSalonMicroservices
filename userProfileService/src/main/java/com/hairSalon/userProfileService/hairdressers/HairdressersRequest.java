package com.hairSalon.userProfileService.hairdressers;

import java.math.BigDecimal;

public record HairdressersRequest(
        Long userId,
        String portfolio,
        String certificates,
        BigDecimal rating,
        boolean isVerified
) {
}
