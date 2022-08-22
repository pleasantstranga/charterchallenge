package com.charter.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class MonthlyPoints {
    private Month month;
    private List<Long> points = new ArrayList<>();


    public MonthlyPoints(Month month, Long points) {
        this.month = month;
        this.points.add(points);
    }

}
