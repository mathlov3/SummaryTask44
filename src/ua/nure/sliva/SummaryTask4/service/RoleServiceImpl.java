package ua.nure.sliva.SummaryTask4.service;

import ua.nure.sliva.SummaryTask4.entity.Role;
import ua.nure.sliva.SummaryTask4.transaction.TRPool;
import ua.nure.sliva.SummaryTask4.transaction.ThreadLocaleHandler;
import ua.nure.sliva.SummaryTask4.transaction.Transaction;

import ua.nure.sliva.SummaryTask4.dao.RoleDao;

import java.sql.SQLException;

public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;
    private TRPool transactionPool;
    public RoleServiceImpl(RoleDao roleDao,TRPool transactionPool){
        this.roleDao=roleDao;
        this.transactionPool = transactionPool;
    }
    @Override
    public Role getRoleById(int id) {
        return transactionPool.execute(new Transaction<Role>() {
            @Override
            public Role execute() throws SQLException {
                return roleDao.getById(id);
            }
        });
    }
}
