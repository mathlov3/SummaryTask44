package ua.nure.sliva.SummaryTask4.transaction;

public interface TRPool {
    <T> T execute(Transaction <T> transaction);
}
