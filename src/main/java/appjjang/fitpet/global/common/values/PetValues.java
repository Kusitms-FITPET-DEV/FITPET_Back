package appjjang.fitpet.global.common.values;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public final class PetValues {
    public static String UPDATE_CYCLE = "3년";
    public static String SELF_BURDEN = "1만원";
    public static String ONE_DAY_REWARD = "15만";

    public static List<String> VALID_RATES = new ArrayList<>(Arrays.asList("70%", "80%", "90%"));

    public static double PETPERMINT_DISCOUNT_RATE = 0.98;
    public static double DB_DISCOUNT_RATE = 0.98;
    public static double HYUNDAI_DISCOUNT_RATE = 0.95;
    public static double KB_DISCOUNT_RATE = 0.98;
    public static double SAMSUNG_DISCOUNT_RATE = 0.95;
}
