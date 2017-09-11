package ua.nure.sliva.SummaryTask4.dao;

public interface GenericDAO<T> {
    T getById(int id);

    int create(T entity);

    int update(T entity);

    int delete(T entity);
}
