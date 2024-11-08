package Data;

import Config.ConfigLoader;
import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

public class TestDataProviderForUITests {
    public static Stream<Arguments> provideTestData() {

        return Stream.of(
                Arguments.of(ConfigLoader.getUiStandardUser(),
                        ConfigLoader.getUiStandardUserPassword(),
                        ConfigLoader.getTotalPrice(),
                        Integer.parseInt(ConfigLoader.getItemsAmount())),

                Arguments.of(ConfigLoader.getPerformanceGlitchUser(),
                        ConfigLoader.getPerformanceGlitchUserPassword(),
                        ConfigLoader.getTotalPrice(),
                        Integer.parseInt(ConfigLoader.getItemsAmount()))
        );
    }
}
