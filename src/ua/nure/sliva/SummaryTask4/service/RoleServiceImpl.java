package ua.nure.sliva.SummaryTask4.service;

import ua.nure.sliva.SummaryTask4.entity.Role;
import ua.nure.sliva.SummaryTask4.transaction.ThreadLocaleHandler;
import ua.nure.sliva.SummaryTask4.transaction.Transaction;
import ua.nure.sliva.SummaryTask4.transaction.TransactionPool;
import ua.nure.sliva.SummaryTask4.dao.RoleDao;

import java.sql.SQLException;

public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;
    private TransactionPool transactionPool;
    public RoleServiceImpl(RoleDao roleDao,TransactionPool transactionPool){
        this.roleDao=roleDao;
        this.transactionPool = transactionPool;
    }
    @Override
    public Role getRoleById(int id) {
        Role role = transactionPool.execute(new Transaction<Role>() {
            @Override
            public Role execute() throws SQLException {
                return roleDao.getById(id);
            }
        });
        transactionPool.closeConnection(ThreadLocaleHandler.getConnection());
        ThreadLocaleHandler.setConnecion(null);
        return role;
    }
}
