package ua.nure.sliva.SummaryTask4.service;

import ua.nure.sliva.SummaryTask4.dao.CommentaryDAO;
import ua.nure.sliva.SummaryTask4.entity.Commentary;
import ua.nure.sliva.SummaryTask4.transaction.TRPool;
import ua.nure.sliva.SummaryTask4.transaction.ThreadLocaleHandler;
import ua.nure.sliva.SummaryTask4.transaction.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentaryServiceImpl implements CommentaryService {
    CommentaryDAO commentaryDAO;
    TRPool transactionPool;
    public CommentaryServiceImpl(CommentaryDAO commentaryDAO, TRPool transactionPool){
        this.commentaryDAO = commentaryDAO;
        this.transactionPool =transactionPool;
    }
    @Override
    public boolean addCommentary(Commentary commentary) {
        final boolean[] result = {false};
        transactionPool.execute(new Transaction<Commentary>() {
            @Override
            public Commentary execute() throws SQLException {
                result[0] = commentaryDAO.create(commentary) >0?true:false;
                return null;
            }
        });
        return result[0];
    }

    @Override
    public List<Commentary> getCommentariesByProductId(int id) {
        return transactionPool.execute(new Transaction<List<Commentary>>() {
            @Override
            public List<Commentary> execute() throws SQLException {
                return commentaryDAO.getCommentariesByProductId(id);
            }
        });
    }
}
