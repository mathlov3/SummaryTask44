package ua.nure.sliva.SummaryTask4.dao;

import ua.nure.sliva.SummaryTask4.entity.Commentary;

import java.util.List;

public interface CommentaryDAO extends GenericDAO<Commentary> {
    List<Commentary> getCommentariesByProductId(int id);
}
