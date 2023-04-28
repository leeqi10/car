package com.xhu.vo;

import com.xhu.entity.Driver;
import com.xhu.entity.Orderplus;
import com.xhu.entity.Passenger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orderplusvo {
    private Orderplus orderplus;

    private Passenger passenger;

    private Driver driver;
}
