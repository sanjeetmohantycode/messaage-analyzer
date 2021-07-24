package com.iot.meter.analyzer.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IncomingIOTMessage {

    @NotBlank(message = "Device type is required field")
    private String deviceType;

    @NotBlank(message = "Imei number is a required field")
    private String imei;

    @Max(Long.MAX_VALUE)
    @Min(1L)
    private long messageCount;

    @PastOrPresent
    private ZonedDateTime messageTimeStamp;

    @Max(Long.MAX_VALUE)
    @Min(1L)
    private long meterReading;

    private boolean cableStatus = false;

    @Min(0L)
    private float remainingBattery;

    private double latitude;

    private double longitude;
}
