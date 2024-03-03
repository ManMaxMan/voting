package org.applications.servlets.dao.factory;
import org.applications.servlets.dao.api.*;
import org.applications.servlets.dao.*;

public class DaoFactory {
    private volatile static IVoteDao voteDao;

    public static IVoteDao getVoteDao(){
        if(voteDao == null){
            synchronized (DaoFactory.class){
                if(voteDao == null){
                    voteDao = new VoteDao();
                }
            }
        }
        return voteDao;
    }
}
