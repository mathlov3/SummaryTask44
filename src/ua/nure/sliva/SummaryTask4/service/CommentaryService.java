package ua.nure.sliva.SummaryTask4.service;

import ua.nure.sliva.SummaryTask4.entity.Commentary;

import java.util.List;

public interface CommentaryService {
    boolean addCommentary(Commentary commentary);

    List<Commentary> getCommentariesByProductId(int id);
}
