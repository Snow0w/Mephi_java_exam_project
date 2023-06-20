package edu.mephi.stats;

import java.util.ArrayList;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

public class Calculate {
  private final double quantile = 1.69;

  private Result firstQuartile(double[] arr) {
    Result out = new Result();
    out.name = new String("Первый квартиль");
    out.value = StatUtils.percentile(arr, 25.0);
    out.value = round((double)out.value, 1);
    return out;
  }

  private Result thirdQuartile(double[] arr) {
    Result out = new Result();
    out.name = new String("Третий квартиль");
    out.value = StatUtils.percentile(arr, 75.0);
    out.value = round((double)out.value, 1);
    return out;
  }

  private Result arithmeticMean(double[] arr) {
    Result out = new Result();
    out.name = new String("Среднее арифметическое");
    out.value = StatUtils.mean(arr);
    out.value = round((double)out.value, 1);
    return out;
  }

  private Result variance(double[] arr) {
    Result out = new Result();
    out.name = new String("Оценка дисперсии");
    out.value = StatUtils.populationVariance(arr);
    out.value = round((double)out.value, 1);
    return out;
  }

  private Result interval(double[] arr) {
    Result out = new Result();
    out.name = new String("Доверительный интервал для мат ожидания");
    double mean = StatUtils.mean(arr);
    StandardDeviation s = new StandardDeviation();
    double sd = s.evaluate(arr);
    double lowBound = mean - quantile * sd / Math.sqrt(arr.length);
    lowBound = round(lowBound, 2);
    double highBound = mean + quantile * sd / Math.sqrt(arr.length);
    highBound = round(highBound, 2);
    out.value = new String("[" + Double.toString(lowBound) + "; " +
                           Double.toString(highBound) + "]");

    return out;
  }
  private double round(double value, int precision) {
    int scale = (int)Math.pow(10, precision);
    return (double)Math.round(value * scale) / scale;
  }

  public ArrayList<Result> calcPerArray(double[] arr) {
    ArrayList<Result> out = new ArrayList<Result>();
    out.add(arithmeticMean(arr));
    out.add(variance(arr));
    out.add(interval(arr));

    return out;
  }
}
