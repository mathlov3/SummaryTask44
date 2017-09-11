package ua.nure.sliva.SummaryTask4.util;

import java.util.List;

public interface Pagenable<T> {
    List<T> getList(int start,int end,boolean ascending,String orderColumn,int category);
}
