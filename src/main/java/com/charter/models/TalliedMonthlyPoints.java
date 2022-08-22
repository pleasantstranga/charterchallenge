package com.charter.models;

import java.time.Month;
import java.util.List;

public class TalliedMonthlyPoints {
    private Month month;
    private Long points;


    public TalliedMonthlyPoints(Month month, List<Long> points) {
        this.month = month;
        this.points = points.stream().mapToLong(Long::longValue).sum();
    }


    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public Long getPoints() {
        return points;
    }

    public void getPoints(Long points) {
        this.points = points;
    }

}
