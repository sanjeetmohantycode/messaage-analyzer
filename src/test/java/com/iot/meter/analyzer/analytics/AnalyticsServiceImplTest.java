package com.iot.meter.analyzer.analytics;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iot.meter.analyzer.domain.DailyConsumption;
import com.iot.meter.analyzer.dto.response.MeterUsageResponse;
import com.iot.meter.analyzer.repository.DailyConsumptionRepository;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@DirtiesContext
class AnalyticsServiceImplTest {

    @Mock
    DailyConsumptionRepository dailyConsumptionRepository;

    @InjectMocks
    AnalyticsService analyticsService = new AnalyticsServiceImpl();

    private final String INPUT_DATA_PATH = "src/test/resources/analytics/monthlyUsage/";

//    @BeforeEach
//    public void setUp() throws IOException {
//        var objectMapper = new ObjectMapper();
//        Collection<DailyConsumption> dailyConsumptionCollection = Arrays.asList(objectMapper
//                .readValue(new File(INPUT_DATA_PATH + "input.DailyConsumption.json"), DailyConsumption[].class));
//        when(dailyConsumptionRepository.findByOrgId(Mockito.anyString())).thenReturn(Flux.fromIterable(dailyConsumptionCollection));
//    }
//
//
//    @Test
//    void testFindCurrentMonthUsageBasedOnOrgId() throws IOException {
//        var objectMapper = new ObjectMapper();
//        MeterUsageResponse meterUsageResponse = objectMapper.readValue(new File(INPUT_DATA_PATH + "output.DailyConsumption.json"), MeterUsageResponse.class);
//        StepVerifier.create(analyticsService.findCurrentMonthUsageBasedOnOrgId("Sicken"))
//                .expectSubscription()
//                .assertNext(w -> assertThat(w).isEqualTo(meterUsageResponse))
//                .expectComplete()
//                .verify();
//    }
}
